package kevin.mytweet.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
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

  private static final String FILENAME = "myTweetData.json";

  public void onCreate() {
    super.onCreate();
    info("MyTweet App Started");
    users = load();
  }

  public void newUser (User user) {
    users.add(user);
  }

  public boolean successLogin(String email, String password) {
    for (User user : users) {
      if (user.email.equals(email) && user.password.equals(password)) {
        currentUser = user;
        info("Logged in: "  + user.toString());
        return true;
      }
    }
    return false;
  }

  public void save() {
    Gson gson = new GsonBuilder().create();
    Writer writer = null;
    try {
      OutputStream out = this.openFileOutput(FILENAME, Context.MODE_PRIVATE);
      writer = new OutputStreamWriter(out);
      writer.write(gson.toJson(users));
      writer.close();
      info("Saved by gson!!");
    } catch (Exception e) {
      info(e.toString());
    }
  }

  public List<User> load() {
    List<User> users = new ArrayList<User>();
    Gson gson = new Gson();
    Type modelType = new TypeToken<List<User>>(){}.getType();
    BufferedReader reader = null;
    try {
      // open and read the file into a StringBuilder
      InputStream in = this.openFileInput(FILENAME);
      reader = new BufferedReader(new InputStreamReader(in));
      StringBuilder jsonString = new StringBuilder();
      String line = null;
      while ((line = reader.readLine()) != null) {
        // line breaks are omitted and irrelevant
        jsonString.append(line);
      }
      reader.close();
      users = gson.fromJson(jsonString.toString(), modelType);
      info("Loaded by GSon!!");
    } catch (Exception e) {
      info(e.toString());
    }

    return users;
  }
}
