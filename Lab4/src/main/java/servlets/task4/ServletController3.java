package servlets.task4;

import classes.task3.Artist;
import classes.task3.MySong;
import classes.task4.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletController3", value = "/ServletController3")
public class ServletController3 extends HttpServlet
{
    private String logMess = "Servlet got the message";
    
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
                List<MySong> list_songs = DatabaseManager.getSongs();

                //setting the list as an attribute
                request.setAttribute("list_songs",list_songs);
            }

            case "Add" ->
            {
                String input_title = request.getParameter("field_title");
                String input_artist_first_name = request.getParameter("field_first_name");
                String input_artist_second_name = request.getParameter("field_second_name");

                //trying to find the artist in the database
                Artist artist = DatabaseManager.getArtist(input_artist_first_name,input_artist_second_name);
                if (artist == null)
                {
                    //if there is no such artist, creating a new one and adding them to the database
                    log("There is no such artist");
                    Artist artist_created = new Artist(input_artist_first_name,input_artist_second_name);
                    DatabaseManager.addToDatabase(artist_created);

                    MySong song = new MySong(input_title,artist_created);
                    DatabaseManager.addToDatabase(song);
                }
                else
                {
                    //if there's such artist in the database, creating a song object using the artist
                    log("The artist has been found");
                    MySong song = new MySong(input_title,artist);
                    DatabaseManager.addToDatabase(song);
                }
            }

            case "Delete" ->
            {
                String input_id = request.getParameter("field_id");
                boolean flag = DatabaseManager.deleteById(MySong.class,Integer.parseInt(input_id));
                if (flag)
                { log("The song has been successfully deleted"); }
                else
                { log("deleteById has failed"); }
            }

            case "Change login" ->
            {
                int input_user_id = Integer.parseInt(request.getParameter("field_user_id"));
                String input_new_login = request.getParameter("field_login");

                DatabaseManager.updateUserLogin(input_user_id,input_new_login);
            }
        }


        //redirecting the request to show the results to the user
        request.getRequestDispatcher("ServletViewer3").forward(request,response);
    }
}
