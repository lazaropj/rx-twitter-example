package org.interview.oauth.twitter.domains.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.interview.oauth.twitter.domains.json.User;

import java.util.Calendar;

public class UserResult {

    @JacksonXmlProperty(isAttribute = true)
	private Long id;

	private String name;

	@JacksonXmlProperty(localName = "screen_name")
	private String screenName;
	
	@JacksonXmlProperty(localName = "created_at")
	private Calendar createdAt;

	public UserResult() {}

	public UserResult(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.screenName = user.getScreenName();
		this.createdAt = user.getCreatedAt();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserResult other = (UserResult) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "UserResult [id=" + id + ", name=" + name + ", screenName=" + screenName + ", createdAt=" + createdAt + "]";
	}

}
