package kevin.mytweet.helpers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Log Helpers
 * Created by kevin on 13/10/2017.
 */

public class LogHelpers {
  /**
   * Log messages and tag with "MyTweet"
   *
   * @param message Message tp log
   */
  public static void info(String message) {
    Log.v("MyTweet", message);
  }

  /**
   * Toast helper
   *
   * @param context      Context of where method was called
   * @param toastMessage Toast message to display
   */
  public static void toastMessage(Context context, String toastMessage) {
    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
  }
}
