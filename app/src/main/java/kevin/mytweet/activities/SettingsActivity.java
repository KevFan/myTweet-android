package kevin.mytweet.activities;

import android.os.Bundle;

import kevin.mytweet.fragments.SettingsFragment;

import static kevin.mytweet.helpers.LogHelpers.info;

/**
 * Created by kevin on 25/10/2017.
 */

public class SettingsActivity extends BaseActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    info("Settings Activity - Created");
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      SettingsFragment fragment = new SettingsFragment();
      getFragmentManager().beginTransaction()
          .add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
          .commit();
    }
  }
}
