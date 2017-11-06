package com.pearson.projectone.core.support.config

import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

class DefaultWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	/**
	 * Permits all wadls and swagger endpoints for all rest end points
	 * @param web
	 * @throws Exception
	 */
	@Override
	void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/login")
				.antMatchers("/webjars/springfox-swagger-ui/**/*")
				.antMatchers("/swagger-resources*")
				.antMatchers("/swagger-resources/**/*")
				.antMatchers("/swagger-ui.html*")
				.antMatchers("/v2/api-docs")
				.antMatchers(HttpMethod.OPTIONS, "/*")
	}
}
