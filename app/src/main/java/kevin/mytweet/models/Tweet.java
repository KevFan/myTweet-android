package kevin.mytweet.models;

import java.util.Date;

/**
 * Created by kevin on 13/10/2017.
 */

public class Tweet {
  public String tweetMessage;
  public Date tweetDate;

  public Tweet(String tweetMessage, Date tweetDate) {
    this.tweetMessage = tweetMessage;
    this.tweetDate = tweetDate;
  }
}
