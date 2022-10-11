package lab2.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lab2.task4_classes.currency_one_day.ValCurs;
import lab2.task4_classes.currency_one_day.Valute;
import lab2.task4_classes.currency_period.ValCursPeriod;
import lab2.task4_classes.currency_period.ValutePeriod;
import lab2.task4_classes.metals_period.MetallCurs;
import lab2.task4_classes.metals_period.PreciousMetall;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

@WebServlet(name = "Prices", value = "/task5")
public class Servlet5 extends HttpServlet
{
    private PrintWriter m_writer;

    public ValCurs getCurrencyOneDay(String date)
    {

        ValCurs temp = null;

        //setting url
        String str_url = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;
        try
        {
            //unmarshalling the data
            URL url = new URL(str_url);
            JAXBContext context = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            temp = (ValCurs) unmarshaller.unmarshal(url);

        }
        catch (IOException exep)
        { exep.printStackTrace(); }
        catch (JAXBException exep)
        {
            exep.printStackTrace();
            JOptionPane.showMessageDialog(null,"JAXBE error","Error",JOptionPane.ERROR_MESSAGE);
        }

        return temp;
    }

    public void showCurrencyOneDay(ValCurs currency)
    {
        //printing currency info
        m_writer.println("<html>");
        m_writer.println("<body>");

        m_writer.println("<table style=margin-left:auto;margin-right:auto>");
        m_writer.println("<tr>");
        m_writer.println("<th>Name</th>");
        m_writer.println("<th>Value</th>");
        m_writer.println("</tr>");

        //printing the info
        String name, value;

        for (Valute temp : currency.getListValutes())
        {
            name = temp.getName();
            value = temp.getValue();
            m_writer.println("<tr>");
            m_writer.println("<td>" + name + "</td>");
            m_writer.println("<td>" + value + "</td>");
            m_writer.println("</tr>");
        }

        m_writer.println("</table>");
        m_writer.println("</html>");
        m_writer.println("</body>");
    }

    public ValCursPeriod getCurrencyPeriod(String date1, String date2, String id)
    {
        ValCursPeriod temp = null;

        //setting url
        //String  str = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=02/03/2001&date_req2=14/03/2001&VAL_NM_RQ=R01235";
        String str_url = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=" + date1 + "&date_req2=" + date2 + "&VAL_NM_RQ=" + id;
        try
        {
            //unmarshalling the data
            URL url = new URL(str_url);

            //CHANGE THE CONTEXT FOR EVERY NEW DATA TYPE!!!
            JAXBContext context = JAXBContext.newInstance(ValCursPeriod.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            temp = (ValCursPeriod) unmarshaller.unmarshal(url);

        }
        catch (IOException exep)
        { exep.printStackTrace(); }
        catch (JAXBException exep)
        {
            exep.printStackTrace();
            JOptionPane.showMessageDialog(null,"JAXBE error","Error",JOptionPane.ERROR_MESSAGE);
        }

        return temp;
    }

    public void showCurrencyPeriod(ValCursPeriod currency_period)
    {
        //printing currency info
        m_writer.println("<html>");
        m_writer.println("<body>");

        m_writer.println("<table style=margin-left:auto;margin-right:auto>");
        m_writer.println("<tr>");
        m_writer.println("<th>Date</th>");
        m_writer.println("<th>Value</th>");
        m_writer.println("</tr>");

        //printing the info
        String date, value;

        for (ValutePeriod temp : currency_period.getListValutes())
        {
            date = temp.getDate();
            value = temp.getValue();
            m_writer.println("<tr>");
            m_writer.println("<td>" + date + "</td>");
            m_writer.println("<td>" + value + "</td>");
            m_writer.println("</tr>");
        }

        m_writer.println("</table>");
        m_writer.println("</html>");
        m_writer.println("</body>");
    }


    public MetallCurs getMetallsPeriod(String date1, String date2)
    {
        MetallCurs temp = null;

        //setting url
        //String  str = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=02/03/2001&date_req2=14/03/2001&VAL_NM_RQ=R01235";
        String str_url = "http://www.cbr.ru/scripts/xml_metall.asp?date_req1=" + date1 + "&date_req2=" + date2;
        try
        {
            //unmarshalling the data
            URL url = new URL(str_url);

            //CHANGE THE CONTEXT FOR EVERY NEW DATA TYPE!!!
            JAXBContext context = JAXBContext.newInstance(MetallCurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            temp = (MetallCurs) unmarshaller.unmarshal(url);

        }
        catch (IOException exep)
        { exep.printStackTrace(); }
        catch (JAXBException exep)
        {
            exep.printStackTrace();
            JOptionPane.showMessageDialog(null,"JAXBE error","Error",JOptionPane.ERROR_MESSAGE);
        }

        return temp;
    }

    public void showMetallPeriod(MetallCurs metalls)
    {
        //printing currency info
        m_writer.println("<html>");
        m_writer.println("<body>");

        m_writer.println("<table style=margin-left:auto;margin-right:auto>");
        m_writer.println("<tr>");
        m_writer.println("<th>Metall</th>");
        m_writer.println("<th>Buy price</th>");
        m_writer.println("<th>Sell price</th>");
        m_writer.println("<th>Date</th>");
        m_writer.println("</tr>");

        //printing the info
        for (PreciousMetall temp : metalls.getListMetalls())
        {
            m_writer.println("<tr>");
            m_writer.println("<td>" + temp.toString() + "</td>");
            m_writer.println("<td>" + temp.getBuyPrice() + "</td>");
            m_writer.println("<td>" + temp.getSellPrice() + "</td>");
            m_writer.println("<td>" + temp.getDate() + "</td>");
            m_writer.println("</tr>");
        }

        m_writer.println("</table>");
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

        //get the user data from the request
        String choice = request.getParameter("chooser");
        String input_date1 = request.getParameter("field_date1");
        String input_date2 = request.getParameter("field_date2");
        String input_id = request.getParameter("field_id");


        //choosing what html page to show
        if (choice.equals("Currency"))
        {
            if (input_date2.equals(""))
            {
                ValCurs valutes = getCurrencyOneDay(input_date1);
            }
            else
            {
                ValCursPeriod valutes_period = getCurrencyPeriod(input_date1,input_date2,input_id);

                request.setAttribute("valutes",valutes_period.getListValutes());
                String path = "/currency_period.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request,response);
            }
        }
        else
        {
            MetallCurs metalls_period = getMetallsPeriod(input_date1,input_date2);
            showMetallPeriod(metalls_period);
        }

    }
}
