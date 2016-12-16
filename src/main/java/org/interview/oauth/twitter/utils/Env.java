package org.interview.oauth.twitter.utils;

public class Env {
	
	public static String redisPort() {
		return System.getenv("REDIS_PORT");
	}

}
