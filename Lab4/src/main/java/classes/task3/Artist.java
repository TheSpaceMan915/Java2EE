package classes.task3;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Artist
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int m_id;

    @Basic
    @Column(name = "first_name")
    private String m_first_name;

    @Basic
    @Column(name = "second_name")
    private String m_second_name;

    private String nickname;


    @OneToMany(mappedBy = "m_artist", cascade = CascadeType.ALL)
    private Set<MySong> m_set_songs;


    public Artist(String first_name, String second_name)
    {
        m_first_name = first_name;
        m_second_name = second_name;
    }

    public Artist() {}

    public int getId() {
        return m_id;
    }

    public void setId(int id) {
        this.m_id = id;
    }

    public String getFirstName() {
        return m_first_name;
    }

    public void setFirstName(String firstName) {
        this.m_first_name = firstName;
    }

    public String getSecondName() {
        return m_second_name;
    }

    public void setSecondName(String secondName) {
        this.m_second_name = secondName;
    }

    public Set<MySong> getSongs() { return m_set_songs; }

    public void setSongs(Set<MySong> m_set_songs) { this.m_set_songs = m_set_songs; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (m_id != artist.m_id) return false;
        if (m_first_name != null ? !m_first_name.equals(artist.m_first_name) : artist.m_first_name != null) return false;
        if (m_second_name != null ? !m_second_name.equals(artist.m_second_name) : artist.m_second_name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = m_id;
        result = 31 * result + (m_first_name != null ? m_first_name.hashCode() : 0);
        result = 31 * result + (m_second_name != null ? m_second_name.hashCode() : 0);
        return result;
    }
}
