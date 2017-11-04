package kevin.mytweet.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;

import kevin.mytweet.R;
import kevin.mytweet.fragments.AddTweetFragment;

import static kevin.mytweet.helpers.IntentHelper.navigateUp;
import static kevin.mytweet.helpers.LogHelpers.info;
import static kevin.mytweet.helpers.LogHelpers.toastMessage;

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

  /**
   * Menu Item selector - only used for navigate up to previous activity here
   * @param item Menu item
   * @return Boolean
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    info("Base Activity - navigated up pressed");
    final Activity activity = this;
    switch (item.getItemId()) {
      case android.R.id.home:
        confirmReturn( new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface arg0, int arg1) {
            navigateUp(activity);
          }
        });
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * Override the on back pressed to display dialog to alert user that tweet will be discarded if
   * they continue
   * https://stackoverflow.com/questions/6413700/android-proper-way-to-use-onbackpressed-with-toast
   */
  @Override
  public void onBackPressed() {
    confirmReturn(new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface arg0, int arg1) {
        AddTweetActivity.super.onBackPressed();
      }
    });
  }

  /**
   * Helper for creating dialog box to reduce code duplication
   * @param listener DialogInterface.OnClickListener listener - action on yes for dialog box
   */
  private void confirmReturn(DialogInterface.OnClickListener listener) {
    new AlertDialog.Builder(this)
        .setTitle("Tweet not saved !!")
        .setMessage("Return to timeline and discard tweet?")
        .setNegativeButton(android.R.string.no, null)
        .setPositiveButton(android.R.string.yes, listener)
        .setIcon(R.drawable.ic_error_outline_black_24dp)
        .create().show();
  }
}
