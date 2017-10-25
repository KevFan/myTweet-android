package kevin.mytweet.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import kevin.mytweet.R;
import kevin.mytweet.fragments.TweetFragment;

import static kevin.mytweet.helpers.IntentHelper.navigateUp;
import static kevin.mytweet.helpers.LogHelpers.info;

/**
 * Created by kevin on 16/10/2017.
 */

public class TweetActivity extends AppCompatActivity {
  ActionBar actionBar;

  public void onCreate(Bundle savedInstanceState) {
    info("Tweet Activity created");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_container);

    actionBar = getSupportActionBar();

    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
    if (fragment == null) {
      fragment = new TweetFragment();
      manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
    }
  }

  // Menu item selector
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    info("Tweet Activity - navigated up pressed");
    switch (item.getItemId()) {
      case android.R.id.home:
        navigateUp(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
