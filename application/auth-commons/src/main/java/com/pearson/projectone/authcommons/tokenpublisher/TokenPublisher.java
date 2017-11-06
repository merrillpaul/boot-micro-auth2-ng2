package com.pearson.projectone.authcommons.tokenpublisher;

import com.pearson.projectone.data.entity.security.oauth2.token.AccessToken;
import com.pearson.projectone.data.entity.security.oauth2.token.RefreshToken;

/**
 * A contract to publish tokens so that they can be distributed for quick access.
 * The implementation should pick a token and place it so that the customer server ( location )
 * has quick access to the token, instead of firing a remote request for oauth2 token validity
 * from the auth server (check_token) .
 */
public interface TokenPublisher {

	void saveAccessToken(AccessToken accessToken, Object principal);

	void saveRefreshToken(RefreshToken refreshToken, Object principal);

	void deleteAccessToken(AccessToken accessToken, Object principal);

	void deleteRefreshToken(RefreshToken refreshToken, Object principal);

	void deleteAccessTokenByRefreshToken(RefreshToken refreshToken, Object principal);
}
