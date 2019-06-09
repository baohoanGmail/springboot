package com.hoan.lam.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"all", "rentaling", "not_return_yet", "prepare_day"})
public class FilterResult {

  @JsonProperty("all")
  private Integer all;
  @JsonProperty("rentaling")
  private Integer rentaling;
  @JsonProperty("not_return_yet")
  private Integer notReturnYet;
  @JsonProperty("prepare_day")
  private Integer prepareDay;

  @JsonProperty("all")
  public Integer getAll() {
    return all;
  }

  @JsonProperty("all")
  public void setAll(Integer all) {
    this.all = all;
  }

  @JsonProperty("rentaling")
  public Integer getRentaling() {
    return rentaling;
  }

  @JsonProperty("rentaling")
  public void setRentaling(Integer rentaling) {
    this.rentaling = rentaling;
  }

  @JsonProperty("not_return_yet")
  public Integer getNotReturnYet() {
    return notReturnYet;
  }

  @JsonProperty("not_return_yet")
  public void setNotReturnYet(Integer notReturnYet) {
    this.notReturnYet = notReturnYet;
  }

  @JsonProperty("prepare_day")
  public Integer getPrepareDay() {
    return prepareDay;
  }

  @JsonProperty("prepare_day")
  public void setPrepareDay(Integer prepareDay) {
    this.prepareDay = prepareDay;
  }

}
