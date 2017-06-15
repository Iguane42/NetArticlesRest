/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Achete;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Epulapp
 */
@Stateless
@LocalBean
public class AcheteFacade {
    
    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;
    
    public void Ajouter(Achete achete)
    {
        //Achete a = new Achete(idClient,idArticle);
        //a.setDateAchat(new Date());
        try {
            em.persist(achete);
        } catch (Exception e) {
            throw e;
        }
        
    }
}
