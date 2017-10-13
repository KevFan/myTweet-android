package kevin.mytweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import static kevin.mytweet.helpers.LogHelpers.info;

import kevin.mytweet.R;

/**
 * Created by kevin on 12/10/2017.
 */

public class AddTweet extends AppCompatActivity implements View.OnClickListener, TextWatcher {
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
    tweetText.addTextChangedListener(this);
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

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
    int remainingCarCount = 140 - s.toString().length();
    charCount.setText(String.valueOf(remainingCarCount));
    info(s.toString());
  }

  @Override
  public void afterTextChanged(Editable s) {
  }
}
