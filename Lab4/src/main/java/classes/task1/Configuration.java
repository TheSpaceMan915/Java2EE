package classes.task1;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Config")
public class Configuration
{
    private String m_name_db;
    private String m_url1;

    private String m_url2;
    private String m_user;
    private String m_password;
    private String m_code;


    public Configuration() {}

    public String getNameDb() { return m_name_db; }

    @XmlAttribute(name = "name_db")
    public void setNameDb(String m_name_db) { this.m_name_db = m_name_db; }

    public String getUrl1() { return m_url1; }

    @XmlElement(name = "url1")
    public void setUrl1(String m_url) { this.m_url1 = m_url; }

    public String getUrl2() { return m_url2; }

    @XmlElement(name = "url2")
    public void setUrl2(String m_url2) { this.m_url2 = m_url2; }

    public String getUser() { return m_user; }

    @XmlElement(name ="user")
    public void setUser(String m_user) { this.m_user = m_user; }

    public String getPassword() { return m_password; }

    @XmlElement(name = "password")
    public void setPassword(String m_password) { this.m_password = m_password; }

}
