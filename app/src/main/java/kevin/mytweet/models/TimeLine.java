package kevin.mytweet.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kevin on 15/10/2017.
 */

public class TimeLine {
  public ArrayList<Tweet> tweets;

  public TimeLine() {
    tweets = new ArrayList<Tweet>();
  }

  public void addTweet(Tweet tweet) {
   tweets.add(tweet);
  }

  public Tweet getTweet(Long id) {
    for (Tweet tweet : tweets) {
      if (tweet.id.equals(id)) {
        return tweet;
      }
    }

    return null;
  }

  public void deleteTweet(Tweet tweet) {
    tweets.remove(tweet);
  }
}
