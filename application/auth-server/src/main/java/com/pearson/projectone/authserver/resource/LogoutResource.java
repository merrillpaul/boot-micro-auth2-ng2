package com.pearson.projectone.authserver.resource;

import com.pearson.projectone.core.support.rest.RestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;

@RestApi
@Component
public class LogoutResource {


	@Autowired
	private ConsumerTokenServices consumerTokenServices;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("")
	public void logout(Principal principal) {
		String token = getToken(principal);
		if (token != null) {
			consumerTokenServices.revokeToken(token);
		}
	}


	private String getToken(Principal principal) {
		String token = null;
		if (principal instanceof OAuth2Authentication) {
			OAuth2Authentication authentication = (OAuth2Authentication) principal;
			if (authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
				token = details.getTokenValue();
			}
		}
		return token;
	}
}
