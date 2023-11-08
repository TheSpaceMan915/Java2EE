package classes.task1;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;


@XmlRootElement(name = "Song")
@XmlType(propOrder = {"id","title","artist"})
public class MySong implements Serializable
{
    private String m_id;
    private String m_title;
    private String m_artist;
    private double length;

    public MySong() {}

    public MySong(String id, String title, String artist)
    {
        m_id = id;
        m_title = title;
        m_artist = artist;
    }

    public String getId() { return m_id; }

    @XmlAttribute(name = "id")
    public void setId(String m_id) { this.m_id = m_id; }

    public String getTitle() { return m_title; }

    @XmlElement(name = "title")
    public void setTitle(String m_title) { this.m_title = m_title; }

    public String getArtist() { return m_artist; }

    @XmlElement(name = "artist")
    public void setArtist(String m_artist) { this.m_artist = m_artist; }
}
