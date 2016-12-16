package org.interview.oauth.twitter.services;

import java.io.IOException;

import org.interview.oauth.twitter.domains.json.Tweet;
import org.interview.oauth.twitter.domains.xml.TweetsResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import rx.Observable;

public class TweetParser {

	static final Logger logger = LoggerFactory.getLogger(TweetParser.class);

	private final ObjectMapper mapper;

	public TweetParser(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public Observable<Tweet> tweetFromJson(Object json) {
		try {
			logger.info("Mapping new tweet from Twitter");
			return Observable.just(mapper.readValue(json.toString(), Tweet.class));
		} catch (IOException ex) {
			logger.error("Not possible to read the Twitter tweet", ex);
			return Observable.error(ex);
		}
	}

	public Observable<String> tweetsToXml(TweetsResults tweetsResults) {
		try {
			XmlMapper mapper = TweetMapper.newXmlInstance();
			String xml = mapper.writeValueAsString(tweetsResults);
			logger.info("Mapping Tweets to xml...");
			return Observable.just(xml);
		} catch (Exception ex) {
			logger.error("Not possible to parse the Tweet", ex);
			return Observable.error(ex);
		}
	}

}
