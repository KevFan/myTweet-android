package kevin.mytweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import kevin.mytweet.R;

/**
 * Created by kevin on 09/10/2017.
 */

public class Login extends AppCompatActivity {
  private TextView email;
  private TextView password;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    email = (TextView) findViewById(R.id.loginEmail);
    password = (TextView) findViewById(R.id.loginPassword);
    Button login = (Button) findViewById(R.id.loginUser);
    login.setOnClickListener(loginListener);
  }

  private View.OnClickListener loginListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      Log.v("MyTweet", "Login User button clicked");
      startActivity(new Intent(view.getContext(), AddTweet.class));
    }
  };
}
