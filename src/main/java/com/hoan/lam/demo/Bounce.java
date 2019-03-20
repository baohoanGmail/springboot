
package com.hoan.lam.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"category", "email", "event", "ip", "reason", "sg_event_id", "sg_message_id",
    "smtp-id", "status", "timestamp", "tls", "type"})
public class Bounce {

  @JsonProperty("category")
  private List<String> category = null;
  @JsonProperty("email")
  private String email;
  @JsonProperty("event")
  private String event;
  @JsonProperty("ip")
  private String ip;
  @JsonProperty("reason")
  private String reason;
  @JsonProperty("sg_event_id")
  private String sgEventId;
  @JsonProperty("sg_message_id")
  private String sgMessageId;
  @JsonProperty("smtp-id")
  private String smtpId;
  @JsonProperty("status")
  private String status;
  @JsonProperty("timestamp")
  private Integer timestamp;
  @JsonProperty("tls")
  private Integer tls;
  @JsonProperty("type")
  private String type;

  @JsonProperty("category")
  public List<String> getCategory() {
    return category;
  }

  @JsonProperty("category")
  public void setCategory(List<String> category) {
    this.category = category;
  }

  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  @JsonProperty("email")
  public void setEmail(String email) {
    this.email = email;
  }

  @JsonProperty("event")
  public String getEvent() {
    return event;
  }

  @JsonProperty("event")
  public void setEvent(String event) {
    this.event = event;
  }

  @JsonProperty("ip")
  public String getIp() {
    return ip;
  }

  @JsonProperty("ip")
  public void setIp(String ip) {
    this.ip = ip;
  }

  @JsonProperty("reason")
  public String getReason() {
    return reason;
  }

  @JsonProperty("reason")
  public void setReason(String reason) {
    this.reason = reason;
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

  @JsonProperty("smtp-id")
  public String getSmtpId() {
    return smtpId;
  }

  @JsonProperty("smtp-id")
  public void setSmtpId(String smtpId) {
    this.smtpId = smtpId;
  }

  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  @JsonProperty("status")
  public void setStatus(String status) {
    this.status = status;
  }

  @JsonProperty("timestamp")
  public Integer getTimestamp() {
    return timestamp;
  }

  @JsonProperty("timestamp")
  public void setTimestamp(Integer timestamp) {
    this.timestamp = timestamp;
  }

  @JsonProperty("tls")
  public Integer getTls() {
    return tls;
  }

  @JsonProperty("tls")
  public void setTls(Integer tls) {
    this.tls = tls;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }
}
