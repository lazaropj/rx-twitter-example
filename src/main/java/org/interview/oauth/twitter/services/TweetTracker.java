package org.interview.oauth.twitter.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.interview.oauth.twitter.domains.json.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import rx.Observable;

public class TweetTracker {

	static final Logger logger = LoggerFactory.getLogger(TweetTracker.class);
	
	private static final String TRACKING_DATE_FORMAT = "dd.MM.yyyy.HH.mm.ss";
	private static final String TRACKING_KEY_FORMAT = "twitter:tweets:%s:%s";

	private final SimpleDateFormat format;
	private final TweetBackgroundTracker backgroundTracker;
	private final Jedis jedis;
	private final ObjectMapper mapper;

	public TweetTracker(ObjectMapper mapper, Jedis jedis) {
		this.format = new SimpleDateFormat(TRACKING_DATE_FORMAT);
		this.mapper = mapper;
		this.jedis = jedis;
		this.backgroundTracker = setBackgroundTracker();
	}
	
	private TweetBackgroundTracker setBackgroundTracker() {
		logger.info("Starting Status Background Tracker");
		return new TweetBackgroundTracker(this).start();
	}

	public Observable<Long> track(String text, Tweet status) {
		return Observable.defer(() -> {
			try {
				final String key = String.format(TRACKING_KEY_FORMAT, text, parseDate(status.getCreatedAt()));
				final String json = mapper.writeValueAsString(status);
				logger.info("Tracking Status ID {}", status.getId());
				return Observable.just(jedis.sadd(key, json));
				
			} catch (JedisConnectionException ex) {
				logger.warn("Redis Server is off");
				backgroundTracker.trackWhenPossible(text, status);
				return Observable.just(0L);
				
			} catch (JsonProcessingException ex) {
				logger.warn("Error trying to parse JSON", ex);
				return Observable.error(ex);

			}
		});

	}
	
	public Boolean checkConnection() {
		try {
			jedis.echo("connection");
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public Jedis getJedis() {
		return this.jedis;
	}
	
	private String parseDate(Calendar date) {
		return format.format(date.getTime());
	}

}
