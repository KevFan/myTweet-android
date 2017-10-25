package kevin.mytweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kevin.mytweet.R;
import kevin.mytweet.app.MyTweetApp;

import static kevin.mytweet.helpers.LogHelpers.toastMessage;

/**
 * Created by kevin on 09/10/2017.
 */

public class Login extends AppCompatActivity {
  private TextView email;
  private TextView password;
  private MyTweetApp app;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    email = (TextView) findViewById(R.id.loginEmail);
    password = (TextView) findViewById(R.id.loginPassword);
    app = (MyTweetApp) getApplication();
    Button login = (Button) findViewById(R.id.loginUser);
    login.setOnClickListener(loginListener);
  }

  private View.OnClickListener loginListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      String emailString = email.getText().toString();
      String passwordString = password.getText().toString();
      if (emailString.isEmpty() || passwordString.isEmpty()) {
        toastMessage(view.getContext(), "Fill in all information to sign in !!");
      } else if (app.successLogin(emailString, passwordString)) {
        startActivity(new Intent(view.getContext(), TimeLineActivity.class));
        toastMessage(view.getContext(), "Login Successful !!");
      } else {
        toastMessage(view.getContext(), "Email/Password incorrect !!");
      }
    }
  };
}
