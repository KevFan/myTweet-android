package kevin.mytweet.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import kevin.mytweet.R;
import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.models.User;

import static kevin.mytweet.helpers.LogHelpers.info;

/**
 * Settings Fragment
 * Created by kevin on 25/10/2017.
 */

public class SettingsFragment extends PreferenceFragment
    implements SharedPreferences.OnSharedPreferenceChangeListener {
  private SharedPreferences prefs;

  /**
   * Called when fragment is first created - sets the preference values from settings xml
   *
   * @param savedInstanceState Bundle with saved data if any
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    info("Settings Fragment - created");
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.settings);
    setHasOptionsMenu(true);
  }

  /**
   * Called when the fragment is becoming visible to the user
   * Instantiates the shared preferences and registers the listener
   */
  @Override
  public void onStart() {
    super.onStart();
    prefs = PreferenceManager
        .getDefaultSharedPreferences(getActivity());
    prefs.registerOnSharedPreferenceChangeListener(this);
  }

  /**
   * Called when the fragment is no longer visible to the user
   * Un-registers the listener
   */
  @Override
  public void onStop() {
    super.onStop();
    prefs.unregisterOnSharedPreferenceChangeListener(this);
  }

  /**
   * Listener for changes in the shared preferences values through keys
   * Used to update user details through use of keys
   * TODO: update email should perform checks such as valid email and used email
   *
   * @param sharedPreferences Shared preference object
   * @param key               Key to a shared preference field/value
   */
  @Override
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    User currentUser = MyTweetApp.getApp().currentUser;
    info("Setting change - key : value = " + key + " : " + sharedPreferences.getString(key, ""));
    switch (key) {
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
}
