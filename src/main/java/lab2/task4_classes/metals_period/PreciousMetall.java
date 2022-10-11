package lab2.task4_classes.metals_period;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Record")
public class PreciousMetall
{
    @XmlAttribute(name = "Date")
    private String m_date;

    @XmlAttribute(name = "Code")
    private String m_code;

    @XmlElement(name = "Buy")
    private String m_buy_price;

    @XmlElement(name = "Sell")
    private String m_sell_price;


    public PreciousMetall() {}

    public String getSellPrice() { return m_sell_price; }

    public String getBuyPrice() { return m_buy_price; }

    public String getCode() { return m_code; }

    public String getDate() { return m_date; }

    @Override
    public String toString()
    {
        String res = switch(m_code) {
            case "1" -> "Gold";
            case "2" -> "Silver";
            case "3" -> "Platinum";
            case "4" -> "Palladium";
            default -> "";
        };

        return res;
    }

}
