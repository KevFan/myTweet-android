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
        Toast.makeText(view.getContext(), "Fill in all information to sign in !!", Toast.LENGTH_SHORT).show();
      } else if (app.successLogin(emailString, passwordString)) {
        startActivity(new Intent(view.getContext(), AddTweet.class));
        Toast.makeText(view.getContext(), "Login Successful !!", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(view.getContext(), "Email/Password incorrect !!", Toast.LENGTH_SHORT).show();
      }
    }
  };
}
