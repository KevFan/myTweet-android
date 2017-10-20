package kevin.mytweet.fragments;

import android.support.v4.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.Date;

import kevin.mytweet.R;
import kevin.mytweet.activities.TweetActivity;
import kevin.mytweet.activities.TimeLineActivity;
import kevin.mytweet.app.MyTweetApp;
import kevin.mytweet.helpers.IntentHelper;
import kevin.mytweet.models.TimeLine;
import kevin.mytweet.models.Tweet;

/**
 * Created by kevin on 20/10/2017.
 */

public class TimeLineFragment extends ListFragment implements AdapterView.OnItemClickListener {
  private TimeLine timeLine;
  private TimeLineAdapter adapter;
  MyTweetApp app;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    getActivity().setTitle(R.string.app_name);

    app = MyTweetApp.getApp();
    timeLine = app.currentUser.timeLine;

    adapter = new TimeLineAdapter(getActivity(), timeLine.tweets);
    setListAdapter(adapter);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    View v = super.onCreateView(inflater, parent, savedInstanceState);
    return v;
  }

  @Override
  public void onListItemClick(ListView l, View view, int position, long id) {
    Tweet tweet = ((TimeLineAdapter) getListAdapter()).getItem(position);
    Intent intent = new Intent(getActivity(), TweetActivity.class);
    intent.putExtra(TweetFragment.EXTRA_TWEET_ID, tweet.id);
    intent.putExtra(TweetFragment.EXTRA_VIEW_EDITABLE, false); // Set Edit view to read only
    startActivityForResult(intent, 0);
  }

  // Menu item selector
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menuTimeLine:
//        Toast.makeText(this, "TimeLine Selected", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), TimeLineActivity.class));
        break;
      case R.id.menuSettings:
        Toast.makeText(getActivity(), "Settings Selected", Toast.LENGTH_SHORT).show();
        break;
      case R.id.menuLogout:
        Toast.makeText(getActivity(), "Logout Selected", Toast.LENGTH_SHORT).show();
        break;
      case R.id.menu_item_new_tweet:
        Tweet tweet = new Tweet("", new Date());
        timeLine.addTweet(tweet);
        Intent intent = new Intent(getActivity(), TweetActivity.class);
        intent.putExtra(TweetFragment.EXTRA_TWEET_ID, tweet.id);
        intent.putExtra(TweetFragment.EXTRA_VIEW_EDITABLE, true); // Set Edit view to editable
        startActivityForResult(intent, 0);
        return true;
      default:
        Toast.makeText(getActivity(), "Add Tweet Menu - Something is wrong :(", Toast.LENGTH_SHORT).show();
        break;
    }
    return true;
  }

  // Menu Item inflater
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater ) {
    // Inflate the menu items for use in the action bar
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_tweet, menu);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Tweet tweet = adapter.getItem(position);
    IntentHelper.startActivityWithData(getActivity(), TweetActivity.class, "TWEET_ID", tweet.id);
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
}