package org.interview.oauth.twitter.services;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;
import org.interview.oauth.twitter.domains.json.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;

public class TweetBackgroundTracker {

	static final Logger logger = LoggerFactory.getLogger(TweetBackgroundTracker.class);

	private final TweetTracker tracker;
	private final Set<Pair<String, Tweet>> tweets;

	public TweetBackgroundTracker(TweetTracker tracker) {
		this.tracker = tracker;
		this.tweets = new HashSet<>();
	}

	public void trackWhenPossible(String text, Tweet status) {
		logger.info("Registering status for a future retrying");
		this.tweets.add(Pair.of(text, status));
	}

	public TweetBackgroundTracker start() {
		Observable.interval(5, TimeUnit.SECONDS)
			.flatMap(num -> track())
			.subscribe(num -> logger.info("Status Background tracked"));
		return this;
	}

	protected Observable<Long> track() {
		Observable<String> obs = tracker.checkConnection() ? Observable.just("ok") : Observable.empty();
		return obs.flatMap(message -> Observable.from(tweets))
				.flatMap(pair -> tracker.track(pair.getLeft(), pair.getRight()));
	}

}
