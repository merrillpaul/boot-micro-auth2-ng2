package com.pearson.projectone.core.support.filters;

import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A Servlet filter to allow pre-flight CORS OPTIONS request without needing
 * to authenticate. Typically this filter needs to be added as the first in the order
 * of filters
 * <p>
 * An example config that might use this is shown below ( Note the setOrder method ):
 * <code>
 *
 * @Bean public FilterRegistrationBean corsFilterChain() {
 * FilterRegistrationBean registration = new FilterRegistrationBean(new SimpleCorsFilter());
 * registration.setOrder(Integer.MIN_VALUE);
 * registration.setName("simpleCorsFilter");
 * return registration;
 * }
 * </code>
 */
public class SimpleCorsFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException {
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type, xsrf-token");
		response.setHeader("Access-Control-Expose-Headers", "xsrf-token");
		if (CorsUtils.isPreFlightRequest(request)) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			filterChain.doFilter(request, response);
		}
	}
}
