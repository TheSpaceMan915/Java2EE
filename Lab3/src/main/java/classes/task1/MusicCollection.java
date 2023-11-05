package classes.task1;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Music")
public class MusicCollection implements Serializable
{
    private List<MySong> m_list_songs = new ArrayList<>();

    public MusicCollection() {
        
    }

    public List<MySong> getListSongs() { return m_list_songs; }

    @XmlElement(name = "Song")
    public void setListSongs(List<MySong> m_list_songs) { this.m_list_songs = m_list_songs; }
}
