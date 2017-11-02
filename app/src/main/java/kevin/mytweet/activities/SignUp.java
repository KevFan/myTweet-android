package kevin.mytweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kevin.mytweet.R;
import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.models.TimeLine;
import kevin.mytweet.models.User;

import static kevin.mytweet.helpers.LogHelpers.info;
import static kevin.mytweet.helpers.LogHelpers.toastMessage;

/**
 * Sign up Activity to register a new user
 * Created by kevin on 09/10/2017.
 */

public class SignUp extends BaseActivity {
  private TextView firstName;
  private TextView lastName;
  private TextView email;
  private TextView password;
  private MyTweetApp app;

  /**
   * Called when activity is first created
   * @param savedInstanceState Bundle with saved data if any
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    info("SignUp Activity - Created");
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

  /**
   * Anonymous class listener for the login button
   * Checks for all fields are filled, if the email is in a valid email format and whether the email
   * is already used before signing up. Starts TimeLineActivity if successfully registered
   */
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
      } else if (isEmailUsed(emailString)) {
        toastMessage(view.getContext(), "Email already used by another user");
      } else {
        app.newUser(new User(firstNameString, lastNameString, emailString, passwordString, new TimeLine()));
        toastMessage(view.getContext(), "Successfully Registered");
        startActivity(new Intent(view.getContext(), TimeLineActivity.class));
      }
    }
  };

  /**
   * Checks string is in a valid email format
   * https://stackoverflow.com/questions/31262250/how-to-check-whether-email-is-valid-format-or-not-in-android
   * @param target String to check
   * @return Boolean of if target string passed is in a valid email format
   */
  private boolean isValidEmail(String target) {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
  }

  /**
   * Check is email already used by a user
   * @param email email to check for
   * @return Boolean of if email is already used
   */
  private boolean isEmailUsed(String email) {
    for (User user : app.users) {
      if (user.email.equals(email)) {
        return true;
      }
    }
    return false;
  }
}
