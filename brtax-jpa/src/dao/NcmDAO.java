/*
 * BRTAX - Servidor
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Ncm;

/**
 * Classe respons�vel por fazer consultas e inser��es na tabela NCM
 *
 * @author Felipe
 */
public class NcmDAO {

    private EntityManagerFactory emf;

    /**
     * M�todo respons�vel por instanciar entity manager
     *
     * @return entity manager criado
     */
    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("brtax");
        }
        return emf.createEntityManager();
    }

    /**
     * Busca NCM
     *
     * @param ncmCode - c�digo do ncm
     * @return lista de ncm encontrado pelo c�digo
     */
    public List<Ncm> searchNCM(int ncmCode) {
        EntityManager em = getEntityManager();
        List<Ncm> listNcm = new ArrayList<>();
        try {
            listNcm = em.createNamedQuery("Ncm.findByNcmCode").setParameter("ncmCode", ncmCode).getResultList();
        } finally {
            em.close();
        }
        return listNcm;
    }

    /**
     * Busca NCM pela descri��o
     *
     * @param description - descri��o do ncm
     * @return lista de ncm pela descri��o
     */
    public List<Ncm> searchDescriptionNCM(String description) {
        EntityManager em = getEntityManager();
        List<Ncm> listNcm = new ArrayList<>();
        try {
            String descriptionParameter = "%'" + description.replace(" ", "%','%") + "'%";

            listNcm = em.createNamedQuery("Ncm.findByDescription").setParameter("description", descriptionParameter).getResultList();
        } finally {
            em.close();
        }
        return listNcm;
    }
}