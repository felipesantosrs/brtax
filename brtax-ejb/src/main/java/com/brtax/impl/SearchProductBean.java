/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brtax.impl;

import com.brtax.mbean.SearchBuscapeMBean;
import com.brtax.mbean.SearchCosmosMBean;
import com.brtax.mbean.SearchGoogleMBean;
import com.brtax.service.SearchProductBeanLocal;
import dao.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.*;
import model.NcmTax;
import model.Product;

/**
 *
 * @author Felipe
 */
@Stateless
@WebService(name = "BRTAXPortType",
        serviceName = "BRTAXService",
        portName = "BRTAXSoapPort",
        targetNamespace = "http://brtax.com")
public class SearchProductBean implements SearchProductBeanLocal {

    @WebResult(name = "result")
    @Override
    public String searchProductEAN(@WebParam(name = "ean") String ean) {
        List<Product> listProduct = null;
        String gtin = null;
        String tax = null;
        String productName = null;
        int error = 0;
        try {
            long eanLong = Long.parseLong(ean);
            ProductDAO productDAO = new ProductDAO();
            listProduct = productDAO.searchProductToEan(eanLong);
            Product product = null;

            // Verifica se o produto existe na base de dados
            if (listProduct.isEmpty()) {
                SearchCosmosMBean searchCosmos = new SearchCosmosMBean();
                product = searchCosmos.searchProduct(eanLong, null, null);
                if (product != null) {
                    productDAO.create(product);
                    gtin = ean;
                    for (NcmTax ncmTax : product.getNcmCode().getNcmTaxList()) {
                        tax = String.valueOf(ncmTax.getAliquot());
                    }
                    productName = product.getProductName();
                } else {
                    // se não achou dados de ncm no Cosmos busca no buscape a categoria
                    SearchBuscapeMBean searchBuscape = new SearchBuscapeMBean();
                    product = searchBuscape.searchProduct(eanLong);
                    if (!product.getCategory().isEmpty()) {
                        SearchGoogleMBean searchGoogle = new SearchGoogleMBean();
                        String url = searchGoogle.searchDescription(product.getDescription());
                        if (url.isEmpty()) {
                            product = searchCosmos.searchProduct(0, url, product);
                            productDAO.create(product);
                        }
                        gtin = ean;
                        for (NcmTax ncmTax : product.getNcmCode().getNcmTaxList()) {
                            tax = String.valueOf(ncmTax.getAliquot());
                        }
                        productName = product.getProductName();
                    } else {
                        // não achou informações sobre produto no cosmos e no buscape
                        error = 1;
                    }
                }
            } else {
                for (Product prod : listProduct) {
                    gtin = ean;
                    for (NcmTax ncmTax : prod.getNcmCode().getNcmTaxList()) {
                        tax = String.valueOf(ncmTax.getAliquot());
                    }
                    productName = prod.getProductName();
                    break;

                }
            }
        } catch (IOException e) {
        }
        String retorno = gtin + ";" + productName + ";" + tax + ";" + error;
        return retorno;



    }
}
