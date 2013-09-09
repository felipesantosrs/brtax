/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brtax.impl;

import com.brtax.mbean.SearchProductMBean;
import com.brtax.service.SearchProductBeanLocal;
import dao.ProductDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.jws.*;
import model.Product;
import org.jsoup.nodes.Document;

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

    Document doc = null;

    List<Product> listProduct = new ArrayList<>();
       @WebResult(name="result") 
       @Override
       public String searchProductEAN(@WebParam(name="ean")  String ean ) {
        try {
            long eanLong = Long.parseLong(ean);
            ProductDAO productDAO = new ProductDAO();
            listProduct = productDAO.searchProductToEan(eanLong);
            String valor;
            if (listProduct.isEmpty()) {
                SearchProductMBean searchProduct = new SearchProductMBean();
                searchProduct.searchGoogle(eanLong);
                searchProduct.searchCosmos(eanLong);
                valor="Sucesso IF";
            }else {
             valor="Sucesso ELSE";
            }
            

            return valor;
        } catch ( IOException e) {
            String msg = e.toString();
            return msg;
        }
        


    }
    
     @PostConstruct
    public void initialize() {
        System.out.println("Starting");
    }

    @PreDestroy
    public void stop() {
        System.out.println("Stopping");
    }


}
