package task4_classes.currency_period;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

//the name of the class may differ with the name of the root element
@XmlRootElement(name = "ValCurs")
public class ValCursPeriod
{
    @XmlAttribute(name = "ID")
    private String m_id;

    @XmlAttribute(name = "DateRange1")
    private String m_date1;

    @XmlAttribute(name = "DateRange2")
    private String m_date2;

    @XmlAttribute(name = "name")
    private String m_name;

    private double price;

    @XmlElement(name = "Record")
    private final List<ValutePeriod> m_list_valutes = new ArrayList<>();

    public ValCursPeriod() {}

    public List<ValutePeriod> getListValutes() { return m_list_valutes; }
}
