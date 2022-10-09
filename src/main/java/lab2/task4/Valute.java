package lab2.task4;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Valute")
public class Valute
{
    private String m_id_valute;
    private String m_num_code;
    private String m_char_code;
    private String m_nominal;
    private String m_name;
    private String m_value;


    //public String getId_valute() { return m_id_valute; }

    @XmlAttribute(name = "ID")
    public void setId_valute(String m_id_valute) { this.m_id_valute = m_id_valute; }

    //public String getNum_code() { return m_num_code; }

    @XmlElement(name = "NumCode")
    public void setNum_code(String m_num_code) { this.m_num_code = m_num_code; }

    //public String getChar_code() { return m_char_code; }

    @XmlElement(name = "CharCode")
    public void setChar_code(String m_char_code) { this.m_char_code = m_char_code; }

    //public String getNominal() { return m_nominal; }

    @XmlElement(name = "Nominal")
    public void setNominal(String m_nominal) { this.m_nominal = m_nominal; }

    //public String getName() { return m_name; }

    @XmlElement(name = "Name")
    public void setName(String m_name) { this.m_name = m_name; }

    //public String getValue() { return m_value; }

    @XmlElement(name = "Value")
    public void setValue(String m_value) { this.m_value = m_value; }
}
