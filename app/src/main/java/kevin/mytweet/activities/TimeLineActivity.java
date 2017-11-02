package kevin.mytweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Date;

import kevin.mytweet.fragments.TimeLineFragment;
import kevin.mytweet.R;
import kevin.mytweet.fragments.TweetFragment;
import kevin.mytweet.models.Tweet;

import static kevin.mytweet.helpers.LogHelpers.info;

/**
 * TimeLine Activity of user
 * Created by kevin on 15/10/2017.
 */

public class TimeLineActivity extends AppCompatActivity {
  /**
   * Called when activity is first created - creates timeline fragment if savedInstanceState is null
   * @param savedInstanceState Bundle with saved data if any
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    info("TimeLineActivity created");
    super.onCreate(savedInstanceState);

    setContentView(R.layout.fragment_home);
    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentById(R.id.fragment_home);
    if (fragment == null) {
      fragment = new TimeLineFragment();
      manager.beginTransaction().add(R.id.fragment_home, fragment).commit();
    }

    FloatingActionButton newTweet = (FloatingActionButton) findViewById(R.id.newTweetAction);
    newTweet.setOnClickListener(newTweetListener);
  }

  /**
   * Anonymous class listener for the new tweet floating action button
   * Creates a new tweet and passes it as a serializable object to the intent for TweetActivity
   */
  private View.OnClickListener newTweetListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      Tweet tweet = new Tweet("", new Date());
//        MyTweetApp.getApp().currentUser.timeLine.addTweet(tweet);
      Intent intent = new Intent(view.getContext(), TweetActivity.class);
      intent.putExtra(TweetFragment.EXTRA_TWEET, tweet);
//        intent.putExtra(TweetFragment.EXTRA_TWEET_ID, tweet.id);
      intent.putExtra(TweetFragment.EXTRA_VIEW_EDITABLE, true); // Set Edit view to editable
      startActivityForResult(intent, 0);
    }
  };
}
