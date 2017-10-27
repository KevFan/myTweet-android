package kevin.mytweet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import static kevin.mytweet.helpers.IntentHelper.navigateUp;
import static kevin.mytweet.helpers.LogHelpers.info;

/**
 * Base Activity to store common functionality such as navigate up
 * Created by kevin on 27/10/2017.
 */

public class BaseActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    info("Base Activity - Created");
  }

  // Menu item selector
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    info("Base Activity - navigated up pressed");
    switch (item.getItemId()) {
      case android.R.id.home:
        navigateUp(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
