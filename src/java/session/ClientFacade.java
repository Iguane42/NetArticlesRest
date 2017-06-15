/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Client;
import java.util.List;
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
public class ClientFacade {

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;
    
    private Client client;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Client Lire_Client_Id(int id_article) throws Exception {
        try {
            
            Client cli = em.find(Client.class, id_article);
            cli.getAcheteList();
            return cli;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Client> Lister_Clients() throws Exception {
        try {
            return (em.createNamedQuery("Client.findAll").getResultList());
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Client lireLogin(String login)
    {
        try {
            Query requete = em.createNamedQuery("Client.findByLoginClient");
            requete.setParameter("loginClient", login);
            return ((Client)requete.getSingleResult());
        } catch (Exception e) {
            throw e;
        }
    }
}
