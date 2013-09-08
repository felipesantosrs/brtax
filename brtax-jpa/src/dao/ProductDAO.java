/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Product;

/**
 *
 * @author Felipe
 */
public class ProductDAO {
   /** 
   * Consulta produto por EAN
   * @param ean
   * @return o objeto Pessoa.
   */
  public Product searchProductToEan( int ean) {
      EntityManager entityManager = UtilDAO.getEntityManager();
      Product product = null;
    try{
        Query query = entityManager.createQuery("select p from Product as p where p.gtin=:ean");
        query.setParameter("ean", ean);
        product =  (Product) query.getSingleResult();
    } finally {
      entityManager.close();
    }
    return product;
  }
}

