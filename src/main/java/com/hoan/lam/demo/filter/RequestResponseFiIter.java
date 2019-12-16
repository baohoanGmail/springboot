package com.hoan.lam.demo.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import com.hoan.lam.demo.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestResponseFiIter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequestWrapper reqWrapper = null;

		RequestLog requestLog = new RequestLog();

		try {
			requestLog.setUri(req.getRequestURI());
			requestLog.setUrl(req.getRequestURL().toString());
			requestLog.setContentType(req.getContentType());
			requestLog.setMethod(req.getMethod());
			requestLog.setLocation("FORM_ENCODED");
			if (HttpMethod.POST.name().equals(req.getMethod())
					&& StringUtils.lowerCase(req.getContentType()).contains("application/json")) {
				reqWrapper = new HttpServletRequestWrapper(req);
				requestLog.setBody(IOUtils.toString(reqWrapper.getInputStream(), StandardCharsets.UTF_8.toString()));
				requestLog.setLocation("JSON");
			}

			requestLog.setParams(getParameters(req));
			requestLog.setHeaders(getHeaders(req));
		} catch (Exception ex) {
			requestLog.setLocation(ex.getCause().getMessage());
		} finally {
			log.info("FINALLY LOG: {}", JsonUtil.toJson(requestLog));
		}
		// ALLOW pass filter entry
		if ("JSON".equals(requestLog.getLocation())) {
			filterChain.doFilter(reqWrapper, resp);
		} else {
			filterChain.doFilter(req, resp);
		}
	}

	private List<Param> getParameters(HttpServletRequest request) {
		List<Param> params = new ArrayList<>();
		Enumeration<String> paramNames = request.getParameterNames();

		if (!paramNames.hasMoreElements()) {
			return params;
		}
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			params.add(new Param(name, request.getParameterValues(name)));
		}
		return params;
	}

	private List<Header> getHeaders(HttpServletRequest request) {
		List<Header> params = new ArrayList<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		if (!headerNames.hasMoreElements()) {
			return params;
		}
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			params.add(new Header(name, request.getHeader(name)));
		}
		return params;
	}

}
