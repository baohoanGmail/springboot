package com.hoan.lam.demo.filter;

import java.util.List;
import lombok.Data;

@Data
public class RequestLog {

	private String uri;
	private String url;
	private String method;
	private String contentType;
	private List<Param> params;
	private List<Header> headers;
	private Object body;
	private String location;
}
