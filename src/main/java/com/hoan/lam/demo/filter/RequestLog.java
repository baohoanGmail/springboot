package com.hoan.lam.demo.filter;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestLog {

	private String uri;
	private String url;
	private String method;
	private String contentType;
	private List<Param> params;
	private List<Header> headers;
	private Object body;
	private String location;
	private String ourSignature;
	private Boolean verifyResult;
}
