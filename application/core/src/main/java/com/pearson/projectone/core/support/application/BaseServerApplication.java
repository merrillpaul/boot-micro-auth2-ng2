package com.pearson.projectone.core.support.application;

import com.pearson.projectone.core.support.filters.SimpleCorsFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This serves as a base class for all server application instances to extend on
 */
@ComponentScan(basePackages = {"com.pearson.projectone"})
public abstract class BaseServerApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(this.getClass());
	}

	/**
	 * Returns a bcrypt based password encoder. Typically used for encrypting user passwords
	 *
	 * @return an instance of PasswordEncoder
	 */
	@Bean
	@Primary
	@Profile({"dev"})
	public PasswordEncoder devpasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	@Primary
	@Profile({"!dev"})
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configures CORS support
	 *
	 * @return an instance of a webmvc configurer adapter
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO the allowable origins should be based on the environment from application.yml
				registry.addMapping("/*").allowedOrigins("*").allowedMethods(
						RequestMethod.POST.name(), RequestMethod.GET.name(), RequestMethod.OPTIONS.name(),
						RequestMethod.PUT.name(), RequestMethod.DELETE.name()
				);
			}
		};
	}

	// TO allow preflight PUTS
	@Bean
	public FilterRegistrationBean corsFilterChain() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new SimpleCorsFilter());
		registration.setOrder(Integer.MIN_VALUE);
		registration.setName("simpleCorsFilter");
		return registration;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public JsonParser jsonParser() {
		return new JacksonJsonParser();
	}
}
