package kevin.mytweet.app;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kevin.mytweet.models.TimeLine;
import kevin.mytweet.models.User;
import static kevin.mytweet.helpers.LogHelpers.info;

/**
 * Created by kevin on 12/10/2017.
 */

public class MyTweetApp extends Application {
  public List<User> users = new ArrayList<>();
  public User currentUser = null;

  public void onCreate()
  {
    super.onCreate();
    info("MyTweet App Started");

    users.add(new User("Homer", "Simpson", "homer@simpson.com", "secret", new TimeLine()));
    users.add(new User("Marge", "Simpson", "marge@simpson.com", "secret", new TimeLine()));
  }

  public void newUser (User user) {
    users.add(user);
  }

  public boolean successLogin(String email, String password) {
    User registeredUser = null;
    for (User user : users) {
      if (user.email.equals(email) && user.password.equals(password)) {
        currentUser = user;
        info("Logged in: "  + user.toString());
        return true;
      }
    }
    return false;
  }
}
