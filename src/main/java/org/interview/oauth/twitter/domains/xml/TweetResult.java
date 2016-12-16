package org.interview.oauth.twitter.domains.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.interview.oauth.twitter.domains.json.Tweet;

import java.util.Calendar;

/**
 * Created by leonardo on 11/13/16.
 */
public class TweetResult {

    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    private String text;

    @JacksonXmlProperty(localName = "created_at")
    private Calendar createdAt;

    public TweetResult() {}

    public TweetResult(Tweet status) {
        this.id = status.getId();
        this.text = status.getText();
        this.createdAt = status.getCreatedAt();
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() { return text; }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getCreatedAt() { return createdAt; }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }
}
