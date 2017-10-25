package kevin.mytweet.helpers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kevin on 13/10/2017.
 */

public class LogHelpers {
  public static void info(String message) {
    Log.v("MyTweet", message);
  }

  public static void toastMessage(Context context, String toastMessage) {
    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
  }
}
