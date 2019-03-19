package com.hoan.lam.demo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"emails"})
public class DeleteMailRequest {

  @JsonProperty("emails")
  private Set<String> emails = null;


  @JsonProperty("emails")
  public Set<String> getEmails() {
    return emails;
  }

  @JsonProperty("emails")
  public void setEmails(Set<String> emails) {
    this.emails = emails;
  }
}
