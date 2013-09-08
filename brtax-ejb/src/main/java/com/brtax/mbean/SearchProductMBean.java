/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brtax.mbean;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Felipe
 */
public class SearchProductMBean{
    
   Document doc = null;
   
   
    public Document searchGoogle(int ean) throws IOException {
        doc = Jsoup.connect("http://cosmos.bluesoft.com.br/products/" + ean).get();
        return doc;
    }

    public Document searchCosmos(int ean) throws IOException {
        doc = Jsoup.connect("http://cosmos.bluesoft.com.br/products/").get();
        return doc;
    }

}
