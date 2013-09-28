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
public class SearchGoogleMBean {

    Document doc = null;

    public String searchDescription(String description) throws IOException {
       Document doc = Jsoup.connect("http://www.google.com/search?q=cosmos.bluesoft.com.br " + description).timeout(300000000).ignoreHttpErrors(true).followRedirects(true).get();
        String urlCosmos = doc.select("h3.r").select("a").text();
        if (!doc.select("h3.r").select("a").text().isEmpty()) {
            return urlCosmos;
        }
        return null;

    }
}