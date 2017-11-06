package com.pearson.projectone.authcommons.service.token.impl;

import com.pearson.projectone.data.dao.security.oauth2.token.AccessTokenDao;
import com.pearson.projectone.data.dao.security.oauth2.token.RefreshTokenDao;
import com.pearson.projectone.data.entity.security.oauth2.token.AccessToken;
import com.pearson.projectone.data.entity.security.oauth2.token.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Use dynamodb based repositories to manage oauth2 tokens
 */
public class DefaultTokenStoreServiceImpl extends AbstractOauth2TokenStoreService {

	@Autowired
	AccessTokenDao accessTokenDao;

	@Autowired
	RefreshTokenDao refreshTokenDao;

	@Override
	AccessToken getOneAccessToken(String tokenKey) {
		return accessTokenDao.findOne(tokenKey);
	}

	@Override
	RefreshToken getOneRefreshToken(String tokenKey) {
		return refreshTokenDao.findOne(tokenKey);
	}

	@Override
	AccessToken persistAccessToken(AccessToken accessToken) {
		return accessTokenDao.save(accessToken);
	}

	@Override
	RefreshToken persistRefreshToken(RefreshToken refreshToken) {
		return refreshTokenDao.save(refreshToken);
	}

	@Override
	void removeAccessToken(String tokenKey) {
		accessTokenDao.delete(tokenKey);
	}

	@Override
	void removeRefreshToken(String tokenKey) {
		refreshTokenDao.delete(tokenKey);
	}

	@Override
	void removeAccessTokenWithRefreshTokenKey(String tokenKey) {
		AccessToken accessToken = accessTokenDao.findByRefreshToken(tokenKey);
		if (accessToken != null) {
			removeAccessToken(accessToken.getTokenId());
		}
	}

	@Override
	AccessToken findAccessTokenByAuthenticationId(String authenticationId) {
		return accessTokenDao.findByAuthenticationId(authenticationId);
	}

	@Override
	List<AccessToken> findAccessTokens(String clientId, String username) {
		return accessTokenDao.findByClientIdAndUserName(clientId, username);
	}

	@Override
	List<AccessToken> findAccessTokens(String clientId) {
		return accessTokenDao.findByClientId(clientId);
	}
}
