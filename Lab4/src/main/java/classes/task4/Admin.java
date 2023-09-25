package classes.task4;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "admin", schema = "public", catalog = "postgres")
public class Admin extends User
{
  private String accessLevel;
}
