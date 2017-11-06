package com.pearson.projectone.authcommons.service.token.impl;

import com.pearson.projectone.authcommons.service.token.Oauth2TokenStoreService;
import com.pearson.projectone.authcommons.tokenpublisher.TokenPublisher;
import com.pearson.projectone.authcommons.utils.Oauth2SerializationUtils;
import com.pearson.projectone.data.entity.security.oauth2.token.AccessToken;
import com.pearson.projectone.data.entity.security.oauth2.token.RefreshToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.List;

/**
 * Service class to manage access and refresh tokens. This service class works in the
 * auth server to persist and distribute tokens. This same service works in the customer side
 * to persist locally.
 */
public abstract class AbstractOauth2TokenStoreService implements Oauth2TokenStoreService {

	private final Log LOG = LogFactory.getLog(this.getClass());

	@Value("${config.auth_server}")
	boolean authServerMode;

	@Autowired(required = false)
	TokenPublisher tokenPublisher;

	@Override
	public AccessToken getAccessToken(String tokenKey) {
		return getOneAccessToken(tokenKey);
	}

	@Override
	public AccessToken saveToken(AccessToken accessToken, OAuth2Authentication authentication) {
		AccessToken token = persistAccessToken(accessToken);
		if (authServerMode) {
			tokenPublisher.saveAccessToken(accessToken, authentication.getPrincipal());
		}
		return token;
	}

	@Override
	public void deleteAccessToken(String tokenKey) {
		AccessToken accessToken = getAccessToken(tokenKey);
		removeAccessToken(tokenKey);
		if (authServerMode && accessToken != null) {
			try {
				Authentication authentication = Oauth2SerializationUtils
						.deserializeAuthentication(accessToken.getAuthentication());
				tokenPublisher.deleteAccessToken(accessToken, authentication.getPrincipal());
			} catch (IllegalArgumentException e) {
				LOG.warn("Failed to deserialize authentication from access token for token " + tokenKey, e);
			}
		}
	}

	@Override
	public RefreshToken saveToken(RefreshToken refreshToken, OAuth2Authentication authentication) {
		RefreshToken token = persistRefreshToken(refreshToken);
		if (authServerMode) {
			tokenPublisher.saveRefreshToken(refreshToken, authentication.getPrincipal());
		}
		return token;
	}

	@Override
	public RefreshToken getRefreshToken(String tokenKey) {
		return getOneRefreshToken(tokenKey);
	}

	@Override
	public void deleteRefreshToken(String tokenKey) {
		RefreshToken refreshToken = getRefreshToken(tokenKey);
		removeRefreshToken(tokenKey);
		if (authServerMode && refreshToken != null) {
			try {
				Authentication authentication = Oauth2SerializationUtils.deserializeAuthentication(refreshToken.getAuthentication());
				tokenPublisher.deleteRefreshToken(refreshToken, authentication.getPrincipal());
			} catch (IllegalArgumentException e) {
				LOG.warn("Failed to deserialize authentication from access token for token " + tokenKey, e);
			}
		}
	}

	@Override
	public void deleteAccessTokenWithRefreshToken(String tokenKey) {
		RefreshToken refreshToken = getRefreshToken(tokenKey);
		removeAccessTokenWithRefreshTokenKey(tokenKey);
		if (authServerMode && refreshToken != null) {
			try {
				Authentication authentication = Oauth2SerializationUtils.deserializeAuthentication(refreshToken.getAuthentication());
				tokenPublisher.deleteAccessTokenByRefreshToken(refreshToken, authentication.getPrincipal());
			} catch (IllegalArgumentException e) {
				LOG.warn("Failed to deserialize authentication from access token for token " + tokenKey, e);
			}
		}
	}

	@Override
	public AccessToken getAccessTokenForAuthenticationId(String authenticationId) {
		return findAccessTokenByAuthenticationId(authenticationId);
	}

	@Override
	public List<AccessToken> getAccessTokens(String clientId, String username) {
		return findAccessTokens(clientId, username);
	}

	@Override
	public List<AccessToken> getAccessTokens(String clientId) {
		return findAccessTokens(clientId);
	}

	abstract AccessToken getOneAccessToken(String tokenKey);

	abstract RefreshToken getOneRefreshToken(String tokenKey);

	abstract AccessToken persistAccessToken(AccessToken accessToken);

	abstract RefreshToken persistRefreshToken(RefreshToken refreshToken);

	abstract void removeAccessToken(String tokenKey);

	abstract void removeRefreshToken(String tokenKey);

	abstract void removeAccessTokenWithRefreshTokenKey(String tokenKey);

	abstract AccessToken findAccessTokenByAuthenticationId(String authenticationId);

	abstract List<AccessToken> findAccessTokens(String clientId, String username);

	abstract List<AccessToken> findAccessTokens(String clientId);

	public boolean isAuthServerMode() {
		return authServerMode;
	}

	public void setAuthServerMode(boolean authServerMode) {
		this.authServerMode = authServerMode;
	}

	public TokenPublisher getTokenPublisher() {
		return tokenPublisher;
	}

	public void setTokenPublisher(TokenPublisher tokenPublisher) {
		this.tokenPublisher = tokenPublisher;
	}
}
