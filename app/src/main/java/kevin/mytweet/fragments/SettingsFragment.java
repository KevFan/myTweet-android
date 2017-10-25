package kevin.mytweet.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import kevin.mytweet.R;
import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.models.User;

import static kevin.mytweet.helpers.IntentHelper.navigateUp;
import static kevin.mytweet.helpers.LogHelpers.info;

/**
 * Created by kevin on 25/10/2017.
 */

public class SettingsFragment extends PreferenceFragment
    implements SharedPreferences.OnSharedPreferenceChangeListener {
  private SharedPreferences prefs;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.settings);
    setHasOptionsMenu(true);
  }

  @Override
  public void onStart() {
    super.onStart();
    prefs = PreferenceManager
        .getDefaultSharedPreferences(getActivity());
    prefs.registerOnSharedPreferenceChangeListener(this);
  }

  @Override
  public void onStop() {
    super.onStop();
    prefs.unregisterOnSharedPreferenceChangeListener(this);
  }

  @Override
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    User currentUser = MyTweetApp.getApp().currentUser;
    info("Setting change - key : value = " + key + " : " + sharedPreferences.getString(key, ""));
    switch (key){
      case "firstName":
        currentUser.firstName = sharedPreferences.getString(key, "");
        break;
      case "lastName":
        currentUser.lastName = sharedPreferences.getString(key, "");
        break;
      case "email":
        currentUser.email = sharedPreferences.getString(key, "");
        break;
      case "password":
        currentUser.password = sharedPreferences.getString(key, "");
        break;
      default:
        info("Settings Fragment - Something went wrong in OnSharedPreferenceChanged :(");
    }
    MyTweetApp.getApp().save();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        navigateUp(getActivity());
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}