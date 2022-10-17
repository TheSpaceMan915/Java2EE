package servlets;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Session" ,value = "/task2")
public class Servlet2 extends HttpServlet {
    private static PrintWriter m_writer;
    private static String m_user_id;
    private static String m_user_key;
    private static String m_date;
    private static int m_counter;


    public void init()
    {
        Date temp = new Date();
        SimpleDateFormat date_format = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
        date_format.setTimeZone(TimeZone.getDefault());
        m_date = date_format.format(temp);

        //m_user_id = "EeeeeRock2007";
        //m_user_key = "Summer";
        m_counter = 0;
    }

    public static void printMessage(String str)
    {
        m_writer.println("<html>");
        m_writer.println("<body>");

        m_writer.println("<p>");
        m_writer.println(str);
        m_writer.println("</p>");


        m_writer.println("</html>");
        m_writer.println("</body>");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //get the session
        HttpSession session = request.getSession(true);
        m_counter++;

        //get the data
        String id_session = session.getId();
        Date time_creation = new Date(session.getCreationTime());

        response.setContentType("text/html");
        m_writer = response.getWriter();


        String input_number = request.getParameter("field_number");

        switch (input_number)
        {
            case "1" -> printMessage("Id: " + id_session + " Creation time: " + time_creation);

            case "2" -> printMessage("Current server time: " + m_date);

            case "3" -> printMessage("The amount of requests: " + m_counter);
        }

        //write a switcher for the input number
    }

    public void destroy() {
    }
}