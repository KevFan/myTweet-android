package kevin.mytweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kevin.mytweet.R;
import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.models.TimeLine;
import kevin.mytweet.models.User;

import static kevin.mytweet.helpers.IntentHelper.navigateUp;
import static kevin.mytweet.helpers.LogHelpers.info;
import static kevin.mytweet.helpers.LogHelpers.toastMessage;

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
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        toastMessage(view.getContext(), "Fill in all information to complete signup");
      } else if (!isValidEmail(emailString)) {
        toastMessage(view.getContext(), "Email is not a valid format");
      } else {
        app.newUser(new User(firstNameString, lastNameString, emailString, passwordString, new TimeLine()));
        toastMessage(view.getContext(), "Successfully Registered");
        startActivity(new Intent(view.getContext(), TimeLineActivity.class));
      }
    }
  };

  // https://stackoverflow.com/questions/31262250/how-to-check-whether-email-is-valid-format-or-not-in-android
  private boolean isValidEmail(String target) {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
  }

  // Menu item selector
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    info("Sign Up Activity - navigated up pressed");
    switch (item.getItemId()) {
      case android.R.id.home:
        navigateUp(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
