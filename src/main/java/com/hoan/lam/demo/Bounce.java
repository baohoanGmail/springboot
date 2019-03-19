
package com.hoan.lam.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "email", "timestamp", "smtp-id", "event", "categories", "sg_event_id", "sg_message_id", "response",
		"attempt" })
// @JsonPropertyOrder({"email", "timestamp", "event"})
public class Bounce {

	@JsonProperty("email")
	private String email;
	@JsonProperty("timestamp")
	private Integer timestamp;
	@JsonProperty("event")
	private String event;
	@JsonProperty("smtp-id")
	private String smtpId;
	@JsonProperty("categories")
	private List<String> categories;
	@JsonProperty("sg_event_id")
	private String sgEventId;
	@JsonProperty("sg_message_id")
	private String sgMessageId;
	@JsonProperty("response")
	private String response;
	@JsonProperty("attempt")
	private String attempt;

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("timestamp")
	public Integer getTimestamp() {
		return timestamp;
	}

	@JsonProperty("event")
	public String getEvent() {
		return event;
	}

	@JsonProperty("smtp-id")
	public String getSmtpId() {
		return smtpId;
	}

	@JsonProperty("smtp-id")
	public void setSmtpId(String smtpId) {
		this.smtpId = smtpId;
	}

	@JsonProperty("categories")
	public List<String> getCategories() {
		return categories;
	}

	@JsonProperty("sg_event_id")
	public String getSgEventId() {
		return sgEventId;
	}

	@JsonProperty("sg_message_id")
	public String getSgMessageId() {
		return sgMessageId;
	}

	@JsonProperty("response")
	public String getResponse() {
		return response;
	}

	@JsonProperty("attempt")
	public String getAttempt() {
		return attempt;
	}
}
