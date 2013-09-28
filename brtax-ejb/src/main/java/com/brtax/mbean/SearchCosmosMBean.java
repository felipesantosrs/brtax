/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brtax.mbean;

import dao.NcmDAO;
import java.io.IOException;
import java.util.List;
import model.Ncm;
import model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Felipe
 */
public class SearchCosmosMBean {
    Document doc = null;
    
    public Product searchProduct(long eanLong, String url, Product productExt) throws IOException {
        
        Product product = productExt;
        List<Ncm> listNcm = null;
       
        if (url==null) {
              doc = Jsoup.connect("http://cosmos.bluesoft.com.br/products/" + eanLong).timeout(300000000).ignoreHttpErrors(true).followRedirects(true).get();
        } else {
                doc = Jsoup.connect(url).timeout(300000000).ignoreHttpErrors(true).followRedirects(true).get();
        }
        // String pis = doc.select("span.text.block-line.pis").text();
        //String cofins = doc.select("span.text.block-line.cofins").text();
            if (!doc.select("span.full-description").select("a").text().isEmpty()) {
            String ncmCode = doc.select("span.full-description").select("a").text();
            String productName = doc.select("h3.center").text();
            if (!ncmCode.isEmpty()) {
                ncmCode = ncmCode.replace(".", "").substring(0, ncmCode.replace(".", "").indexOf(" "));
                if (product == null) {
                    product = new Product(eanLong, "Cosmos");
                    product.setProductName(productName.trim());
                }
                NcmDAO ncmDAO = new NcmDAO();
                listNcm = ncmDAO.searchNCM(Integer.valueOf(ncmCode));
                for (Ncm ncm : listNcm) {
                    product.setNcmCode(ncm);
                }
            }
        }
        return product;
    }
}
