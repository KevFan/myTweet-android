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
    generateTestData();
  }

  public void addTweet(Tweet tweet) {
   tweets.add(tweet);
  }

  public Tweet getTweet(Long id) {
    for (Tweet tweet : tweets) {
      if (tweet.id == id) {
        return tweet;
      }
    }

    return null;
  }

  private void generateTestData() {
    for (int i = 0; i < 100; i += 1) {
      Tweet tweet = new Tweet("Test " + i, new Date());
      addTweet(tweet);
    }
  }
}
