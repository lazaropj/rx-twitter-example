package org.interview.oauth.twitter.services;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class TweetMapper {

    static final String TWITTER_FORMAT = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
    static final String RESULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss ZZZZZ";

	public static ObjectMapper newJsonInstance() {
		ObjectMapper mapper = new ObjectMapper().setDateFormat(new SimpleDateFormat(TWITTER_FORMAT, Locale.US));
		mapper.writer().withRootName("result");
		return mapper;
	}
	public static XmlMapper newXmlInstance() {
		XmlMapper mapper = new XmlMapper();
		return mapper;
	}

}
