package servlets.task1;

import classes.task1.Configuration;
import classes.task1.Item;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletController1", value = "/ServletController1")
public class ServletController1 extends HttpServlet
{
    private String m_name_db;
    private String m_url1;
    private String m_url2;
    private String m_user;
    private String m_password;
    private String sid;
    private File m_file_xml;


    private Configuration readXml()
    {
        Configuration temp = null;
        try
        {
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            temp = (Configuration) unmarshaller.unmarshal(m_file_xml);
        }
        catch (JAXBException exep)
        { exep.printStackTrace(); }

        assert temp != null;
        return temp;
    }


    @Override
    public void init()
    {
        //reading the xml file and initialising the strings
         m_file_xml= new File("/Users/jackyokov/IdeaProjects/Java2EE/Lab4/src/main/webapp/xml/task2_conf.xml");
        Configuration config = readXml();

        m_name_db = config.getNameDb();
        m_url1 = config.getUrl1();
        m_url2 = config.getUrl2();
        m_user = config.getUser();
        m_password = config.getPassword();
    }


    private Connection createDatabase()
    {
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(m_url2,m_user,m_password);

            //creating a database
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE DATABASE " + m_name_db);
            st.close();

            //creating the tables
            conn = DriverManager.getConnection(m_url2 + m_name_db,m_user,m_password);
            Statement st_new_db = conn.createStatement();
            st_new_db.executeUpdate(
                    "CREATE TABLE category" +
                    "(id integer PRIMARY KEY NOT NULL," +
                    "name text NOT NULL)");

            st_new_db.executeUpdate(
                    "CREATE TABLE item" +
                            "(id integer PRIMARY KEY NOT NULL," +
                            "category_id integer," +
                            "manufacturer_id integer," +
                            "name text NOT NULL)");

            st_new_db.executeUpdate(
                    "CREATE TABLE manufacturer" +
                            "(id integer PRIMARY KEY NOT NULL," +
                            "name text NOT NULL)");

            st_new_db.executeUpdate(
                    "CREATE TABLE warehouse" +
                            "(id integer PRIMARY KEY NOT NULL," +
                            "item_id integer," +
                            "amount integer NOT NULL)");

            st_new_db.close();
        }
        catch (SQLException exep)
        {
            exep.printStackTrace();
            log("createDatabase() has failed");
        }

        return conn;
    }


    //connecting to the database
    private Connection getConnection()
    {
        Connection connection = null;
        try
        { connection = DriverManager.getConnection(m_url1,m_user,m_password); }
        catch (SQLException exep)
        {
            exep.printStackTrace();
            log("getConnection() has failed. There's no such database");

            //creating a database
            connection = createDatabase();
        }
        return connection;
    }

    //reading the table of items and creating a list
    private List<Item> getItems()
    {
        List<Item> list_temp = new ArrayList<>();
        try (Connection connection = getConnection())
        {
            String query = "SELECT * FROM item";
            int id_temp, category_id_temp, manufacturer_id_temp;
            String name_temp = null;
            try (PreparedStatement statement = connection.prepareStatement(query); ResultSet result = statement.executeQuery())
            {
                //reading data from the table
                while (result.next())
                {
                    id_temp = result.getInt("id");
                    category_id_temp = result.getInt("category_id");
                    manufacturer_id_temp = result.getInt("manufacturer_id");
                    name_temp = result.getString("name");

                    //creating objects and adding them to the list
                    list_temp.add(new Item(id_temp,category_id_temp,manufacturer_id_temp,name_temp));
                }
            }
            catch (SQLException exep)
            { exep.printStackTrace(); }
        }
        catch (SQLException exep)
        { exep.printStackTrace(); }

        return list_temp;
    }


    private int addItemToDatabase(int category_id, int manufacturer_id, String name)
    {
        int id_generated = 0;
        String query = "INSERT INTO item(category_id, manufacturer_id, name) VALUES(?,?,?)";


        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS))
        {
            //setting the values
            statement.setInt(1,category_id);
            statement.setInt(2,manufacturer_id);
            statement.setString(3,name);

            //executing the query
            int affected_rows = statement.executeUpdate();
            if (affected_rows > 0)
            {
                try (ResultSet result = statement.getGeneratedKeys())
                {
                    if (result.next())
                    { id_generated = result.getInt(1); }
                }
                catch (SQLException exep)
                { exep.printStackTrace(); }
            }
        }
        catch (SQLException exep)
        { exep.printStackTrace(); }

        return id_generated;
    }


    private int updateItemName(int id, String name)
    {
        int affected_rows = 0;
        String query = "UPDATE item SET name = ? WHERE id = ?";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1,name);
            statement.setInt(2,id);

            affected_rows = statement.executeUpdate();
        }
        catch (SQLException exep)
        { exep.printStackTrace(); }

        return affected_rows;
    }


    private int deleteItem(int id)
    {
       int affected_rows = 0;
       String query = "DELETE FROM item WHERE id = ?";

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1,id);
            affected_rows = statement.executeUpdate();
        }
        catch (SQLException exep)
        { exep.printStackTrace(); }

        return affected_rows;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { doPost(request,response);}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        String chooser = request.getParameter("action");
        request.setAttribute("action",chooser);

        switch(chooser)
        {
            case "View" ->
            {
                List<Item> list_items = getItems();

                //redirecting the request to Viewer
                request.setAttribute("list_items",list_items);
                request.getRequestDispatcher("ServletViewer1").forward(request,response);
            }
            case "Add" ->
            {
                //getting the data from the form
                String input_category_id = request.getParameter("field_category_id");
                String input_manufacturer_id = request.getParameter("field_manufacturer_id");
                String input_name = request.getParameter("field_item_name");

                //adding a new row to the table of items
                int id_new_item = addItemToDatabase(Integer.parseInt(input_category_id), Integer.parseInt(input_manufacturer_id),input_name);

                //it prints it to the glassfish log (category: infos)
                //the log file is here: /Applications/glassfish6/glassfish/domains/domain1/logs/server.log
                if (id_new_item > 0)
                {
                    log("A new item has been added");
                    log("The id of the new item: " + id_new_item);
                }
            }
            case "Update" ->
            {
                //getting the data from the form
                String input_id = request.getParameter("field_id");
                String input_name = request.getParameter("field_item_name");

                //updating the item
                int number_affected_rows = updateItemName(Integer.parseInt(input_id),input_name);

                if (number_affected_rows > 0)
                {
                    log("A row has been updated");
                    log("The number of affected rows: " + number_affected_rows);
                }
            }
            case "Delete" ->
            {
                //getting the data from the form
                String input_id = request.getParameter("field_id");

                //deleting the item
                int number_affected_rows = deleteItem(Integer.parseInt(input_id));

                if (number_affected_rows > 0)
                {
                    log("An item has been deleted");
                    log("The number of affected rows: " + number_affected_rows);
                }
            }
        }

        //redirecting the request to show the results to the user
        request.getRequestDispatcher("ServletViewer1").forward(request,response);
    }
}
