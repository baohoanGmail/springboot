package com.hoan.lam.demo.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.hoan.lam.demo.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GZipServletFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("Init GzipRequestFilter");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest requestWrapper = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
//		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) req);
//		requestWrapper.getParameterMap(); // needed for caching!!
		
		String now = LocalDateTime.now().toString();
		log.info("[B] ------------------------ {} -----------------------------------------------------------", now);
		RequestLog logger = new RequestLog();
		logger.setBody(JsonUtil.getValueOfFirst(requestWrapper.getParameterMap()));
		logger.setMethod(requestWrapper.getMethod());
		logger.setContentType(requestWrapper.getContentType());
		logger.setUri(requestWrapper.getRequestURI());
		logger.setUrl(requestWrapper.getRequestURL().toString());
//		String body = IOUtils.toString(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8.name());
//		logger.setBody(body);

		logger.setParams(getParameters(requestWrapper));
		logger.setHeaders(getHeaders(requestWrapper));

		log.info("Filter: {}", logger);
		log.info("[E] ------------------------ {} ----------------------------------------------------------", now);

		chain.doFilter(requestWrapper, response);
	}

	@Override
	public void destroy() {

	}

	private String getRequestBody(final HttpServletRequest request) throws IOException {
		MultiReadRequestWrapper requestWrapper = new MultiReadRequestWrapper(request);
		return IOUtils.toString(requestWrapper.getInputStream(), StandardCharsets.UTF_8);
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

	private class GZIPServletRequestWrapper extends HttpServletRequestWrapper {

		public GZIPServletRequestWrapper(HttpServletRequest request) {
			super(request);
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			return new GZIPServletInputStream(super.getInputStream());
		}

		@Override
		public BufferedReader getReader() throws IOException {
			return new BufferedReader(new InputStreamReader(new GZIPServletInputStream(super.getInputStream())));
		}
	}

	private class GZIPServletInputStream extends ServletInputStream {
		private InputStream input;

		public GZIPServletInputStream(InputStream input) throws IOException {
			this.input = new GZIPInputStream(input);
		}

		@Override
		public int read() throws IOException {
			return input.read();
		}

		@Override
		public boolean isFinished() {
			boolean finished = false;
			try {
				if (input.available() == 0) {
					finished = true;
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return finished;
		}

		@Override
		public boolean isReady() {
			boolean ready = false;
			try {
				if (input.available() > 0) {
					ready = true;
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return ready;
		}

		@Override
		public void setReadListener(ReadListener listener) {
		}
	}
}