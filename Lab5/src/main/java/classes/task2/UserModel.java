package classes.task2;
import classes.task1.InputModel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@RequestScoped
public class UserModel  implements Serializable
{
    private String m_user_login;

    private String m_user_password;

    private String m_user_email;

    private String m_user_phone_number;

    private final List<User> m_list_users = new ArrayList<>();
    {
        //filling the list with users
        m_list_users.add(new User("Darth Vader", -865508475,"dark_side@hotmail.com","+7-956-321-835"));
        m_list_users.add(new User("Chewbacca",-146492424,"urrryyyaaa@galaxy.com","8-789-246-498"));
        m_list_users.add(new User("Luke Skywalker",46944573,"JediMaster@stars.com","+7945672371"));
    }

/*    Login: Darth Vader
      Password: urov!sd3

      Login: Chewbacca
      Password: 9534?Pure2

      Login: Luke Skywalker
      Password: Tpre32!P
*/


    //signing up a new user
    public void signUpUser()
    {
        m_list_users.add(new User(m_user_login,m_user_password.hashCode(),m_user_email,m_user_phone_number));
        System.out.println("A new user has been signed up");
    }


    //checking if the user is signed up
    public boolean isSignedUp(String user_login, String user_password)
    {
        boolean res = false;
        for (User user : m_list_users)
        {
            if (user.m_login().equals(user_login))
            {
                if (user.m_hash_password() == user_password.hashCode())
                { res = true; }
            }
        }

        return res;
    }


    //redirecting the user to one of the pages
    public String redirectUser()
    {
        String page = "";
        if (isSignedUp(m_user_login,m_user_password))
        { page = "/jsf/task2/signed_up.xhtml"; }
        else
        { page = "/jsf/task2/not_signed_up.xhtml"; }

        return page;
    }

    public String getUserPassword() { return m_user_password; }

    public void setUserPassword(String m_user_password) { this.m_user_password = m_user_password; }

    public String getUserLogin() { return m_user_login; }

    public void setUserLogin(String m_user_login) { this.m_user_login = m_user_login; }

    public String getUserPhone() { return m_user_phone_number; }

    public void setUserPhone(String m_user_phone) { this.m_user_phone_number = m_user_phone; }

    public String getUserEmail() { return m_user_email; }

    public void setUserEmail(String m_user_email) { this.m_user_email = m_user_email; }
}
