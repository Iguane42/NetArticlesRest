/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utilitaires.Utilitaire;
import dal.Achete;
import dal.Article;
import dal.Auteur;
import dal.Client;
import dal.Domaine;
import dal.Redige;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Hibernate;
import session.AcheteFacade;
import session.ArticleFacade;
import session.ClientFacade;
import session.DomaineFacade;
import session.RedigeFacade;

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
    @EJB
    AcheteFacade acf;
    @EJB
    RedigeFacade rf;

    /**
     * Creates a new instance of WebserviceResource
     */
    public WebserviceResource() {
    }
    
    @GET
    @Path("getConnexionClient/{login}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
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
    
    @Transactional
    @GET
    @Path("getAchatsClient/{id}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response getAchatsClient(@PathParam("id") int id) {
        Response response = null;
        try {
            Client cli = cf.Lire_Client_Id(id);
            Hibernate.initialize(cli.getAcheteList());
            GenericEntity<List<Achete>> GECli = new GenericEntity<List<Achete>>(cli.getAcheteList()) {};
            response = Response.status(Response.Status.OK).entity(GECli).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
    @GET
    @Path("getArticle/{id_article}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
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
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
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
    @Path("getLastArticle")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response getLastArticle() throws Exception {
        Response response = null;
        try {
            Article article = af.lastArticle();
            GenericEntity<Article> GEarticle = new GenericEntity<Article>(article) {};
            response = Response.status(Response.Status.OK).entity(GEarticle).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
    @GET
    @Path("getDomaines")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
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
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
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
    
    @GET
    @Path("getAuteursArticle/{id_article}")
    //@Path("getArticlesParDomaine")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response getAuteursArticle(@PathParam("id_article") int idArticle) throws Exception {
    //public Response getArticlesDomaine() throws Exception {
        Response response = null;
        try {
            List<Redige> rediges = rf.getRedigeArticle(idArticle);
            List<Auteur> lauteurs = new ArrayList<Auteur>();
            for(Redige r : rediges)
            {
                lauteurs.add(r.getAuteur());
            }
            //Hibernate.initialize(domaine.getArticleList());
            GenericEntity<List<Auteur>> GElAuteur = new GenericEntity<List<Auteur>>(lauteurs) {};
            response = Response.status(Response.Status.OK).entity(GElAuteur).build();
        } catch (Exception e) {
            JsonObject retour = Json.createObjectBuilder().add("message", Utilitaire.getExceptionCause(e)).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
    @POST
    @Path("acheteArticle")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acheteArticle(Achete achete) throws Exception {
    //public Response getArticlesDomaine() throws Exception {
        Response response = null;
        try {
            //Domaine domaine = df.Lire_Domaine_Id(id_domaine);
            //Hibernate.initialize(domaine.getArticleList());
            //GenericEntity<List<Article>> GElArticle = new GenericEntity<List<Article>>(domaine.getArticleList()) {};
            acf.Ajouter(achete);
            JsonObject GEAchat = Json.createObjectBuilder().add("Succes", true).build();
            response = Response.status(Response.Status.OK).entity(GEAchat).build();
        } catch (Exception e) {
            String erreur = Utilitaire.getExceptionCause(e);
            if(erreur.contains("PRIMARY"))
                erreur = "Vous avez déjà acheté un des articles du panier.";            
            JsonObject retour = Json.createObjectBuilder().add("message", erreur).build();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(retour).build();
        }
        return response;
    }
    
}
