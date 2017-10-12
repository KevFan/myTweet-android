package kevin.mytweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import kevin.mytweet.R;

/**
 * Created by kevin on 12/10/2017.
 */

public class AddTweet extends AppCompatActivity implements View.OnClickListener {
  private TextView charCount;
  private TextView tweetDate;
  private Button tweetButton;
  private Button selectContactButton;
  private Button emailViaButton;
  private EditText tweetText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_tweet);

    charCount = (TextView) findViewById(R.id.charCount);
    charCount.setText("140");
    tweetDate = (TextView) findViewById(R.id.tweetDate);
    tweetDate.setText((new Date().toString()));
    tweetButton = (Button) findViewById(R.id.tweetButton);
    tweetButton.setOnClickListener(this);
    selectContactButton = (Button) findViewById(R.id.selectContactButton);
    selectContactButton.setOnClickListener(this);
    emailViaButton = (Button) findViewById(R.id.emailViaButton);
    emailViaButton.setOnClickListener(this);
    tweetText = (EditText) findViewById(R.id.tweetText);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tweetButton:
        Toast.makeText(this, "Add Tweet - Tweet Button Pressed", Toast.LENGTH_SHORT).show();
        break;
      case R.id.selectContactButton:
        Toast.makeText(this, "Add Tweet - Select Contact Button Pressed", Toast.LENGTH_SHORT).show();
        break;
      case R.id.emailViaButton:
        Toast.makeText(this, "Add Tweet - Email Via Button Pressed", Toast.LENGTH_SHORT).show();
        break;
      default:
        Toast.makeText(this, "Add Tweet - Something is wrong :/ ", Toast.LENGTH_SHORT).show();
        break;
    }
  }
}
