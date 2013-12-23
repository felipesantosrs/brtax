/*
 * BRTAX - Servidor
 */
package com.brtax.mbean;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Classe responsável por fazer consultar no google de produtos
 *
 * @author Felipe
 */
public class SearchGoogleMBean {

    Document doc = null;

    /**
     * Consulta de descrição de produtos no google
     *
     * @param description - descrição do produto
     * @return retorna o product com a url das informações sobre produto
     * @throws IOException
     */
    public String searchDescription(String description) throws IOException {
        Document doc = Jsoup.connect("http://www.google.com.br/#q=cosmos.bluesoft.com.br%20" + description).timeout(300000000).ignoreHttpErrors(true).userAgent("Mozilla").get();
        String urlCosmos = doc.select("h3.r").select("a").text();
        if (!doc.select("h3.r").select("a").text().isEmpty()) {
            return urlCosmos;
        }
        return null;

    }
}