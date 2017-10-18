package kevin.mytweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import static kevin.mytweet.helpers.LogHelpers.info;

import kevin.mytweet.R;
import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.models.Tweet;

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
  private MyTweetApp app;

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

    app = (MyTweetApp) getApplication();
  }

  // OnClist Listener
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tweetButton:
        String tweetMessage = tweetText.getText().toString();
        Date date = new Date(tweetDate.getText().toString());
        if (tweetMessage.isEmpty()) {
          Toast.makeText(this, "Enter some text to start tweeting !!", Toast.LENGTH_SHORT).show();
        } else {
          app.currentUser.timeLine.addTweet(new Tweet(tweetMessage, date));
          app.save();
          startActivity(new Intent(this, TimeLineActivity.class));
        }
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

  // TextWatcher Listener method implementations
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

  // Menu item selector
  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    switch (item.getItemId())
    {
      case R.id.menuTimeLine:
//        Toast.makeText(this, "TimeLine Selected", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TimeLineActivity.class));
        break;
      case R.id.menuSettings:
        Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
        break;
      case R.id.menuLogout:
        Toast.makeText(this, "Logout Selected", Toast.LENGTH_SHORT).show();
        break;
      default:
        Toast.makeText(this, "Add Tweet Menu - Something is wrong :(", Toast.LENGTH_SHORT).show();
        break;
    }
    return true;
  }

  // Menu Item inflater
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu items for use in the action bar
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_tweet, menu);
    return super.onCreateOptionsMenu(menu);
  }
}
