package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Email {
	
	private String text;
	private String to;
	private String subject;
	private String file;
	
	@JsonCreator
	public Email(@JsonProperty("text") String text, @JsonProperty("to") String to, @JsonProperty("subject") String subject, @JsonProperty("file") String file) {
		super();
		this.text = text;
		this.to = to;
		this.subject = subject;
		this.file = file;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}