package com.hoan.lam.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.hoan.lam.demo.filter.GZipServletFilter;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean<RequestResponseFiIter> loggingFilter() {
//		FilterRegistrationBean<RequestResponseFiIter> registrationBean = new FilterRegistrationBean<>();
//
//		registrationBean.setFilter(new RequestResponseFiIter());
//		registrationBean.addUrlPatterns("*");
//
//		return registrationBean;
//	}
	
//	@Bean
//	public FilterRegistrationBean<GZipServletFilter> loggingFilter() {
//		FilterRegistrationBean<GZipServletFilter> registrationBean = new FilterRegistrationBean<>();

//		registrationBean.setFilter(new GZipServletFilter());
//		registrationBean.addUrlPatterns("*");

//		return registrationBean;
//	}
}
