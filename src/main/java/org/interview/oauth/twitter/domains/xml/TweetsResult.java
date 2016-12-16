package org.interview.oauth.twitter.domains.xml;

import java.util.List;
import java.util.stream.Collectors;

import org.interview.oauth.twitter.domains.json.Tweet;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="tweet")
public class TweetsResult {

    private UserResult user;

	@JacksonXmlElementWrapper(localName="actions")
	@JacksonXmlProperty(localName="actions")
	private List<TweetResult> actions;

	public TweetsResult() {}
	
	public TweetsResult(List<Tweet> actions) {
		super();
        if (!actions.isEmpty())
            this.user = new UserResult(actions.get(0).getUser());
		this.actions = actions.stream().map(status -> new TweetResult(status)).collect(Collectors.toList());
	}

    public List<TweetResult> getActions() {
        return actions;
    }

    public void setActions(List<TweetResult> actions) {
        this.actions = actions;
    }

	public UserResult getUser() { return user; }

    @JacksonXmlProperty(localName = "total-tweets", isAttribute = true)
    public Integer getTotal() {
        return actions.size();
    }

    public void setTotal(Integer total) {}
}
