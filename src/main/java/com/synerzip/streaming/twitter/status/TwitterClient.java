/**
 * 
 */
package com.synerzip.streaming.twitter.status;


import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class TwitterClient {
    /**
     * Main entry of this application.
     *
     * @param args
     */
    public static void main(String[] args) throws TwitterException {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey("8zIUUP4igioSCth2Zh8zpg")
    	  .setOAuthConsumerSecret("Klnt5t7TNDrGV5CRJKBjj9qmCN4aNxjRnKhe7oPqQ")
    	  .setOAuthAccessToken("14242294-WBn0JDI0Vh7AAGPo6D7xW7sQ9Unjy6Jxoo08ESJDI")
    	  .setOAuthAccessTokenSecret("8iUHuwsy53wMMahRAAXJfXJj7TL92xh0nDoX6HIxt4E2U");
    	
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        StatusListener listener = new StatusListener() {
            public void onStatus(Status status) {
            	
                System.out.println(status.toString()+"\n\n");
            }


            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }


            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

 
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

 
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

 
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        twitterStream.sample();
    }
}
