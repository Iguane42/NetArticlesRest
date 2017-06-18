/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Redige;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Epulapp
 */
@Stateless
public class RedigeFacade {

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;
    
    public List<Redige> getRedigeArticle(int id_article) throws Exception {
        try {
            Query requete = em.createNamedQuery("Redige.findByIdArticle");
            requete.setParameter("idArticle", id_article);
            return ((List<Redige>)requete.getResultList());
        } catch (Exception e) {
            throw e;
        }
    }
}
