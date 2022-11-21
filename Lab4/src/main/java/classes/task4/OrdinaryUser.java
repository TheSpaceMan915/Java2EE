package classes.task4;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "ordinary_user", schema = "public", catalog = "postgres")
public class OrdinaryUser extends User
{

}
