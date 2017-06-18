/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Domaine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Epulapp
 */
@Stateless
public class DomaineFacade {

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Domaine> Lister_Domaines() throws Exception {
        try {
            return (em.createNamedQuery("Domaine.findAll").getResultList());
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Domaine Lire_Domaine_Id(int id_domaine) throws Exception {
        try {
           return em.find(Domaine.class, id_domaine);
        } catch (Exception e) {
            throw e;
        }
    }
}
