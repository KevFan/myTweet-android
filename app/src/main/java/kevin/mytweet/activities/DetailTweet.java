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
import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.models.TimeLine;
import kevin.mytweet.models.Tweet;

/**
 * Created by kevin on 16/10/2017.
 */

public class DetailTweet extends AppCompatActivity implements View.OnClickListener {
  private TextView detailCharCount;
  private TextView detailTweetDate;
  private TextView detailTweetText;
  private Button detailSelectContactButton;
  private Button detailEmailViaButton;
  private TimeLine timeLine;
  private MyTweetApp app;
  private Tweet tweet;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_tweet);

    detailCharCount = (TextView) findViewById(R.id.detailCharCount);
    detailTweetDate = (TextView) findViewById(R.id.detailTweetDate);
    detailTweetDate.setText((new Date().toString()));
    detailTweetText = (TextView) findViewById(R.id.detailTweetText);
    detailSelectContactButton = (Button) findViewById(R.id.detailSelectContactButton);
    detailSelectContactButton.setOnClickListener(this);
    detailEmailViaButton = (Button) findViewById(R.id.detailEmailViaButton);
    detailEmailViaButton.setOnClickListener(this);
    app = (MyTweetApp) getApplication();
    timeLine = app.currentUser.timeLine;

    Long tweetId = (Long) getIntent().getExtras().getSerializable("TWEET_ID");
    tweet = timeLine.getTweet(tweetId);
    if (tweet != null) {
      updateView(tweet);
    }
  }

  public void updateView(Tweet tweet) {
    detailCharCount.setText(String.valueOf(140 - tweet.tweetMessage.length()));
    detailTweetDate.setText(tweet.tweetDate.toString());
    detailTweetText.setText(tweet.tweetMessage);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.detailSelectContactButton:
        Toast.makeText(this, "Detail Tweet - Select Contact Button Pressed", Toast.LENGTH_SHORT).show();
        break;
      case R.id.detailEmailViaButton:
        Toast.makeText(this, "Detail Tweet - Email Via Button Pressed", Toast.LENGTH_SHORT).show();
        break;
      default:
        Toast.makeText(this, "Detail Tweet - Something is wrong :/ ", Toast.LENGTH_SHORT).show();
        break;
    }
  }
}