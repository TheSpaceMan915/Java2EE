package servlets.task4;
import classes.task4.Admin;
import classes.task4.DatabaseManager;
import classes.task4.OrdinaryUser;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


@WebServlet(name = "ServletAuthenticator", value = "/ServletAuthenticator")
public class ServletAuthenticator extends HttpServlet
{
    /*
    The user
    Password: 9383!Pof
    Hashcode: 225384315

    The admin
    Password: Uirf@34
    Hashcode: 1242845625
    */
    
    private boolean authenticateUser(String login, long hashcode)
    {
        //getting the user
        OrdinaryUser user = DatabaseManager.getUser(login,hashcode);
        boolean res = false;

        if (user != null)
        { res = true; }

        return res;
    }


    private boolean authenticateAdmin(String login, long hashcode)
    {
        //getting the admin
        Admin admin = DatabaseManager.getAdmin(login,hashcode);
        boolean res = false;

        if (admin != null)
        { res = true; }

        return res;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { doPost(request,response); }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        //getting the data from user
        String input_login = request.getParameter("field_login");
        String input_password = request.getParameter("field_password");
        long password_hashcode = input_password.hashCode();


        if (authenticateUser(input_login,password_hashcode))
        {
            //redirecting the request to the user's menu
            request.getRequestDispatcher("jsp/task4_user_menu.jsp").forward(request,response);
            log("The user has been authenticated");
        }
        else if (authenticateAdmin(input_login,password_hashcode))
        {
            //redirecting the request to the admin's menu
            request.getRequestDispatcher("jsp/task4_admin_menu.jsp").forward(request,response);
            log("The admin has been authenticated");
        }
        else
        {
            response.getWriter().println("Authentication error");
            log("Authentication has failed");
        }
    }
}
