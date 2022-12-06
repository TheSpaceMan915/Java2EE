package classes.task2;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.validation.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@FacesValidator("email_validator")
public class EmailValidator implements Validator<String>
{
    private final int m_min_length = 5;

    private final int m_max_length = 30;


    @Override
    public void validate(FacesContext context, UIComponent component, String str) throws ValidationException
    {
        if (str.length() < m_min_length)
        { throw new ValidatorException(new FacesMessage("The e-mail must have more than " + m_min_length + " characters")); }

        if (str.length() > m_max_length)
        { throw new ValidatorException(new FacesMessage("The e-mail must have less than " + m_max_length + " characters")); }


        Pattern patter_obj = Pattern.compile("\\w+@\\w+\\.\\w+");
        Matcher matcher_obj = patter_obj.matcher(str);
        boolean res = matcher_obj.matches();

        if (!res)
        { throw new ValidatorException(new FacesMessage("The e-mail is invalid")); }
    }
}
