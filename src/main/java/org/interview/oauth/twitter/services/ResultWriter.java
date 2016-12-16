package org.interview.oauth.twitter.services;

import java.io.FileWriter;
import java.io.IOException;

import rx.Observable;

public class ResultWriter {

	private final String fileName;
	
	public ResultWriter(String fileName) {
		this.fileName = fileName;
	}
	
	public Observable<String> write(String content) {
		return Observable.defer(() -> {
			FileWriter writer = null;
			try {
				writer = new FileWriter(fileName);
				writer.write(content);
				return Observable.just(content);
				
			} catch (IOException ex) {
				return Observable.error(ex);
				
			} finally {
				if (writer!=null) {
					try {
						writer.close();
					} catch (IOException ex) {
						return Observable.error(ex);
					}
				}
				
			}
		});
	}
	
}
