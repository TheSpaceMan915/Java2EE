package classes.task1;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlRootElement(name = "Config")
@XmlType(propOrder = {"url","user","passwor"})
public class Configuration
{

}
