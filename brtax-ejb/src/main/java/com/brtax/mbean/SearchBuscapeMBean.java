/*
 * BRTAX - Servidor
 */
package com.brtax.mbean;

import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * Classe que realiza busca de informações sobre produto pelo web service
 *
 * @author Felipe
 */
public class SearchBuscapeMBean {

    private final String URL_WEBSERVICE_BUSCAPE = "http://sandbox.buscape.com/service/findOfferList/67616954656369504a726b3d/?barcode=";
    private final int HTTP_COD_SUCESSO = 200;

    /**
     * Método que realiza busca de informações sobre produto no web service do
     * buscapé
     *
     * @param ean - código ean do produto
     * @return retorna o objeto product com as informações sobre produto
     * @throws MalformedURLException
     * @throws IOException
     */
    public Product searchProduct(long ean) throws MalformedURLException, IOException {
        URL url = new URL(URL_WEBSERVICE_BUSCAPE + ean);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // con.setRequestProperty("Accept", "text/xml");  
        if (con.getResponseCode() != HTTP_COD_SUCESSO) {
            throw new RuntimeException("HTTP error code : " + con.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        SAXBuilder sb = new SAXBuilder();
        Document d;
        Product product = new Product(ean, "buscape");
        try {
            d = sb.build(br);
            Element result = d.getRootElement();
            List<Element> list = result.getChildren();
            for (Element element : list) {
                if (element.getName() == "category") {
                    Element elementCategory = (Element) element.getChildren().get(2);
                    product.setCategory(elementCategory.getContent(0).getValue());
                }
                if (element.getName() == "offer") {
                    Element elementOffer = (Element) element.getChildren().get(1);
                    String offer = elementOffer.getContent(0).getValue();
                    if (!offer.isEmpty()) {
                        product.setProductName(offer.trim());
                        break;
                    }

                }
            }

        } catch (JDOMException ex) {
            Logger.getLogger(SearchBuscapeMBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        con.disconnect();
        return product;
    }
}
