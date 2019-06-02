package com.hoan.lam.demo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(content = Include.NON_NULL)
public class RestResponse {

	private String status;
	@JsonProperty(value = "products")
	private Object data;
	private int total;
	private int offset;
	private boolean more;
}
