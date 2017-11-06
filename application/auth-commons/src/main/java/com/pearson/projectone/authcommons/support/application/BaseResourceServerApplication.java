package com.pearson.projectone.authcommons.support.application;

import com.pearson.projectone.authcommons.provider.token.converters.ResourceServerAccessTokenConverter;
import com.pearson.projectone.authcommons.service.user.CurrentUserService;
import com.pearson.projectone.authcommons.service.user.impl.ResourceCurrentUserService;
import com.pearson.projectone.core.support.application.BaseServerApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Serves as a base class for all resource server applications for eg customer and global applications
 */
public abstract class BaseResourceServerApplication extends BaseServerApplication {

	@Bean
	public AccessTokenConverter accessTokenConverter() {
		return new ResourceServerAccessTokenConverter();
	}

	@Bean
	public CurrentUserService currentUserService() {
		return new ResourceCurrentUserService();
	}

	@RequestMapping("/user")
	public ResponseEntity<?> user() {
		return ResponseEntity.ok(currentUserService().getCurrentUser());
	}
}
