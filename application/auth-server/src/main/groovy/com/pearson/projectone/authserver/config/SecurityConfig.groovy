package com.pearson.projectone.authserver.config

import com.pearson.projectone.authcommons.config.DefaultGlobalMethodSecurityConfiguration
import com.pearson.projectone.core.support.config.DefaultWebSecurityConfigurerAdapter
import com.pearson.projectone.core.support.filters.SimpleCorsFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.channel.ChannelProcessingFilter

@Configuration
@EnableWebSecurity
class SecurityConfig extends DefaultWebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailService

	@Autowired
	PasswordEncoder passwordEncoder

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService)
				.passwordEncoder(passwordEncoder)
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean()
			throws Exception {
		super.authenticationManagerBean()
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.formLogin().permitAll()
				.and()
				.csrf().disable()
				.addFilterBefore(new SimpleCorsFilter(), ChannelProcessingFilter)
	}
}


@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
class AuthServerMethodSecurityConfig extends DefaultGlobalMethodSecurityConfiguration {

}


