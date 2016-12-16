package org.interview.oauth.twitter.rx;

import java.io.IOException;
import java.io.OutputStream;

import rx.Observable;

public class TweetStream {

	private final Observable<Object> observable;
	private final OutputStream outputStream;
	
	public TweetStream(Observable<Object> observable, OutputStream outputStream) {
		super();
		this.observable = observable;
		this.outputStream = outputStream;
	}

	public Observable<Object> getObservable() {
		return observable;
	}

	public void stop() {
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
