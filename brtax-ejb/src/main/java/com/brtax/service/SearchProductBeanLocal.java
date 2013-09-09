/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brtax.service;

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
     * @return
     */
    String searchProductEAN(String ean);
    
}
