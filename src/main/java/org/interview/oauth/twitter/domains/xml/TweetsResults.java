package org.interview.oauth.twitter.domains.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName="tweets_results")
public class TweetsResults {

	@JacksonXmlElementWrapper(localName="tweets")
	@JacksonXmlProperty(localName="tweet")
	private List<TweetsResult> results;

	public TweetsResults() {
		this.results = new ArrayList<>();
	}

    public List<TweetsResult> getResults() {
        return results;
    }

    public void setResults(List<TweetsResult> results) {
        this.results = results;
    }

	public void addResult(TweetsResult result) {
		this.results.add(result);
	}

}
