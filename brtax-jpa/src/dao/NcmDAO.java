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
 * Classe responsável por fazer consultas e inserções na tabela NCM
 *
 * @author Felipe
 */
public class NcmDAO {

    private EntityManagerFactory emf;

    /**
     * Método responsável por instanciar entity manager
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
     * @param ncmCode - código do ncm
     * @return lista de ncm encontrado pelo código
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
     * Busca NCM pela descrição
     *
     * @param description - descrição do ncm
     * @return lista de ncm pela descrição
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