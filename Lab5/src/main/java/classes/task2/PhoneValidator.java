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


@FacesValidator("phone_number_validator")
public class PhoneValidator implements Validator<String>
{
    private final int m_min_length = 7;

    private final int m_max_length = 20;


    @Override
    public void validate(FacesContext context, UIComponent component, String str) throws ValidationException
    {
        if (str.length() < m_min_length)
        { throw new ValidatorException(new FacesMessage("The phone number must have more than " + m_min_length + " characters")); }

        if (str.length() > m_max_length)
        { throw new ValidatorException(new FacesMessage("The phone number must have less than " + m_max_length + " characters")); }


        Pattern patter_obj = Pattern.compile("(\\+7|8)-?\\d{3}-?\\d{3}-?\\d{3}");
        Matcher matcher_obj = patter_obj.matcher(str);
        boolean res = matcher_obj.matches();

        if (!res)
        { throw new ValidatorException(new FacesMessage("The phone number is invalid")); }
    }
}
