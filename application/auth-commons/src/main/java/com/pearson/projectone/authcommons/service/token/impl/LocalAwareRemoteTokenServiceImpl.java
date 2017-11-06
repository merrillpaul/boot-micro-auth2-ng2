package com.pearson.projectone.authcommons.service.token.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestOperations;

/**
 * Checks if the token is available in the local db, if not tries to
 * resolve it from the auth server and caches it locally.
 * <p>
 * 1. loads from local ds if configuration is setup
 * 2. checks whether expired, if expired throws exception
 * 3. if not found tries to resolve from the remote auth server
 * 4. if token successful then caches into local ds
 */
public class LocalAwareRemoteTokenServiceImpl implements ResourceServerTokenServices {
	@Autowired(required = false)
	TokenStore tokenStore; // represents the local token store

	private RemoteTokenServices delegate;

	public LocalAwareRemoteTokenServiceImpl() {
		this.delegate = new RemoteTokenServices();
	}

	public LocalAwareRemoteTokenServiceImpl(RemoteTokenServices delegate) {
		this.delegate = delegate;
	}

	protected final Log LOGGER = LogFactory.getLog(getClass());

	@Override
	public OAuth2Authentication loadAuthentication(String accessTokenValue) throws AuthenticationException, InvalidTokenException {
		OAuth2AccessToken accessToken;
		OAuth2Authentication authentication = null;

		if (tokenStore != null) {
			try {
				accessToken = tokenStore.readAccessToken(accessTokenValue);
				if (ObjectUtils.isEmpty(accessToken)) {
					throw new InvalidTokenException("Invalid access token: " + accessTokenValue);
				} else if (accessToken.isExpired()) {
					tokenStore.removeAccessToken(accessToken);
					throw new InvalidTokenException("Access token expired: " + accessTokenValue);
				}

				authentication = tokenStore.readAuthentication(accessToken);
				if (ObjectUtils.isEmpty(authentication)) {
					// in case of race condition
					throw new InvalidTokenException("Invalid access token: " + accessTokenValue);
				}
			} catch (InvalidTokenException te) {
				LOGGER.warn("invalid token in local token store");
				// handle the error and then let the remote check_token to be fired as a fallback
			}
		}

		if (ObjectUtils.isEmpty(authentication)) {
			// try to get it from remote
			authentication = delegate.loadAuthentication(accessTokenValue);
		}
		return authentication;
	}

	public void setRestTemplate(RestOperations restTemplate) {
		delegate.setRestTemplate(restTemplate);
	}

	public void setCheckTokenEndpointUrl(String checkTokenEndpointUrl) {
		delegate.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
	}

	public void setClientId(String clientId) {
		delegate.setClientId(clientId);
	}

	public void setClientSecret(String clientSecret) {
		delegate.setClientSecret(clientSecret);
	}

	public void setAccessTokenConverter(AccessTokenConverter accessTokenConverter) {
		delegate.setAccessTokenConverter(accessTokenConverter);
	}

	public void setTokenName(String tokenName) {
		delegate.setTokenName(tokenName);
	}

	@Override
	public OAuth2AccessToken readAccessToken(String accessToken) {
		return delegate.readAccessToken(accessToken);
	}
}
