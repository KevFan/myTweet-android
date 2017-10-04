package kevin.mytweet.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kevin.mytweet.R;

public class Welcome extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);

    Button loginButton = (Button) findViewById(R.id.loginButton);
    loginButton.setOnClickListener(this);
    Button signupButton = (Button) findViewById(R.id.signupButton);
    signupButton.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.signupButton:
        Toast.makeText(this, "Welcome Signup Button Pressed", Toast.LENGTH_SHORT).show();
        break;
      case R.id.loginButton:
        Toast.makeText(this, "Welcome Login Button Pressed", Toast.LENGTH_SHORT).show();
        break;
      default:
        Toast.makeText(this, "Welcome - Something is wrong :/ ", Toast.LENGTH_SHORT).show();
        break;
    }
  }
}
