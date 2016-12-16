package org.interview.oauth.twitter.services;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.interview.oauth.twitter.rx.TweetStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;

import static rx.Observable.*;
import rx.Observable;
import rx.schedulers.Schedulers;

public class TweetListener {

	static final Logger logger = LoggerFactory.getLogger(TweetListener.class);

	private final String text;

	private final String consumerKey;
	private final String consumerSecret;

	private final PipedInputStream inputStream;
	private final PipedOutputStream outputStream;

	public TweetListener(final String text, final String consumerKey, final String consumerSecret) {
		this.text = text;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.inputStream = new PipedInputStream();
		try {
			this.outputStream = new PipedOutputStream(this.inputStream);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public TweetStream authorizeAndListen() {
		try {
			Authenticator authenticator = new Authenticator(System.out, consumerKey, consumerSecret);
			HttpRequestFactory factory = authenticator.getAuthorizedHttpRequestFactory();
			return listen(factory);

		} catch (Exception ex) {
			return new TweetStream(error(ex), outputStream);

		}
	}

	protected TweetStream listen(HttpRequestFactory factory) {
        try {
            HttpRequest request = factory.buildGetRequest(new GenericUrl("https://stream.twitter.com/1.1/statuses/filter.json?track=" + text));
            Observable<Object> listener = merge(readFrom(this.inputStream), listenAndWriteTo(request, outputStream));
            return new TweetStream(listener, outputStream);

        } catch (Exception ex) {
            return new TweetStream(error(ex), outputStream);

        }
    }

	private Observable<?> listenAndWriteTo(final HttpRequest request, final OutputStream stream) {
		return from(request.executeAsync()).subscribeOn(Schedulers.io()).flatMap(response -> {
			return defer(() -> {
				try {
					logger.info("Reading from Twitter Stream...");
					response.download(stream);
					return empty();
					
				} catch (IOException ex) {
					logger.warn("Stream closed");
					return error(ex);
				}
			});
		});
	}

	private Observable<String> readFrom(PipedInputStream inputStream) {
		return new TweetReader(inputStream).read().subscribeOn(Schedulers.io());
	}
}
