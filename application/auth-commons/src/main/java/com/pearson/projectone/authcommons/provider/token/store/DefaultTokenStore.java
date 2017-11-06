package com.pearson.projectone.authcommons.provider.token.store;

import com.pearson.projectone.authcommons.service.token.Oauth2TokenStoreService;
import com.pearson.projectone.data.entity.security.oauth2.token.AccessToken;
import com.pearson.projectone.data.entity.security.oauth2.token.RefreshToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.pearson.projectone.authcommons.utils.Oauth2SerializationUtils.deserializeAccessToken;
import static com.pearson.projectone.authcommons.utils.Oauth2SerializationUtils.deserializeAuthentication;
import static com.pearson.projectone.authcommons.utils.Oauth2SerializationUtils.deserializeRefreshToken;
import static com.pearson.projectone.authcommons.utils.Oauth2SerializationUtils.extractTokenKey;
import static com.pearson.projectone.authcommons.utils.Oauth2SerializationUtils.serializeAccessToken;
import static com.pearson.projectone.authcommons.utils.Oauth2SerializationUtils.serializeAuthentication;
import static com.pearson.projectone.authcommons.utils.Oauth2SerializationUtils.serializeRefreshToken;

/**
 * A oauth2 token store implementation that allows for pluggable storage
 * of tokens. This is inspired from
 *
 * @see org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
 */
public class DefaultTokenStore implements TokenStore {

	private static final Log LOG = LogFactory.getLog(DefaultTokenStore.class);

	private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

	@Autowired
	Oauth2TokenStoreService oauthTokenStoreService;

	public DefaultTokenStore() {

	}

