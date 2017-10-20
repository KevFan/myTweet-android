package kevin.mytweet.helpers;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;

import java.io.Serializable;

/**
 * Created by kevin on 19/10/2017.
 */

public class IntentHelper {
  public static void navigateUp(Activity parent) {
    Intent upIntent = NavUtils.getParentActivityIntent(parent);
    NavUtils.navigateUpTo(parent, upIntent);
  }

  public static void selectContact(Activity parent, int id) {
    Intent selectContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    parent.startActivityForResult(selectContactIntent, id);
  }

  public static void startActivityWithData(Activity parent, Class classname, String extraID, Serializable extraData) {
    Intent intent = new Intent(parent, classname);
    intent.putExtra(extraID, extraData);
    parent.startActivity(intent);
  }

  public static void startActivityWithDataForResult(Activity parent, Class classname, String extraID, Serializable extraData, int idForResult) {
    Intent intent = new Intent(parent, classname);
    intent.putExtra(extraID, extraData);
    parent.startActivityForResult(intent, idForResult);
  }
}
