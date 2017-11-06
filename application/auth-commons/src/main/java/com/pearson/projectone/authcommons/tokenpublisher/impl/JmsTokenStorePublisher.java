package com.pearson.projectone.authcommons.tokenpublisher.impl;

import com.pearson.projectone.authcommons.dto.AppUserDetails;
import com.pearson.projectone.authcommons.dto.TokenMessagePayload;
import com.pearson.projectone.authcommons.dto.TokenMessageType;
import com.pearson.projectone.authcommons.tokenpublisher.TokenPublisher;
import com.pearson.projectone.data.dao.security.user.AppUserDao;
import com.pearson.projectone.data.entity.security.oauth2.token.AccessToken;
import com.pearson.projectone.data.entity.security.oauth2.token.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

import java.util.HashMap;
import java.util.Map;

public class JmsTokenStorePublisher implements TokenPublisher {
	@Autowired(required = false)
	JmsTemplate jmsTemplate;

	@Autowired
	AppUserDao appUserDao;

	// TODO remove this when we get the customer token store info from the user account
	@Value("${config.customer_token_store_key}")
	String customerTokenStore;

	@Override
	public void saveAccessToken(AccessToken accessToken, Object principal) {
		jmsTemplate.convertAndSend(TokenMessagePayload.DESTINATION, new TokenMessagePayload(
				resolveUserLocationTokenStoreCreds(principal),
				TokenMessageType.SAVE_ACCESS_TOKEN, accessToken));
	}

	@Override
	public void saveRefreshToken(RefreshToken refreshToken, Object principal) {
		jmsTemplate.convertAndSend(TokenMessagePayload.DESTINATION, new TokenMessagePayload(
				resolveUserLocationTokenStoreCreds(principal),
				TokenMessageType.SAVE_REFRESH_TOKEN, refreshToken));
	}

	@Override
	public void deleteAccessToken(AccessToken accessToken, Object principal) {
		jmsTemplate.convertAndSend(TokenMessagePayload.DESTINATION, new TokenMessagePayload(
				resolveUserLocationTokenStoreCreds(principal),
				TokenMessageType.DEL_ACCESS_TOKEN, accessToken.getTokenId()));
	}

	@Override
	public void deleteRefreshToken(RefreshToken refreshToken, Object principal) {
		jmsTemplate.convertAndSend(TokenMessagePayload.DESTINATION, new TokenMessagePayload(
				resolveUserLocationTokenStoreCreds(null),
				TokenMessageType.DEL_REFRESH_TOKEN, refreshToken.getTokenId()));
	}

	@Override
	public void deleteAccessTokenByRefreshToken(RefreshToken refreshToken, Object principal) {
		jmsTemplate.convertAndSend(TokenMessagePayload.DESTINATION, new TokenMessagePayload(
				resolveUserLocationTokenStoreCreds(principal),
				TokenMessageType.DEL_ACCESS_BY_REFRESH_TOKEN, refreshToken.getTokenId()));
	}

	private Map<String, String> resolveUserLocationTokenStoreCreds(Object principal) {
		Map<String, String> result = new HashMap<>(0);
		if (principal != null && principal instanceof AppUserDetails) {
			// TODO get the token store url from account of the user. For now hard coding to US
			// AppUserDetails userDetails = (AppUserDetails) principal;
			// AppUser appUser = appUserDao.findOne(userDetails.getId());
			result.put("tokenStoreKey", "US");
		}
		return result;
	}
}
