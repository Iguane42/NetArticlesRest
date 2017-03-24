/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Article;
import java.util.List;
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
public class ArticleFacade {

    @PersistenceContext(unitName = "NetArticlesRestPU")
    private EntityManager em;
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Article Lire_Article_Id(int id_article) throws Exception {
        try {
            return em.find(Article.class, id_article);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public List<Article> Lister_Articles() throws Exception {
        try {
            return (em.createNamedQuery("Article.findAll").getResultList());
        } catch (Exception e) {
            throw e;
        }
    }
}
