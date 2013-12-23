/*
 * BRTAX - Servidor
 */
package com.brtax.impl;

import com.brtax.dto.ProductDTO;
import com.brtax.mbean.SearchBuscapeMBean;
import com.brtax.mbean.SearchCosmosMBean;
import com.brtax.mbean.SearchGoogleMBean;
import com.brtax.service.SearchProductBeanLocal;
import dao.ProductDAO;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.jws.*;
import model.NcmTax;
import model.Product;

/**
 * Classe que permite consultar informa��es dobre produto
 *
 * @author Felipe
 */
@Stateless
@WebService(targetNamespace = "http://brtax.com/")
public class SearchProductBean implements SearchProductBeanLocal {

    /**
     * M�todo que realiza consulta de informa��es sobre produto
     *
     * @param ean- c�digo ean informado na chamada do web service
     * @param priceString - pre�o em formato string
     * @return retorna informa��es sobre produto: nome, aliquota de imposto,
     * valor de imposto e etc..
     *
     * @throws EJBException
     */
    @Override
    @WebMethod
    public ProductDTO searchProductEAN(@WebParam(name = "ean") String ean, @WebParam(name = "price") String priceString) throws EJBException {
        List<Product> listProduct = null;
        String gtin = null;
        double tax = 0;
        String productName = null;
        int error = 0;
        double price;
        if (priceString.isEmpty()) {
            price = 0.00;
        } else {
            price = Double.parseDouble(priceString);
        }
        ProductDTO productDTO = null;


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
                        tax = ncmTax.getAliquot();
                    }
                    productName = product.getProductName();
                } else {
                    // se n�o achou dados de ncm no Cosmos busca no buscape a categoria
                    SearchBuscapeMBean searchBuscape = new SearchBuscapeMBean();
                    product = searchBuscape.searchProduct(eanLong);
                    if (!product.getCategory().isEmpty()) {
                        SearchGoogleMBean searchGoogle = new SearchGoogleMBean();
                        String url = searchGoogle.searchDescription(product.getProductName());
                        if (!url.isEmpty()) {
                            product = searchCosmos.searchProduct(0, url, product);
                            productDAO.create(product);
                        }
                        gtin = ean;
                        for (NcmTax ncmTax : product.getNcmCode().getNcmTaxList()) {
                            tax = ncmTax.getAliquot();
                        }
                        productName = product.getProductName();
                    } else {
                        // n�o achou informa��es sobre produto no cosmos e no buscape
                        error = 1;
                    }
                }
            } else {
                for (Product prod : listProduct) {
                    gtin = ean;
                    for (NcmTax ncmTax : prod.getNcmCode().getNcmTaxList()) {
                        tax = ncmTax.getAliquot();
                    }
                    productName = prod.getProductName();
                    break;

                }
            }
            productDTO = new ProductDTO();
            if (price != 0.00) {
                productDTO.setValueTax(price * (tax / 100));
                productDTO.setPriceFee(price - productDTO.getValueTax());
                productDTO.setPrice(price);
            }

            productDTO.setName(productName);
            productDTO.setTax(tax);

        } catch (Exception e) {
        }
        return productDTO;



    }
}
