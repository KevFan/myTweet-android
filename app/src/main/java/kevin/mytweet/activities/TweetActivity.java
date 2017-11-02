package kevin.mytweet.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;

import kevin.mytweet.R;
import kevin.mytweet.fragments.TweetFragment;

import static kevin.mytweet.helpers.LogHelpers.info;

/**
 * Tweet Activity
 * Created by kevin on 16/10/2017.
 */

public class TweetActivity extends BaseActivity {
  ActionBar actionBar;

  /**
   * Called when activity is first created - creates tweet fragment if savedBundleInstance is null
   * @param savedInstanceState Bundle with saved data if any
   */
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
}
