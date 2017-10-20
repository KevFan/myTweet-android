package kevin.mytweet.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.models.TimeLine;
import kevin.mytweet.R;
import kevin.mytweet.models.Tweet;

import static kevin.mytweet.helpers.IntentHelper.navigateUp;

/**
 * Created by kevin on 15/10/2017.
 */

public class TimeLineActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
  private ListView listView;
  private TimeLine timeLine;
  private TimeLineAdapter adapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle(R.string.app_name);
    setContentView(R.layout.activity_timeline);

    listView = (ListView) findViewById(R.id.tweetList);

    MyTweetApp app = (MyTweetApp) getApplication();
    timeLine = app.currentUser.timeLine;

    adapter = new TimeLineAdapter(this, timeLine.tweets);
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(this);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Tweet tweet = adapter.getItem(position);
    Intent intent = new Intent(this, DetailTweet.class);
    intent.putExtra("TWEET_ID", tweet.id);
    startActivity(intent);
  }

  // Menu item selector
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menuTimeLine:
//        Toast.makeText(this, "TimeLine Selected", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, TimeLineActivity.class));
        break;
      case R.id.menuSettings:
        Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
        break;
      case R.id.menuLogout:
        Toast.makeText(this, "Logout Selected", Toast.LENGTH_SHORT).show();
        break;
      case R.id.menu_item_new_tweet:
        startActivity(new Intent(this, AddTweet.class));
        return true;
      default:
        Toast.makeText(this, "Add Tweet Menu - Something is wrong :(", Toast.LENGTH_SHORT).show();
        break;
    }
    return true;
  }

  // Menu Item inflater
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu items for use in the action bar
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_tweet, menu);
    return super.onCreateOptionsMenu(menu);
  }
}

class TimeLineAdapter extends ArrayAdapter<Tweet> {
  private Context context;

  public TimeLineAdapter(Context context, ArrayList<Tweet> tweets) {
    super(context, 0, tweets);
    this.context = context;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.list_item_tweet, null);
    }

    Tweet tweet = getItem(position);

    TextView tweetText = (TextView) convertView.findViewById(R.id.list_item_tweetText);
    tweetText.setText(tweet.tweetMessage);

    TextView tweetDate = (TextView) convertView.findViewById(R.id.list_item_tweetDate);
    tweetDate.setText(tweet.tweetDate.toString());

    return convertView;
  }
}
