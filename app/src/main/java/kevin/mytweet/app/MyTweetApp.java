package kevin.mytweet.app;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kevin.mytweet.models.User;

/**
 * Created by kevin on 12/10/2017.
 */

public class MyTweetApp extends Application {
  public List<User> users = new ArrayList<>();

  public void onCreate()
  {
    super.onCreate();
    Log.v("MyTweet", "MyTweet App Started");
  }

  public void newUser (User user) {
    users.add(user);
  }

  public User successLogin(String email, String password) {
    User registeredUser = null;
    for (User user : users) {
      if (user.email.equals(email) && user.password.equals(password)) {
        registeredUser = user;
        Log.v("MyTweet", "Logged in: "  + user.toString());
      }
    }
    return registeredUser;
  }
}
