package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Credential {

    private String email;
    private String password;
    private String role;
    private Integer user;
    
 	@JsonCreator
	public Credential(@JsonProperty("email") String email, @JsonProperty("password") String password, 
			@JsonProperty("role") String role, @JsonProperty("user") Integer user) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}
}