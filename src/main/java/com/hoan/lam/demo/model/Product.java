package com.hoan.lam.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(content = Include.NON_NULL)
public class Product {

  @JsonProperty("id")
  private Integer id;
  @JsonProperty("unique_number")
  private String uniqueNumber;
  @JsonProperty("name")
  private String name;
  @JsonProperty("product_no")
  private String productNo;
  @JsonProperty("created")
  private String created;
  @JsonProperty("order_no")
  private String orderNo;
  @JsonProperty("return_no")
  private String returnNo;
  @JsonProperty("status")
  private String status;
  @JsonProperty("shipment_date")
  private String shipmentDate;
  @JsonProperty("arrive_date")
  private String arriveDate;
  @JsonProperty("usage_date")
  private String usageDate;
  @JsonProperty("return_date")
  private String returnDate;
  @JsonProperty("preparation_date")
  private String preparationDate;
  @JsonProperty("group")
  private String group;

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("unique_number")
  public String getUniqueNumber() {
    return uniqueNumber;
  }

  @JsonProperty("unique_number")
  public void setUniqueNumber(String uniqueNumber) {
    this.uniqueNumber = uniqueNumber;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("product_no")
  public String getProductNo() {
    return productNo;
  }

  @JsonProperty("product_no")
  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  @JsonProperty("created")
  public String getCreated() {
    return created;
  }

  @JsonProperty("created")
  public void setCreated(String created) {
    this.created = created;
  }

  @JsonProperty("order_no")
  public String getOrderNo() {
    return orderNo;
  }

  @JsonProperty("order_no")
  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  @JsonProperty("return_no")
  public String getReturnNo() {
    return returnNo;
  }

  @JsonProperty("return_no")
  public void setReturnNo(String returnNo) {
    this.returnNo = returnNo;
  }

  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  @JsonProperty("status")
  public void setStatus(String status) {
    this.status = status;
  }

  @JsonProperty("shipment_date")
  public String getShipmentDate() {
    return shipmentDate;
  }

  @JsonProperty("shipment_date")
  public void setShipmentDate(String shipmentDate) {
    this.shipmentDate = shipmentDate;
  }

  @JsonProperty("arrive_date")
  public String getArriveDate() {
    return arriveDate;
  }

  @JsonProperty("arrive_date")
  public void setArriveDate(String arriveDate) {
    this.arriveDate = arriveDate;
  }

  @JsonProperty("usage_date")
  public String getUsageDate() {
    return usageDate;
  }

  @JsonProperty("usage_date")
  public void setUsageDate(String usageDate) {
    this.usageDate = usageDate;
  }

  @JsonProperty("return_date")
  public String getReturnDate() {
    return returnDate;
  }

  @JsonProperty("return_date")
  public void setReturnDate(String returnDate) {
    this.returnDate = returnDate;
  }

  @JsonProperty("preparation_date")
  public String getPreparationDate() {
    return preparationDate;
  }

  @JsonProperty("preparation_date")
  public void setPreparationDate(String preparationDate) {
    this.preparationDate = preparationDate;
  }

  @JsonProperty("group")
  public String getGroup() {
    return group;
  }

  @JsonProperty("group")
  public void setGroup(String group) {
    this.group = group;
  }
}
