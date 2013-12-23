/*
 * BRTAX - Servidor
 */
package com.brtax.service;

import com.brtax.dto.ProductDTO;
import javax.ejb.Local;

/**
 * Interface que representa a consulta de produtos
 *
 * @author Felipe
 */
@Local
public interface SearchProductBeanLocal {

    /**
     * Método que realiza consulta de informações sobre produto
     *
     * @param ean- código ean informado na chamada do web service
     * @param priceString - preço em formato string
     * @return retorna informações sobre produto: nome, aliquota de imposto,
     * valor de imposto e etc..
     *
     * @throws EJBException
     */
    ProductDTO searchProductEAN(String ean, String priceString);
}
