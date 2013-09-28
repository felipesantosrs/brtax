/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Product;

/**
 *
 * @author Felipe
 */
public class ProductDAO {
    
private EntityManagerFactory emf;
 
    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("brtax");
        }
        return emf.createEntityManager();
    }
   
   /** 
   * Consulta produto por EAN
   * @param ean
   * @return o objeto Pessoa.
   */
    
  public List<Product> searchProductToEan( long ean) {
      EntityManager em = getEntityManager();
      List<Product> listProduct = new ArrayList<>();
    try{
       listProduct= em.createNamedQuery("Product.findByGtin").setParameter("gtin", ean).getResultList();
    } finally {
      em.close();
    }
    return listProduct;
  }
  
  /**
   * Persistir produto na base de dados
   * @param product 
   */
  public void create(Product product) {
      EntityManager em = getEntityManager();  
    try{
       em.getTransaction().begin();
       em.persist(product);
       em.getTransaction().commit();
    } catch(Exception e){
        throw  e;
    }
    finally {
      em.close();
    }
  }
}

