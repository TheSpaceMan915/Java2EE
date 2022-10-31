package servlets.task1;
import classes.task1.MySong;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//sends the data to the html page
@WebServlet(name = "Servlet2", value = "/ServletViewer")
public class ServletViewer extends HttpServlet
{
    private PrintWriter m_writer;


    public void showList(List<MySong> list)
    {
        m_writer.println("<html>");
        m_writer.println("<title>Music List</title>");
        m_writer.println("<body>");
        m_writer.println("<h1 style=color:darkviolet;text-align:center>Your music collection</h1>");
        m_writer.println("<table style=margin-left:auto;margin-right:auto>");
        m_writer.println("<tr>");
        m_writer.println("<th>Id</th>");
        m_writer.println("<th>Artist</th>");
        m_writer.println("<th>Title</th>");
        m_writer.println("</tr>");

        for (MySong song : list)
        {
            m_writer.println("<tr>");
            m_writer.println("<td>" + song.getId() + "</td>");
            m_writer.println("<td>" + song.getArtist() + "</td>");
            m_writer.println("<td>" + song.getTitle() + "</td>");
            m_writer.println("</tr>");
        }

        m_writer.println("</table>");
        m_writer.println("<br><br>");
        m_writer.println("<a href = http://localhost:8080/Lab3-1.0-SNAPSHOT/>Go back</a>");
        m_writer.println("</html>");
        m_writer.println("</body>");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { doPost(request,response); }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        m_writer = response.getWriter();

        //showing the list
        List<MySong> list_songs = (List<MySong>) request.getAttribute("list_songs");
        showList(list_songs);
    }
}
