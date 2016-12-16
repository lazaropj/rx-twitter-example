package org.interview.oauth.twitter;

import org.interview.oauth.twitter.services.ResultWriter;
import org.interview.oauth.twitter.services.TweetExtractor;
import org.interview.oauth.twitter.services.TweetListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
	
	static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws InterruptedException {
		final String resultFileName = "tweets_response.xml";
		final String text = "trump";
		final String consumerKey = "8fKSsi3MkQZd1q92iYRzX0lKT";
		final String consumerSecret = "6tcCuZDD66AMaCAqn0FmhX5A3ZMViptMfPgpgnLF4Lod2GZqzk";
		final TweetListener listener = new TweetListener(text, consumerKey, consumerSecret);
		
		ResultWriter writer = new ResultWriter(resultFileName);
		
		new TweetExtractor(listener.authorizeAndListen())
			.extractAsXml(text)
			.flatMap(writer::write)
			.subscribe(xml -> {
				logger.info("Tweet result handled successfully. ", xml);
				
			}, Throwable::printStackTrace, () -> {
				logger.info("Work done.");
				
			});
		
		Thread.sleep(100000);
	}
	
}
