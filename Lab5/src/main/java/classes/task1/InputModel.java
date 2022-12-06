package classes.task1;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;


@Named
@RequestScoped
public class InputModel implements Serializable
{
    private String m_user_input;
    private int m_counter = 0;


    public String getUserInput() { return m_user_input; }

    public void setUserInput(String m_user_input) { this.m_user_input = m_user_input; }

    public void inputCounter() { m_counter++; }
}
