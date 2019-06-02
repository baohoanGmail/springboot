package com.hoan.lam.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(content = Include.NON_NULL)
public class Product {

	private String id;
	private String productNo;
	private String created;
	private String productName;
	private String orderNo;
	private String returnNo;
	private String status;
	private String shipmentDate;
	private String arriveDate;
	private String usageDate;
	private String returnDate;
	private String preparationDate;
	private String uniqueNo;
	private String group;
}
