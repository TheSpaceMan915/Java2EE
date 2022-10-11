package lab2.task4_classes.currency_one_day;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Valute")
public class Valute
{
    @XmlElement(name = "NumCode")
    private String m_num_code;

    @XmlElement(name = "CharCode")
    private String m_char_code;

    @XmlElement(name = "Nominal")
    private String m_nominal;

    @XmlElement(name = "Name")
    private String m_name;

    @XmlElement(name = "Value")
    private String m_value;

    public Valute() {}

    public String getNumCode() { return m_num_code; }
    public String getCharCode() { return m_char_code; }
    public String getName() { return m_name; }
    public String getValue() { return m_value; }
}
