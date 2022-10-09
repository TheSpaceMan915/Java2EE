package lab2.task4;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "ValCurs")
public class ValCurs
{
    private List<Valute> m_list_valutes = new ArrayList<>();;
    private String m_date  = null;



    @XmlAttribute(name = "Date")
    public void setM_date(String m_date) { this.m_date = m_date; }

    @XmlElement(name = "Valute")
    public void setList_valutes(List<Valute> m_list_valutes) { this.m_list_valutes = m_list_valutes; }

    public static void readXML()
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            URL url = new URL("https://cbr.ru/scripts/XML_daily.asp?date_req=02/03/2002");
            ValCurs valutes = (ValCurs) unmarshaller.unmarshal(url);
        }
        catch (MalformedURLException exep)
        {
            exep.printStackTrace();
            JOptionPane.showMessageDialog(null,"URL error","Error",JOptionPane.ERROR_MESSAGE);
        }
        catch (JAXBException exep)
        {
            exep.printStackTrace();
            JOptionPane.showMessageDialog(null,"JAXBE error","Error",JOptionPane.ERROR_MESSAGE);
        }

    }
}
