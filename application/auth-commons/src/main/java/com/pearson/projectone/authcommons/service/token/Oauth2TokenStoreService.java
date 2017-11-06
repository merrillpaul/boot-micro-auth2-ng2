package com.pearson.projectone.authcommons.service.token;

import com.pearson.projectone.data.entity.security.oauth2.token.AccessToken;
import com.pearson.projectone.data.entity.security.oauth2.token.RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.List;

/**
 * Interface for managing oauth2 access tokens and refresh tokens
 */
public interface Oauth2TokenStoreService {
	AccessToken getAccessToken(String tokenKey);

	AccessToken saveToken(AccessToken accessToken, OAuth2Authentication authentication);

	void deleteAccessToken(String tokenKey);

	RefreshToken saveToken(RefreshToken refreshToken, OAuth2Authentication authentication);

	RefreshToken getRefreshToken(String tokenKey);

	void deleteRefreshToken(String tokenKey);

	void deleteAccessTokenWithRefreshToken(String tokenKey);

	AccessToken getAccessTokenForAuthenticationId(String authenticationId);

	List<AccessToken> getAccessTokens(String clientId, String username);

	List<AccessToken> getAccessTokens(String clientId);

}
