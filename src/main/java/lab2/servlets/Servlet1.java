package lab2.servlets;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//value is the URL pattern of the servlet
@WebServlet(name="Authentication", value = "/task1")
public class Servlet1 extends HttpServlet
{
    private static PrintWriter m_writer;
    private static String m_login;
    private static String m_password;
    private static String m_date;
    private static int m_counter;
    private List<String> m_arr_str;

    private String getTextValue(String def, Element doc, String tag)
    {
        String value = def;
        NodeList nl;
        nl = doc.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            value = nl.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }

    public boolean readXML(String xml)
    {
        Document dom;
        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            dom = db.parse(xml);

            Element doc = dom.getDocumentElement();

            m_login = getTextValue(m_login, doc, "login");
            if (m_login != null)
            {
                if (!m_login.isEmpty())
                { m_arr_str.add(m_login); }
            }
            m_password = getTextValue(m_password, doc, "password");
            if (m_password != null)
            {
                if (!m_password.isEmpty())
                { m_arr_str.add(m_password); }
            }

            return true;
        }
        catch (ParserConfigurationException pce)
        { System.out.println(pce.getMessage()); }
        catch (SAXException se)
        { System.out.println(se.getMessage()); }
        catch (IOException ioe)
        { System.err.println(ioe.getMessage()); }

        return false;
    }

    public static void printMessage(String str)
    {
        m_writer.println("<html>");
        m_writer.println("<body>");

        m_writer.println("<dialog open>");
        m_writer.println(str);
        m_writer.println("</dialog>");


        m_writer.println("</html>");
        m_writer.println("</body>");
    }

    public void init()
    {
        //initialising the members
        m_counter = 0;
        m_arr_str = new ArrayList<>();

        Date temp = new Date();
        SimpleDateFormat date_format = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
        date_format.setTimeZone(TimeZone.getDefault());
        m_date = date_format.format(temp);

        //don't forget to change the paths to my .xml and .dtd (in servlet1_data_format.dtd) files
        String path_windows = "C:\\Users\\nikit\\IdeaProjects\\Java2EE\\src\\main\\webapp\\files\\xml\\servlet1_data.xml";
        //String path = "/Users/jackyokov/IdeaProjects/Java2EE/src/main/webapp/files/xml/servlet1_data.xml";
        boolean res = readXML(path_windows);
        System.out.println(res);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        response.setContentType("text/html");

        //get the user data from the request
        String input_login = request.getParameter("field_login");
        String input_password = request.getParameter("field_password");

        try
        {
            m_writer = response.getWriter();
            if (m_counter < 3)
            {
                if (input_login.equals(m_login))
                {
                    if (input_password.equals(m_password))
                    { printMessage("Success!\n" + m_date); }
                    else
                    {
                        m_counter++;
                        printMessage("The password is incorrect\n" + "Try number " + m_counter);
                    }
                }
                else
                {
                    m_counter++;
                    printMessage("The login is incorrect\n" + "Try number " + m_counter);
                }
            }
            else
            {
                printMessage("You shall not paaaaaaass");
            }
        }
        catch(IOException exep)
        {
            System.err.println("There's a problem with the writer");
            exep.printStackTrace();
        }


    }

    public void destroy() {
    }
}