
package com.hoan.lam.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"email", "timestamp", "smtp-id", "event", "category", "sg_event_id",
    "sg_message_id", "response", "attempt"})
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
  @JsonProperty("category")
  private String category;
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

  @JsonProperty("category")
  public String getCategory() {
    return category;
  }

  @JsonProperty("category")
  public void setCategory(String category) {
    this.category = category;
  }

  @JsonProperty("sg_event_id")
  public String getSgEventId() {
    return sgEventId;
  }

  @JsonProperty("sg_event_id")
  public void setSgEventId(String sgEventId) {
    this.sgEventId = sgEventId;
  }

  @JsonProperty("sg_message_id")
  public String getSgMessageId() {
    return sgMessageId;
  }

  @JsonProperty("sg_message_id")
  public void setSgMessageId(String sgMessageId) {
    this.sgMessageId = sgMessageId;
  }

  @JsonProperty("response")
  public String getResponse() {
    return response;
  }

  @JsonProperty("response")
  public void setResponse(String response) {
    this.response = response;
  }

  @JsonProperty("attempt")
  public String getAttempt() {
    return attempt;
  }

  @JsonProperty("attempt")
  public void setAttempt(String attempt) {
    this.attempt = attempt;
  }
}
