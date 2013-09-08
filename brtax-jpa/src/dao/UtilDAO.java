/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Felipe
 */
public class UtilDAO {
  /**  
   * M�todo utilizado para obter o entity manager.
   * @return
   */
  public static EntityManager getEntityManager() {
    EntityManagerFactory factory = null;
    EntityManager entityManager = null;
    try {
      //Obt�m o factory a partir da unidade de persist�ncia.
      factory = Persistence.createEntityManagerFactory("brtax-jpa");
      //Cria um entity manager.
      entityManager = factory.createEntityManager();
      //Fecha o factory para liberar os recursos utilizado.
    } finally {
      factory.close();
    }
    return entityManager;
  }

    
}
