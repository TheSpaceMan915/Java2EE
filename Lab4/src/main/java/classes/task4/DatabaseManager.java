package classes.task4;
import classes.task3.Artist;
import classes.task3.MySong;
import jakarta.persistence.*;
import java.util.List;


public class DatabaseManager
{
    private static final EntityManagerFactory m_factory = Persistence.createEntityManagerFactory("lab4");

    public static void addToDatabase(Object obj)
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


    public static boolean deleteById(Class<?> type, int id)
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


    public static void updateUserLogin(int user_id, String new_login)
    {
        EntityManager manager = m_factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try
        {
            //getting the user object from the database
            OrdinaryUser user_obj = manager.find(OrdinaryUser.class,user_id);

            transaction.begin();

            //changing the login
            user_obj.setLogin(new_login);
            manager.merge(user_obj);

            transaction.commit();
        }
        finally
        {
            if (transaction.isActive())
            { transaction.rollback(); }

            manager.close();
        }
    }


    public static List<MySong> getSongs()
    {
        //creating a query and getting results
        EntityManager manager = m_factory.createEntityManager();
        Query query = manager.createQuery("SELECT a FROM MySong a", MySong.class);

        List<MySong> list = query.getResultList();
        manager.close();

        return list;
    }


    public static Artist getArtist(String first_name, String second_name)
    {
/*
        //looking for the actor using Criteria API
        final EntityManager manager = m_factory.createEntityManager();
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


    public static OrdinaryUser getUser(String login, long password_hashcode)
    {
        //creating a query and getting results
        EntityManager manager = m_factory.createEntityManager();
        OrdinaryUser user = null;
        try
        {
            Query query = manager.createQuery("SELECT a FROM OrdinaryUser a WHERE a.m_login LIKE '" + login + "' AND a.m_password_hashcode=" + password_hashcode, OrdinaryUser.class);
            user = (OrdinaryUser) query.getSingleResult();
        }
        catch (NoResultException exep)
        { exep.printStackTrace(); }
        finally
        { manager.close(); }

        return user;
    }


    public static Admin getAdmin(String login, long password_hashcode)
    {
        //creating a query and getting results
        EntityManager manager = m_factory.createEntityManager();
        Admin admin = null;
        try
        {
            Query query = manager.createQuery("SELECT a FROM Admin a WHERE a.m_login LIKE '" + login + "' AND a.m_password_hashcode=" + password_hashcode, Admin.class);
            admin = (Admin) query.getSingleResult();
        }
        catch (NoResultException exep)
        { exep.printStackTrace(); }
        finally
        { manager.close(); }

        return admin;
    }
}
