package kevin.mytweet.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Tweet tweet = adapter.getItem(position);
    Intent intent = new Intent(this, DetailTweet.class);
    intent.putExtra("TWEET_ID", tweet.id);
    startActivity(intent);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId())
    {
      case android.R.id.home:  navigateUp(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}

class TimeLineAdapter extends ArrayAdapter<Tweet> {
  private Context context;

  public TimeLineAdapter(Context context, ArrayList<Tweet> tweets) {
    super(context, 0, tweets);
    this.context = context;
  }

  @Override
  public View getView(int posistion, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.list_item_tweet, null);
    }

    Tweet tweet = getItem(posistion);

    TextView tweetText = (TextView) convertView.findViewById(R.id.list_item_tweetText);
    tweetText.setText(tweet.tweetMessage);

    TextView tweetDate = (TextView) convertView.findViewById(R.id.list_item_tweetDate);
    tweetDate.setText(tweet.tweetDate.toString());

    return convertView;
  }
}