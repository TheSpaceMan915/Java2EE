package classes.task3;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;


@Entity
@Table(name = "song", schema = "public", catalog = "lab4_task3")
public class MySong
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int m_id;

    @Basic
    @Column(name = "title")
    private String m_title;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = true)
    private Artist m_artist;

    public MySong() {}

    public MySong(String title, Artist artist)
    {
        m_title = title;
        m_artist = artist;
    }

    public int getId() { return m_id; }

    public void setId(int m_id) { this.m_id = m_id; }

    public String getTitle() { return m_title; }

    public void setTitle(String m_title) { this.m_title = m_title; }

    public Artist getArtist() { return m_artist; }

    public void setArtist(Artist m_artist) { this.m_artist = m_artist; }
}
