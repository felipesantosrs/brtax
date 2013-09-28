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
import model.Ncm;
/**
 *
 * @author Felipe
 */
public class NcmDAO {

    private EntityManagerFactory emf;

    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("brtax");
        }
        return emf.createEntityManager();
    }

    public List<Ncm> searchNCM(int ncmCode) {
        EntityManager em = getEntityManager();
         List<Ncm> listNcm = new ArrayList<>();
        try {
            listNcm =  em.createNamedQuery("Ncm.findByNcmCode").setParameter("ncmCode", ncmCode).getResultList();
        } finally {
            em.close();
        }
        return listNcm;
    }
        
        
   public List<Ncm> searchDescriptionNCM(String description) {
        EntityManager em = getEntityManager();
        List<Ncm> listNcm = new ArrayList<>();
        try {
            String descriptionParameter ="%'"+description.replace(" ","%','%")+"'%";
            
            listNcm = em.createNamedQuery("Ncm.findByDescription").setParameter("description", descriptionParameter).getResultList();
        } finally {
            em.close();
        }
        return listNcm;
    }
       
}