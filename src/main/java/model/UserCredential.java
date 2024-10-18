package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCredential{

	private User user;
	private Credential credential;
	
	@JsonCreator
	public UserCredential(@JsonProperty("user") User user, @JsonProperty("credential") Credential credential) {
		super();
		this.user = user;
		this.credential = credential;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}	
}