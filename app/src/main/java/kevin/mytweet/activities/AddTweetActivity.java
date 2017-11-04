package kevin.mytweet.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import kevin.mytweet.R;
import kevin.mytweet.fragments.AddTweetFragment;

import static kevin.mytweet.helpers.LogHelpers.info;

/**
 * Add Tweet activity
 * Created by kevin on 25/10/2017.
 */

public class AddTweetActivity extends BaseActivity {
  /**
   * Called when activity is first created
   * Creates the add tweet fragment if savedInstanceState is null
   * @param savedInstanceState Bundle with saved data if any
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    info("Add Tweet Activity - Created");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_container);

    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
    if (fragment == null) {
      fragment = new AddTweetFragment();
      manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
    }
  }
}
