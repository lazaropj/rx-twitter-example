package org.interview.oauth.twitter.services;

import java.io.ByteArrayOutputStream;
import java.io.PipedInputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;

public class TweetReader {

	static final Logger logger = LoggerFactory.getLogger(TweetReader.class);
	static final byte DELIMITER = (byte) '\n';

	private final PipedInputStream inputStream;
	private final AtomicBoolean reading;

	public TweetReader(PipedInputStream inputStream) {
		this.inputStream = inputStream;
		this.reading = new AtomicBoolean(true);
	}

	public Observable<String> read() {
		return Observable.create(subscriber -> {
			if (!subscriber.isUnsubscribed()) {
				logger.info("Start reading tweets from piped stream...");
				try {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buffer = new byte[1];
					int read;
					while(reading.get()) {
						read = inputStream.read(buffer, 0, 1);
						if (read != -1) {
							baos.write(buffer, 0, read);
							if (buffer[0] == DELIMITER) {
								String tweet = baos.toString("UTF-8");
								logger.info("New tweet identified. {}", tweet);
								subscriber.onNext(tweet);
								baos = new ByteArrayOutputStream();
							}
						}
					}
					subscriber.onCompleted();
					
				} catch (Exception ex) {
					subscriber.onError(ex);
					
				}
			}
		});
	}
	
	public void stop() {
		this.reading.set(false);
	}
}