package kevin.mytweet.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kevin.mytweet.R;

import static kevin.mytweet.helpers.LogHelpers.toastMessage;

public class Welcome extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);

    Button loginButton = (Button) findViewById(R.id.welcomeLogin);
    loginButton.setOnClickListener(this);
    Button signupButton = (Button) findViewById(R.id.welcomeSignup);
    signupButton.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.welcomeSignup:
        startActivity(new Intent(this, SignUp.class));
        break;
      case R.id.welcomeLogin:
        startActivity(new Intent(this, Login.class));
        break;
      default:
        toastMessage(this, "Welcome - Something is wrong :/ ");
        break;
    }
  }
}
