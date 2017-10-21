package kevin.mytweet.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import kevin.mytweet.R;
import kevin.mytweet.activities.TimeLineActivity;
import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.models.TimeLine;
import kevin.mytweet.models.Tweet;

import static kevin.mytweet.helpers.ContactHelper.getContact;
import static kevin.mytweet.helpers.ContactHelper.getEmail;
import static kevin.mytweet.helpers.ContactHelper.sendEmail;
import static kevin.mytweet.helpers.IntentHelper.navigateUp;
import static kevin.mytweet.helpers.IntentHelper.selectContact;
import static kevin.mytweet.helpers.LogHelpers.info;

/**
 * Created by kevin on 20/10/2017.
 */

public class TweetFragment extends Fragment implements View.OnClickListener, TextWatcher {
  public static final String EXTRA_TWEET = "TWEET";
  public static final String EXTRA_VIEW_EDITABLE = "VIEW_EDITABLE";
  private static final int REQUEST_CONTACT = 1;

  private TextView charCount;
  private TextView tweetDate;
  private Button tweetButton;
  private Button selectContactButton;
  private Button emailViaButton;
  private EditText tweetText;
  private TimeLine timeLine;
  private MyTweetApp app;
  private Tweet tweet;
  private String emailAddress = "";
  private Intent data;

  public void onCreate(Bundle savedInstanceState) {
    info("Tweet Fragment created");
    super.onCreate(savedInstanceState);
    app = MyTweetApp.getApp();
    timeLine = app.currentUser.timeLine;

//    Long tweetId = (Long) getActivity().getIntent().getExtras().getSerializable("TWEET_ID");
//    tweet = timeLine.getTweet(tweetId);
    tweet = (Tweet) getActivity().getIntent().getExtras().getSerializable(EXTRA_TWEET);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    super.onCreateView(inflater, parent, savedInstanceState);
    View v = inflater.inflate(R.layout.fragment_tweet, parent, false);

    charCount = (TextView) v.findViewById(R.id.charCount);
    tweetDate = (TextView) v.findViewById(R.id.tweetDate);
    tweetButton = (Button) v.findViewById(R.id.tweetButton);
    tweetButton.setOnClickListener(this);
    selectContactButton = (Button) v.findViewById(R.id.selectContactButton);
    selectContactButton.setOnClickListener(this);
    emailViaButton = (Button) v.findViewById(R.id.emailViaButton);
    emailViaButton.setOnClickListener(this);
    tweetText = (EditText) v.findViewById(R.id.tweetText);
    tweetText.addTextChangedListener(this);
    updateView(tweet);

    boolean editable = (boolean) getActivity().getIntent().getExtras().getSerializable("VIEW_EDITABLE");
    if (!editable) {
      tweetText.setEnabled(false);
    }
    return v;
  }


  public void updateView(Tweet tweet) {
    charCount.setText(String.valueOf(140 - tweet.tweetMessage.length()));
    tweetDate.setText(tweet.tweetDate.toString());
    tweetText.setText(tweet.tweetMessage);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tweetButton:
        tweet.tweetMessage = tweetText.getText().toString();
        timeLine.addTweet(tweet);
        app.save();
        Toast.makeText(getActivity(), "Message Sent !! ", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), TimeLineActivity.class));
        break;
      case R.id.selectContactButton:
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CONTACT);
        break;
      case R.id.emailViaButton:
        sendEmail(getActivity(), emailAddress,
            getString(R.string.tweet_report_title), tweet.getTweetReport());
        break;
      default:
        Toast.makeText(getActivity(), "Tweet Fragment - Something is wrong :/ ", Toast.LENGTH_SHORT).show();
        break;
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode != Activity.RESULT_OK) {
      return;
    }

    switch (requestCode) {
      case REQUEST_CONTACT:
        this.data = data;
        checkContactsReadPermission();
        break;
    }
  }

  private void readContact() {
    String name = getContact(getActivity(), data);
    emailAddress = getEmail(getActivity(), data);
    selectContactButton.setText(name + " : " + emailAddress);
  }

  //https://developer.android.com/training/permissions/requesting.html
  private void checkContactsReadPermission() {
    // Here, thisActivity is the current activity
    if (ContextCompat.checkSelfPermission(getActivity(),
        Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
      //We can request the permission.
      ActivityCompat.requestPermissions(getActivity(),
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

  // TextWatcher Listener method implementations
  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
    int remainingCarCount = 140 - s.toString().length();
    charCount.setText(String.valueOf(remainingCarCount));
    tweet.tweetMessage = tweetText.getText().toString();
  }

  @Override
  public void afterTextChanged(Editable s) {
  }

//  @Override
//  public void onDestroy(){
//    super.onDestroy();
//    info("On Stop Fragment");
//    if (tweet.tweetMessage.equals("")) {
//      timeLine.deleteTweet(tweet);
//      info("Tweet was empty - Deleted tweet");
//    }
////    app.load()
//  }
}