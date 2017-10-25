package kevin.mytweet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kevin.mytweet.fragments.SettingsFragment;

/**
 * Created by kevin on 25/10/2017.
 */

public class SettingsActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      SettingsFragment fragment = new SettingsFragment();
      getFragmentManager().beginTransaction()
          .add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
          .commit();
    }
  }
}
