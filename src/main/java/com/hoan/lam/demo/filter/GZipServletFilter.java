package com.hoan.lam.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

import com.hoan.lam.demo.filter.gzip.GZIPResponseWrapper;

public class GZipServletFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String acceptEncoding = httpRequest.getHeader(HttpHeaders.ACCEPT_ENCODING);
		if (acceptEncoding != null) {
			if (acceptEncoding.indexOf("gzip") >= 0) {
				GZIPResponseWrapper gzipResponse = new GZIPResponseWrapper(httpResponse);
				chain.doFilter(request, gzipResponse);
				gzipResponse.finish();
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// CHECKSONAR

	}

}
