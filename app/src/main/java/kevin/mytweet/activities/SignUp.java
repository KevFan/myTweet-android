package kevin.mytweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import kevin.mytweet.R;
import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.models.User;

/**
 * Created by kevin on 09/10/2017.
 */

public class SignUp extends AppCompatActivity {
  private TextView firstName;
  private TextView lastName;
  private TextView email;
  private TextView password;
  private MyTweetApp app;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);

    firstName = (TextView) findViewById(R.id.firstName);
    lastName = (TextView) findViewById(R.id.lastName);
    email = (TextView) findViewById(R.id.email);
    password = (TextView) findViewById(R.id.password);
    app = (MyTweetApp) getApplication();
    Button signup = (Button) findViewById(R.id.signupMyTweet);
    signup.setOnClickListener(signupListener);
  }

  private View.OnClickListener signupListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      String firstNameString = firstName.getText().toString();
      String lastNameString = lastName.getText().toString();
      String emailString = email.getText().toString();
      String passwordString = password.getText().toString();
      if (firstNameString.isEmpty() || lastNameString.isEmpty() || emailString.isEmpty()
          || passwordString.isEmpty()) {
        Toast.makeText(view.getContext(), "Fill in all information to complete signup", Toast.LENGTH_SHORT).show();
      } else {
        app.newUser(new User(firstNameString, lastNameString, emailString, passwordString));
        Toast.makeText(view.getContext(), "Successfully Registered - Login now to begin", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(view.getContext(), Login.class));
      }
    }
  };
}
