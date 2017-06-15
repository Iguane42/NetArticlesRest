/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Auteur;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Epulapp
 */
@Stateless
@LocalBean
public class AuteurFacade {

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;
    
    private Auteur auteur;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Auteur Lire_Auteur_Id(int id_auteur) throws Exception {
        try {
            return em.find(Auteur.class, id_auteur);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Auteur lireLogin(String login)
    {
        try {
            Query requete = em.createNamedQuery("Auteur.findByLoginAuteur");
            requete.setParameter("loginAuteur", login);
            return ((Auteur)requete.getSingleResult());
        } catch (Exception e) {
            throw e;
        }
    }
}
