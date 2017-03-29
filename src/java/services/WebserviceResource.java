/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utilitaires.Utilitaire;
import dal.Article;
import dal.Categorie;
import dal.Client;
import dal.Domaine;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Hibernate;
import session.ArticleFacade;
import session.ClientFacade;
import session.DomaineFacade;

/**
 * REST Web Service
 *
 * @author Epulapp
 */
@Path("webservice")
public class WebserviceResource {
    @EJB
    ClientFacade cf;
    @EJB
    ArticleFacade af;
    @EJB
    DomaineFacade df;

    /**
     * Creates a new instance of WebserviceResource
     */
    public WebserviceResource() {
    }
    
    @GET
    @Path("getConnexionClient/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response connecterClient(@PathParam("login") String login) {
        Response response = null;
        try {
            Client cli = cf.lireLogin(login);
            GenericEntity<Client> GECli = new GenericEntity<Client>(cli) {};
            response = Response.status(Response.Status.OK).entity(GECli).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
    @GET
    @Path("getArticle/{id_article}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticleId(@PathParam("id_article") int id_article) throws Exception {
        Response response = null;
        try {
            Article article = af.Lire_Article_Id(id_article);
            GenericEntity<Article> GEArticle = new GenericEntity<Article>(article) {};
            response = Response.status(Response.Status.OK).entity(GEArticle).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
    @GET
    @Path("getArticles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticles() throws Exception {
        Response response = null;
        try {
            List<Article> larticle = af.Lister_Articles();
            GenericEntity<List<Article>> GElarticle = new GenericEntity<List<Article>>(larticle) {};
            response = Response.status(Response.Status.OK).entity(GElarticle).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
    @GET
    @Path("getDomaines")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDomaines() throws Exception {
        Response response = null;
        try {
            List<Domaine> ldomaines = df.Lister_Domaines();
            GenericEntity<List<Domaine>> GEldomaines = new GenericEntity<List<Domaine>>(ldomaines) {};
            response = Response.status(Response.Status.OK).entity(GEldomaines).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
    @Transactional
    @GET
    @Path("getArticlesParDomaine/{id_domaine}")
    //@Path("getArticlesParDomaine")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticlesDomaine(@PathParam("id_domaine") int id_domaine) throws Exception {
    //public Response getArticlesDomaine() throws Exception {
        Response response = null;
        try {
            Domaine domaine = df.Lire_Domaine_Id(id_domaine);
            Hibernate.initialize(domaine.getArticleList());
            GenericEntity<List<Article>> GElArticle = new GenericEntity<List<Article>>(domaine.getArticleList()) {};
            response = Response.status(Response.Status.OK).entity(GElArticle).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
}
