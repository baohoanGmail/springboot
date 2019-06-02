package com.hoan.lam.demo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorElement {

	private String timestamp;
	private String message;
	private String field;

}
