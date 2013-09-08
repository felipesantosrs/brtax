/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brtax.impl;

import com.brtax.mbean.SearchProductMBean;
import com.brtax.service.SearchProductBeanLocal;
import dao.ProductDAO;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import model.Product;
import org.jsoup.nodes.Document;

/**
 *
 * @author Felipe
 */
@Stateless
public class SearchProductBean implements SearchProductBeanLocal {

    Document doc = null;
    Product product = null;

    public String searchProductEAN(int ean) {
        try {
            ProductDAO productDAO = new ProductDAO();
            product = productDAO.searchProductToEan(ean);
            if (product == null) {
                SearchProductMBean searchProduct = new SearchProductMBean();
                searchProduct.searchGoogle(ean);
                searchProduct.searchCosmos(ean);
            }
            


        } catch (Exception e) {
        }
        return null;


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
