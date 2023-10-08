package servlets.task1;

import classes.task1.Item;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ServletViewer1", value = "/ServletViewer1")
public class ServletViewer1 extends HttpServlet
{
    private PrintWriter m_writer;
    
    private void showList(List<Item> list)
    {
        m_writer.println("<html>");
        m_writer.println("<title>List of goods</title>");
        m_writer.println("<body>");
        m_writer.println("<h1 style=color:darkviolet;text-align:center>Your goods</h1>");
        m_writer.println("<table style=margin-left:auto;margin-right:auto>");
        m_writer.println("<tr>");
        m_writer.println("<th>Id</th>");
        m_writer.println("<th>Name</th>");
        m_writer.println("<th>Category Id</th>");
        m_writer.println("<th>Manufacturer Id</th>");
        m_writer.println("</tr>");

        for (Item item : list)
        {
            m_writer.println("<tr>");
            m_writer.println("<td style=text-align:center>" + item.getId() + "</td>");
            m_writer.println("<td style=text-align:center>" + item.getName() + "</td>");
            m_writer.println("<td style=text-align:center>" + item.categoryIdToString() + "</td>");
            m_writer.println("<td style=text-align:center>" + item.manufacturerIdToString() + "</td>");
            m_writer.println("</tr>");
        }

        m_writer.println("</table>");
        m_writer.println("<br><br>");
        m_writer.println("<a href = http://localhost:8080/Lab4-1.0-SNAPSHOT/>Go back</a>");
        m_writer.println("</html>");
        m_writer.println("</body>");
    }


    private void printMessage(String message)
    {
        m_writer.println("<html>");
        m_writer.println("<title>Message</title>");
        m_writer.println("<body>");

        m_writer.println("<p>" + message + "</p>");

        m_writer.println("<a href = http://localhost:8080/Lab4-1.0-SNAPSHOT/>Go back</a>");
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

        String chooser = request.getParameter("action");
        switch(chooser)
        {
            case "View" ->
            {
                List<Item> list_items = (List<Item>) request.getAttribute("list_items");
                showList(list_items);
            }

            case "Add" -> printMessage("A new item has been added");

            case "Update" -> printMessage("A row has been updated");

            case "Delete" -> printMessage("An item has been deleted");
        }
    }
}
