package kevin.mytweet.helpers;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;

/**
 * Created by kevin on 19/10/2017.
 */

public class IntentHelper {
  public static void navigateUp(Activity parent) {
    Intent upIntent = NavUtils.getParentActivityIntent(parent);
    NavUtils.navigateUpTo(parent, upIntent);
  }
}
