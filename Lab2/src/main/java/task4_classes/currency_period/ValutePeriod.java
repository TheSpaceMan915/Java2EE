package task4_classes.currency_period;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Record")
public class ValutePeriod
{
    @XmlAttribute(name = "Date")
    private String m_date;

    @XmlAttribute(name = "Id")
    private String m_id;

    @XmlElement(name = "Nominal")
    private String m_nominal;

    @XmlElement(name = "Value")
    private String m_value;


    public ValutePeriod() {}


    public String getDate() { return m_date; }

    public String getId() { return m_id; }

    public String getNominal() { return m_nominal; }

    public String getValue() { return m_value; }
}
