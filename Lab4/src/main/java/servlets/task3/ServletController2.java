package servlets.task3;

import classes.task1.Item;
import classes.task3.Artist;
import classes.task3.MySong;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

@WebServlet(name = "ServletController2", value = "/ServletController2")
public class ServletController2 extends HttpServlet
{
    private static final EntityManagerFactory m_factory = Persistence.createEntityManagerFactory("lab4");


    private static void addToDatabase(Object obj)
    {
        EntityManager manager = m_factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try
        {
            transaction.begin();

            manager.persist(obj);
            transaction.commit();
        }
        finally
        {
            if (transaction.isActive())
            { transaction.rollback(); }

            manager.close();
        }
    }


    private boolean deleteById(Class<?> type, int id)
    {
        EntityManager manager = m_factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        boolean res = false;

        try
        {
            transaction.begin();

            Object persistentInstance = manager.find(type, id);
            if (persistentInstance != null)
            {
                manager.remove(persistentInstance);
                res = true;
            }

            transaction.commit();
        }
        finally
        {
            if (transaction.isActive())
            { transaction.rollback(); }

            manager.close();
        }
        return res;
    }


    //looking for the actor using Criteria API
    private static Artist findArtist(String first_name, String second_name)
    {
/*        final EntityManager manager = m_factory.createEntityManager();
        CriteriaBuilder criteria__builder = manager.getCriteriaBuilder();

        CriteriaQuery<Artist> query = criteria__builder.createQuery(Artist.class);
        Root<Artist> root_artist = query.from(Artist.class);


        jakarta.persistence.criteria.Predicate predicate_first_name = criteria__builder.equal(root_artist.get("first_name"),first_name);
        jakarta.persistence.criteria.Predicate predicate_second_name = criteria__builder.equal(root_artist.get("second_name"),second_name);

        TypedQuery<Artist> artist = query.select(root_artist).where(criteria__builder.and(predicate_first_name,predicate_second_name)).get;*/
        //creating a query and getting results
        EntityManager manager = m_factory.createEntityManager();
        Artist artist = null;
        try
        {
            Query query = manager.createQuery("SELECT a FROM Artist a WHERE a.m_first_name LIKE '" + first_name + "' AND a.m_second_name LIKE '" + second_name + "'", Artist.class);
            artist = (Artist) query.getSingleResult();
        }
        catch (NoResultException exep)
        { exep.printStackTrace(); }
        finally
        { manager.close(); }

        return artist;
    }


    private static List<MySong> getSongs()
    {
        //creating a query and getting results
        EntityManager manager = m_factory.createEntityManager();
        Query query = manager.createQuery("SELECT a FROM MySong a", MySong.class);

        List<MySong> list = query.getResultList();
        manager.close();

        return list;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { doPost(request,response);}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");
        String chooser = request.getParameter("action");
        request.setAttribute("action",chooser);

        switch (chooser)
        {
            case "View" ->
            {
                List<MySong> list_songs = getSongs();

                //setting the list as an attribute
                request.setAttribute("list_songs",list_songs);
            }
            case "Add" ->
            {
                String input_title = request.getParameter("field_title");
                String input_artist_first_name = request.getParameter("field_first_name");
                String input_artist_second_name = request.getParameter("field_second_name");

                //trying to find the artist in the database
                Artist artist = findArtist(input_artist_first_name,input_artist_second_name);
                if (artist == null)
                {
                    //if there is no such artist, creating a new one and adding them to the database
                    log("There is no such artist");
                    Artist artist_created = new Artist(input_artist_first_name,input_artist_second_name);
                    addToDatabase(artist_created);

                    MySong song = new MySong(input_title,artist_created);
                    addToDatabase(song);
                }
                else
                {
                    //if there's such artist in the database, creating a song object using the artist
                    log("The artist has been found");
                    MySong song = new MySong(input_title,artist);
                    addToDatabase(song);
                }
            }
            case "Delete" ->
            {
                String input_id = request.getParameter("field_id");
                boolean flag = deleteById(MySong.class,Integer.parseInt(input_id));
                if (flag)
                { log("The song has been successfully deleted"); }
                else
                { log("deleteById has failed"); }
            }
        }


        //redirecting the request to show the results to the user
        request.getRequestDispatcher("ServletViewer2").forward(request,response);
    }
}
