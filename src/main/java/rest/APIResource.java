package rest;

import DTO.CountDTO;
import DTO.movieInfo;
import DTO.movieScore;
import com.google.gson.Gson;
import entities.Count;
import entities.MovieInfo;
import entities.MovieScore;
import entities.User;
import facades.fetchFacade;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 * @author Hamad
 */
@Path("movie")
public class APIResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private fetchFacade api = new fetchFacade();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            List<User> users = em.createQuery("select user from User user").getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-info-simple/{title}")
    public String getMovie(@PathParam("title") String title) throws ProtocolException, IOException {
        Gson g = new Gson();
        createOrGetMovie(title);
        movieInfo info = api.getMovieData(title);
        return g.toJson(info);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-info-all/{title}")
    //@RolesAllowed("user")
    public String getMovieScore(@PathParam("title") String title) throws ProtocolException, IOException {
        Gson g = new Gson();
        createOrGetMovie(title);
        EntityManager em = EMF.createEntityManager();
        try {
            List<MovieInfo> mi = em.createQuery("select i from MovieInfo i where i.title=:title").setParameter("title", title).getResultList();
            movieInfo result = new movieInfo(mi.get(0));
            return g.toJson(result);
        } finally {
            em.close();
        }
    }

    public boolean createOrGetMovie(String title) throws ProtocolException, IOException {
        if (MovieExist(title)) {
            EntityManager em = EMF.createEntityManager();
            try {
                List<MovieInfo> mi = em.createQuery("select i from MovieInfo i where i.title=:title").setParameter("title", title).getResultList();
                Count c = (Count) em.createQuery("select c from Count c where c.mf=:mf").setParameter("mf", mi.get(0)).getSingleResult();
                c.count();
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return false;
        } else {
            movieInfo info = api.getMovieData(title);
            List<movieScore> ms = api.getMovieScore(title);
            info.setScores(ms);
            EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            MovieInfo mi = new MovieInfo(info);
            ArrayList<MovieScore> scoreList = new ArrayList<MovieScore>();
            for (movieScore x : ms) {
                scoreList.add(new MovieScore(x, mi));
            }
            mi.setScores(scoreList);
            Count c = new Count(1, mi);
            em.persist(mi);
            em.persist(c);
            em.getTransaction().commit();
            em.close();
            emf.close();
            return true;
        }
    }

    public boolean MovieExist(String title) {
        EntityManager em = EMF.createEntityManager();
        try {
            List<MovieInfo> mi = em.createQuery("select i from MovieInfo i where i.title=:title").setParameter("title", title).getResultList();
            if (mi.size() > 0) {
                return true;
            } else {
                return false;
            }
        } finally {
            em.close();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("movie-count/{title}")
    @RolesAllowed("admin")
    public String getMovieCount(@PathParam("title") String title) throws ProtocolException, IOException {
        EntityManager em = EMF.createEntityManager();
        Gson g = new Gson();
        try {
            List<MovieInfo> mi = em.createQuery("select i from MovieInfo i where i.title=:title").setParameter("title", title).getResultList();
            Count c = (Count) em.createQuery("select c from Count c where c.mf=:mf").setParameter("mf", mi.get(0)).getSingleResult();
            CountDTO ct = new CountDTO(c.getCount(), new movieInfo(mi.get(0)));
            return g.toJson(ct);
        } catch (Exception e) {
            return "not found";
        } finally {
            em.close();
        }

    }

}