	public DefaultTokenStore(Oauth2TokenStoreService oauth2TokenStoreService) {
		this.oauthTokenStoreService = oauth2TokenStoreService;
	}

	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		return readAuthentication(token.getValue());
	}

	@Override
	public OAuth2Authentication readAuthentication(String token) {
		OAuth2Authentication authentication = null;
		String tokenKey = extractTokenKey(token);
		AccessToken accessToken = oauthTokenStoreService.getAccessToken(tokenKey);
		try {
			if (accessToken != null) {
				String authenticationBlobString = accessToken.getAuthentication();
				authentication = deserializeAuthentication(authenticationBlobString);
			} else {
				if (LOG.isInfoEnabled()) {
					LOG.info("Failed to find access token for token " + token);
				}
			}
		} catch (IllegalArgumentException e) {
			LOG.warn("Failed to deserialize authentication for " + token);
			removeAccessToken(token);
		}
		return authentication;
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		String refreshToken = null;
		if (token.getRefreshToken() != null) {
			refreshToken = token.getRefreshToken().getValue();
		}

		if (readAccessToken(token.getValue()) != null) {
			removeAccessToken(token.getValue());
		}

		String tokenKey = extractTokenKey(token.getValue());
		String tokenBase64String = serializeAccessToken(token);
		String authenticationId = authenticationKeyGenerator.extractKey(authentication);
		String username = authentication.isClientOnly() ? null : authentication.getName();
		String clientId = authentication.getOAuth2Request().getClientId();
		String authenticationBase64String = serializeAuthentication(authentication);
		String refreshTokenKey = extractTokenKey(refreshToken);

		AccessToken accessToken = new AccessToken();
		accessToken.setTokenId(tokenKey);
		accessToken.setToken(tokenBase64String);
		accessToken.setUserName(username);
		accessToken.setClientId(clientId);
		accessToken.setAuthenticationId(authenticationId);
		accessToken.setAuthentication(authenticationBase64String);
		accessToken.setRefreshToken(refreshTokenKey);
		oauthTokenStoreService.saveToken(accessToken, authentication);
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		OAuth2AccessToken accessToken = null;

		try {
			String tokenKey = extractTokenKey(tokenValue);
			AccessToken accessTokenObj = oauthTokenStoreService.getAccessToken(tokenKey);
			if (ObjectUtils.isEmpty(accessTokenObj)) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Failed to find access token for token " + tokenValue);
				}
			} else {
				accessToken = deserializeAccessToken(accessTokenObj.getToken());
			}
		} catch (IllegalArgumentException e) {
			LOG.warn("Failed to deserialize access token for " + tokenValue);
			removeAccessToken(tokenValue);
		}

		return accessToken;
	}

	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		removeAccessToken(token.getValue());
	}

	public void removeAccessToken(String token) {
		String tokenKey = extractTokenKey(token);
		oauthTokenStoreService.deleteAccessToken(tokenKey);
	}

	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		String refreshTokenKey = extractTokenKey(refreshToken.getValue());
		String tokenSer = serializeRefreshToken(refreshToken);
		String authenticationSer = serializeAuthentication(authentication);
		RefreshToken refreshTokenObj = new RefreshToken();
		refreshTokenObj.setTokenId(refreshTokenKey);
		refreshTokenObj.setToken(tokenSer);
		refreshTokenObj.setAuthentication(authenticationSer);
		oauthTokenStoreService.saveToken(refreshTokenObj, authentication);
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String token) {
		OAuth2RefreshToken refreshToken = null;

		try {
			String tokenKey = extractTokenKey(token);
			RefreshToken refreshTokenObj = oauthTokenStoreService.getRefreshToken(tokenKey);
			if (ObjectUtils.isEmpty(refreshTokenObj)) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Failed to find refresh token for token " + token);
				}
			} else {
				refreshToken = deserializeRefreshToken(refreshTokenObj.getToken());
			}
		} catch (IllegalArgumentException e) {
			LOG.warn("Failed to deserialize refresh token for token " + token);
			removeRefreshToken(token);
		}

		return refreshToken;
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
		OAuth2Authentication authentication = null;
		String value = token.getValue();
		try {
			String tokenKey = extractTokenKey(value);
			RefreshToken refreshTokenObj = oauthTokenStoreService.getRefreshToken(tokenKey);
			if (ObjectUtils.isEmpty(refreshTokenObj)) {
				if (LOG.isInfoEnabled()) {
					LOG.info("Failed to find refresh token for token " + value);
				}
			} else {
				authentication = deserializeAuthentication(refreshTokenObj.getAuthentication());
			}
		} catch (IllegalArgumentException e) {
			LOG.warn("Failed to deserialize refresh token for token " + value);
			removeRefreshToken(value);
		}
		return authentication;
	}

	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
		removeRefreshToken(token.getValue());
	}

	public void removeRefreshToken(String refreshTokenKey) {
		String tokenKey = extractTokenKey(refreshTokenKey);
		oauthTokenStoreService.deleteRefreshToken(tokenKey);
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		String refreshTokenKey = extractTokenKey(refreshToken.getValue());
		oauthTokenStoreService.deleteAccessTokenWithRefreshToken(refreshTokenKey);
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		OAuth2AccessToken accessToken = null;
		String key = authenticationKeyGenerator.extractKey(authentication);
		try {
			AccessToken accessTokenObj = oauthTokenStoreService.getAccessTokenForAuthenticationId(key);
			if (ObjectUtils.isEmpty(accessTokenObj)) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("Failed to find access token for authentication " + authentication);
				}
			} else {
				accessToken = deserializeAccessToken(accessTokenObj.getToken());
			}
		} catch (IllegalArgumentException e) {
			LOG.error("Could not extract access token for authentication " + authentication, e);
		}

		if (accessToken != null
				&& !key.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
			removeAccessToken(accessToken.getValue());
			// Keep the store consistent (maybe the same user is represented by this authentication
			// but the details have changed)
			storeAccessToken(accessToken, authentication);
		}
		return accessToken;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
		List<AccessToken> tokens = oauthTokenStoreService.getAccessTokens(clientId, userName);
		return mapTokens(tokens);
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		List<AccessToken> tokens = oauthTokenStoreService.getAccessTokens(clientId);
		return mapTokens(tokens);
	}

	private List<OAuth2AccessToken> mapTokens(List<AccessToken> tokens) {
		List<OAuth2AccessToken> accessTokens = new ArrayList<>(0);
		if (tokens != null) {
			for (AccessToken token : tokens) {
				OAuth2AccessToken eachObj = null;
				try {
					eachObj = deserializeAccessToken(token.getToken());
					if (eachObj != null) {
						accessTokens.add(eachObj);
					}
				} catch (IllegalArgumentException e) {
					LOG.warn("Failed to deserialize access token for " + token.getTokenId(), e);
				}
			}
		}
		return accessTokens;
	}

}
