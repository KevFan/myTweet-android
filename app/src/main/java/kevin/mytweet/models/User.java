package kevin.mytweet.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 12/10/2017.
 */

public class User {
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  public TimeLine timeLine;

  public User (String firstName, String lastName, String email, String password, TimeLine timeLine) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.timeLine = timeLine;
  }
}
