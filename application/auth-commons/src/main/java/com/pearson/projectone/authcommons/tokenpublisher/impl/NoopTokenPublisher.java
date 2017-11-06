package com.pearson.projectone.authcommons.tokenpublisher.impl;

import com.pearson.projectone.authcommons.tokenpublisher.TokenPublisher;
import com.pearson.projectone.data.entity.security.oauth2.token.AccessToken;
import com.pearson.projectone.data.entity.security.oauth2.token.RefreshToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A placeholder token publisher
 */
public class NoopTokenPublisher implements TokenPublisher {

	private final static Log LOG = LogFactory.getLog(NoopTokenPublisher.class);

	@Override
	public void saveAccessToken(AccessToken accessToken, Object principal) {
		LOG.debug("save access Token " + accessToken + " : " + principal);
	}

	@Override
	public void saveRefreshToken(RefreshToken refreshToken, Object principal) {
		LOG.debug("save refresh Token " + refreshToken + " : " + principal);
	}

	@Override
	public void deleteAccessToken(AccessToken accessToken, Object principal) {
		LOG.debug("delete access Token " + accessToken + " : " + principal);
	}

	@Override
	public void deleteRefreshToken(RefreshToken refreshToken, Object principal) {
		LOG.debug("delete refresh Token " + refreshToken + " : " + principal);
	}

	@Override
	public void deleteAccessTokenByRefreshToken(RefreshToken refreshToken, Object principal) {
		LOG.debug("save access Token by refresh Token" + refreshToken + " : " + principal);
	}
}
