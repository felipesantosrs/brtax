/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brtax.service;

import com.brtax.dto.ProductDTO;
import javax.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface SearchProductBeanLocal {
    
    /**
     * 
     * @param ean
     * @param price
     * @return 
     */
    ProductDTO searchProductEAN(String ean, String priceString);
    
}
