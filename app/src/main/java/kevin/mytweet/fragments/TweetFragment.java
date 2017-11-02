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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kevin.mytweet.R;
import kevin.mytweet.activities.TimeLineActivity;
import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.models.TimeLine;
import kevin.mytweet.models.Tweet;

import static kevin.mytweet.helpers.ContactHelper.getContact;
import static kevin.mytweet.helpers.ContactHelper.getEmail;
import static kevin.mytweet.helpers.ContactHelper.sendEmail;
import static kevin.mytweet.helpers.LogHelpers.*;

/**
 * Tweet Fragment - used to detail and add tweet
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

  /**
   * Called when fragment is first created
   *
   * @param savedInstanceState Bundle with saved data if any
   */
  public void onCreate(Bundle savedInstanceState) {
    info("Tweet Fragment created");
    super.onCreate(savedInstanceState);
    app = MyTweetApp.getApp();
    timeLine = app.currentUser.timeLine;

//    Long tweetId = (Long) getActivity().getIntent().getExtras().getSerializable("TWEET_ID");
//    tweet = timeLine.getTweet(tweetId);
    tweet = (Tweet) getActivity().getIntent().getExtras().getSerializable(EXTRA_TWEET);
  }

  /**
   * Called to create the view hierarchy associated with the fragment
   *
   * @param inflater           Layout inflater
   * @param parent             Parent view group
   * @param savedInstanceState Bundle with saved data if any
   * @return View of the layout
   */
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    super.onCreateView(inflater, parent, savedInstanceState);
    View view = inflater.inflate(R.layout.fragment_tweet, parent, false);
    charCount = (TextView) view.findViewById(R.id.charCount);
    tweetDate = (TextView) view.findViewById(R.id.tweetDate);
    tweetButton = (Button) view.findViewById(R.id.tweetButton);
    tweetButton.setOnClickListener(this);
    selectContactButton = (Button) view.findViewById(R.id.selectContactButton);
    selectContactButton.setOnClickListener(this);
    emailViaButton = (Button) view.findViewById(R.id.emailViaButton);
    emailViaButton.setOnClickListener(this);
    tweetText = (EditText) view.findViewById(R.id.tweetText);
    tweetText.addTextChangedListener(this);
    updateView(tweet);

    // Determine should tweet text is editable or not, remove tweet button if not editable
    boolean editable = (boolean) getActivity().getIntent().getExtras().getSerializable("VIEW_EDITABLE");
    if (!editable) {
      tweetText.setEnabled(false); // Set tweet message to read only in view tweets
      tweetButton.setVisibility(View.GONE); // Remove tweet button in view tweets adding same tweet
    }
    return view;
  }

  /**
   * Updates the text view and edit text view with the tweet details
   *
   * @param tweet Tweet to update views with
   */
  public void updateView(Tweet tweet) {
    charCount.setText(String.valueOf(140 - tweet.tweetMessage.length()));
    tweetDate.setText(tweet.tweetDate.toString());
    tweetText.setText(tweet.tweetMessage);
  }

  /**
   * On click listener for the tweet, select contact and email buttons
   *
   * @param view View clicked
   */
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tweetButton:
        // If tweet message is empty
        if (tweet.tweetMessage.equals("")) {
          toastMessage(getActivity(), "Write your message to send tweet");
        } else {
          timeLine.addTweet(tweet);
          app.save();
          toastMessage(getActivity(), "Message Sent !! ");
          startActivity(new Intent(getActivity(), TimeLineActivity.class));
        }
        break;
      case R.id.selectContactButton:
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CONTACT);
        break;
      case R.id.emailViaButton:
        sendEmail(getActivity(), emailAddress, getString(R.string.tweet_report_title), tweet.getTweetReport());
        break;
      default:
        toastMessage(getActivity(), "Tweet Fragment - Something is wrong :/ ");
        break;
    }
  }

  /**
   * Receive the result from a previous call to startActivityForResult
   *
   * @param requestCode Integer of request code supplied by startActivityForResult
   * @param resultCode  integer result code
   * @param data        Intent
   */
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    // If result code is not an ok result - exit
    if (resultCode != Activity.RESULT_OK) {
      return;
    }

    // If request code is the request contact, set the intent to data passed in and check
    // for permissions
    switch (requestCode) {
      case REQUEST_CONTACT:
        this.data = data;
        checkContactsReadPermission();
        break;
    }
  }

  /**
   * Reads the contact details and sets the email address to the contact email and set select
   * contact button text with contact name and email
   */
  private void readContact() {
    String name = getContact(getActivity(), data);
    emailAddress = getEmail(getActivity(), data);
    selectContactButton.setText(name + " : " + emailAddress);
  }

  /**
   * Check for permission to read contacts
   * https://developer.android.com/training/permissions/requesting.html
   */
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

  /**
   * Called after asking for permissions
   * https://developer.android.com/training/permissions/requesting.html
   *
   * @param requestCode  Request code passed in by requestPermissions
   * @param permissions  requested permissions
   * @param grantResults result of granting permissions
   */
  @Override
  public void onRequestPermissionsResult(int requestCode, String permissions[],
                                         int[] grantResults) {
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

  /* ************ TextWatcher Listener methods (begin) *********** */

  /**
   * Called after text changed - sets the character count to be 140 - character sequence length and
   * sets the tweet message to be the tweetText
   *
   * @param s      Character sequence
   * @param start  start
   * @param before before
   * @param count  count
   */
  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
    int remainingCarCount = 140 - s.toString().length();
    charCount.setText(String.valueOf(remainingCarCount));
    tweet.tweetMessage = tweetText.getText().toString();
  }

  /**
   * Method stub to implement TextWatcher Listener interface - unused
   */
  @Override
  public void afterTextChanged(Editable s) {
  }

  /**
   * Method stub to implement TextWatcher Listener interface - unused
   */
  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  }
}