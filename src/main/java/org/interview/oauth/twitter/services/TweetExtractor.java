package org.interview.oauth.twitter.services;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.interview.oauth.twitter.domains.json.Tweet;
import org.interview.oauth.twitter.domains.json.User;
import org.interview.oauth.twitter.domains.xml.TweetsResult;
import org.interview.oauth.twitter.domains.xml.TweetsResults;
import org.interview.oauth.twitter.rx.TweetStream;
import org.interview.oauth.twitter.utils.Redis;

import com.fasterxml.jackson.databind.ObjectMapper;

import rx.Observable;
import rx.Single;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

public class TweetExtractor {

	private final ObjectMapper mapper;
	private final TweetParser parser;
	private final TweetTracker tracker;

	private final TweetStream stream;
	
	public TweetExtractor(TweetStream stream) {
		super();
		this.stream = stream;
		this.mapper = TweetMapper.newJsonInstance();
		this.parser = new TweetParser(mapper);
		this.tracker = new TweetTracker(mapper, Redis.newInstance());
	}
	
	public Observable<String> extractAsXml(String text) {
		return stream.getObservable()
				.flatMap(parser::tweetFromJson)
				.buffer(30, TimeUnit.SECONDS, 100)
				.first()
				.doOnNext(stop(stream))
				.flatMap(Observable::from)
				.flatMap(track(text))
				.groupBy(tweetUser())
				.sorted(byCreatedDate())
				.flatMap(sortedTweets())
				.scan(new TweetsResults(), aggregateTweets())
				.last()
				.flatMap(parser::tweetsToXml);
	}

	private Action1<List<Tweet>> stop(TweetStream stream) {
		return tweets -> stream.stop();
	}

	private Func1<Tweet, Observable<Tweet>> track(String text) {
		return tweet -> tracker.track(text, tweet).map(result -> tweet);
	}

	private Func1<Tweet, User> tweetUser() {
		return tweet -> tweet.getUser();
	}

	private Func2<GroupedObservable<User, Tweet>, GroupedObservable<User, Tweet>, Integer> byCreatedDate() {
		return (previous, next) -> previous.getKey().getCreatedAt().before(next.getKey().getCreatedAt()) ? -1 : 0;
	}

	private Func1<GroupedObservable<User, Tweet>, Observable<List<Tweet>>> sortedTweets() {
		return (grouped) -> {
			return grouped.toList().flatMap(Observable::from).sorted((previous, next) -> {
				return previous.getCreatedAt().before(next.getCreatedAt()) ? -1 : 0;
			}).toList();
		};
	}
	
	private Func2<TweetsResults, List<Tweet>, TweetsResults> aggregateTweets() {
		return (tweetsResults, tweets) -> {
			tweetsResults.addResult(new TweetsResult(tweets));
			return tweetsResults;
		};
	}

}
