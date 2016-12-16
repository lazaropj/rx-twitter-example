package org.interview.oauth.twitter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class Redis {

	static final Logger logger = LoggerFactory.getLogger(Redis.class);
	
	public static Jedis newInstance() {
		String fullUrl = Env.redisPort();
		logger.info("Redis URL {}", fullUrl);
		if (fullUrl != null) {
			String url = fullUrl.substring("tcp://".length());
			String host = url.split(":")[0];
			Integer port = Integer.parseInt(url.split(":")[1]);
			logger.info("Redis HOST {}, PORT {}", host, port);
			return new Jedis(host, port);
		}
		return new Jedis();
	}

}
