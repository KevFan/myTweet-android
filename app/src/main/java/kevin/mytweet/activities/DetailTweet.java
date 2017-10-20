package kevin.mytweet.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import kevin.mytweet.R;
import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.models.TimeLine;
import kevin.mytweet.models.Tweet;

import static kevin.mytweet.helpers.ContactHelper.getContact;
import static kevin.mytweet.helpers.ContactHelper.getEmail;
import static kevin.mytweet.helpers.ContactHelper.sendEmail;
import static kevin.mytweet.helpers.IntentHelper.navigateUp;
import static kevin.mytweet.helpers.IntentHelper.selectContact;

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
  private static final int REQUEST_CONTACT = 1;
  private String emailAddress = "";
  private Intent data;

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

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        selectContact(this, REQUEST_CONTACT);
        break;
      case R.id.detailEmailViaButton:
        sendEmail(this, emailAddress,
            getString(R.string.tweet_report_title), tweet.getTweetReport());
        break;
      default:
        Toast.makeText(this, "Detail Tweet - Something is wrong :/ ", Toast.LENGTH_SHORT).show();
        break;
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case REQUEST_CONTACT:
        this.data = data;
        checkContactsReadPermission();
        break;
    }
  }

  private void readContact() {
    String name = getContact(this, data);
    emailAddress = getEmail(this, data);
    detailSelectContactButton.setText(name + " : " + emailAddress);
  }

  //https://developer.android.com/training/permissions/requesting.html
  private void checkContactsReadPermission() {
    // Here, thisActivity is the current activity
    if (ContextCompat.checkSelfPermission(this,
        Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
      //We can request the permission.
      ActivityCompat.requestPermissions(this,
          new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACT);
    } else {
      //We already have permission, so go head and read the contact
      readContact();
    }
  }

  //https://developer.android.com/training/permissions/requesting.html
  @Override
  public void onRequestPermissionsResult(int requestCode,
                                         String permissions[], int[] grantResults) {
    switch (requestCode) {
      case REQUEST_CONTACT: {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          // permission was granted
          readContact();
        }
      }
    }
  }

  // Menu item selector
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        navigateUp(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
