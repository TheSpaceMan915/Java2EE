package lab2;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

@WebServlet(name = "Prices", value = "/task4")
public class Servlet4 extends HttpServlet
{
    private PrintWriter m_writer;


    public static Document readXML(String xml)
    {
        Document dom = null;

        // Make an  instance of the DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            URL url = new URL(xml);

            //getting InputStream from the URL
            final InputStream stream = url.openStream();


            // use the factory to take an instance of the document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // parse using the builder to get the DOM mapping of the
            // XML file
            dom = db.parse(stream);

            Element doc = dom.getDocumentElement();
            doc.normalize();
        }
        catch (MalformedURLException exep)
        { exep.printStackTrace(); }
        catch (ParserConfigurationException pce)
        { System.out.println(pce.getMessage()); }
        catch (SAXException se)
        { System.out.println(se.getMessage()); }
        catch (IOException ioe)
        { System.err.println(ioe.getMessage()); }

        //return the document containing all the nodes
        return dom;
    }

    public void showCurrencyOneDay(String date)
    {
        String currency_xml = "https://cbr.ru/scripts/XML_daily.asp?date_req=03/03/2002";
        Document document = readXML(currency_xml);

        NodeList list_nodes = document.getElementsByTagName("Valute");
        NodeList list_child_nodes = null;
        String name = null;
        String value = null;

        //printing currency info
        m_writer.println("<html>");
        m_writer.println("<body>");

        m_writer.println("<table>");
        m_writer.println("<tr>");
        m_writer.println("<th>Name</th>");
        m_writer.println("<th>Value</th>");
        m_writer.println("</tr>");

        for (int i = 0; i < list_nodes.getLength(); i++)
        {
            //getting the list of child notes
            list_child_nodes = list_nodes.item(i).getChildNodes();
            name = list_child_nodes.item(i).getNodeName();
            value = list_child_nodes.item(i).getNodeValue();

            m_writer.println("<tr>");
            m_writer.println("<td>" + name + "</td>");
            m_writer.println("<td>" + value + "</td>");
            m_writer.println("</tr>");
        }

        m_writer.println("</table>");
        m_writer.println("</html>");
        m_writer.println("</body>");

    }

    public static void showCurrencyPeriod()
    {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { doPost(request,response); }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        m_writer = response.getWriter();

        //get the user data from the request
        String choice = request.getParameter("chooser");
        String input_date1 = request.getParameter("field_date1");
        String input_date2 = request.getParameter("field_date2");

        //choosing what html page to show
        if (choice.equals("Currency"))
        {
            if (input_date2.equals(""))
            { showCurrencyOneDay(input_date1); }
        }

    }
}
