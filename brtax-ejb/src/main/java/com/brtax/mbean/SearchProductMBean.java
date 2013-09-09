/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brtax.mbean;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Felipe
 */
public class SearchProductMBean {

    Document doc = null;

    public Document searchGoogle(long ean) throws IOException {
        doc = Jsoup.connect("https://www.google.com.br/?gws_rd=cr&ei=xE0tUt3sDYqG9QT1r4G4CQ#q=" + ean).userAgent("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.2 Safari/537.36").timeout(50000).get();
        Elements links = doc.select("li[class=g]");
        for (Element link : links) {
            Elements titles = link.select("h3[class=r]");
            String title = titles.text();

            Elements bodies = link.select("span[class=st]");
            String body = bodies.text();

            System.out.println("Title: " + title);
            System.out.println("Body: " + body + "\n");
        }
        return doc;
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    public Document searchCosmos(long ean) throws IOException {
        doc = Jsoup.connect("http://cosmos.bluesoft.com.br/products/" + ean).get();
        return doc;
    }

    private static String trim(String s, int width) {
        if (s.length() > width) {
            return s.substring(0, width - 1) + ".";
        } else {
            return s;
        }
    }
}
