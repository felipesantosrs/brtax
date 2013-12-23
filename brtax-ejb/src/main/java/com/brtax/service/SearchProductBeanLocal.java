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
     * M�todo que realiza consulta de informa��es sobre produto
     *
     * @param ean- c�digo ean informado na chamada do web service
     * @param priceString - pre�o em formato string
     * @return retorna informa��es sobre produto: nome, aliquota de imposto,
     * valor de imposto e etc..
     *
     * @throws EJBException
     */
    ProductDTO searchProductEAN(String ean, String priceString);
}
