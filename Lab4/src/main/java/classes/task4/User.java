package classes.task4;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int m_id;

    @Basic
    @Column(name = "login")
    private String m_login;

    @Basic
    @Column(name = "password_hashcode")
    private long m_password_hashcode;



    public long getPasswordHashcode() { return m_password_hashcode; }

    public void setPasswordHashcode(long m_password_hashcode) { this.m_password_hashcode = m_password_hashcode; }

    public int getId() { return m_id; }

    public void setId(int m_id) { this.m_id = m_id; }

    public String getLogin() { return m_login; }

    public void setLogin(String m_login) { this.m_login = m_login; }
}
