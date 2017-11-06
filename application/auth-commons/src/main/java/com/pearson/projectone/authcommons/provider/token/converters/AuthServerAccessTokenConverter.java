package com.pearson.projectone.authcommons.provider.token.converters;

import com.pearson.projectone.authcommons.dto.AppUserDetails;
import com.pearson.projectone.data.dao.security.user.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.pearson.projectone.authcommons.provider.AuthKeys.firstName;
import static com.pearson.projectone.authcommons.provider.AuthKeys.honorific;
import static com.pearson.projectone.authcommons.provider.AuthKeys.id;
import static com.pearson.projectone.authcommons.provider.AuthKeys.lastName;
import static com.pearson.projectone.authcommons.provider.AuthKeys.metaData;
import static com.pearson.projectone.authcommons.provider.AuthKeys.timezoneId;

/**
 * Adds extra attributes that might be needed in the API
 * ( customer centric application) to get extra information about
 * the principal/user
 */
public class AuthServerAccessTokenConverter implements AccessTokenConverter {

	@Autowired
	AppUserDao userDao;

	private AccessTokenConverter delegate;

	public AuthServerAccessTokenConverter() {
		this.delegate = new DefaultAccessTokenConverter();
	}

	public AuthServerAccessTokenConverter(AccessTokenConverter delegate) {
		this.delegate = delegate;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		Map resp = delegate.convertAccessToken(token, authentication);
		resp.putAll(addExtraAttributes(token, authentication));
		return resp;
	}

	@Override
	public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
		return delegate.extractAccessToken(value, map);
	}

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
		return delegate.extractAuthentication(map);
	}

	public Map<String, Serializable> addExtraAttributes(OAuth2AccessToken token, OAuth2Authentication authentication) {
		Object principal = authentication.getPrincipal();
		Map<String, Serializable> extra = new HashMap<>(0);
		if (principal instanceof AppUserDetails) {
			AppUserDetails userDetails = (AppUserDetails) principal;
			extra.put(id.name(), userDetails.getId());
			extra.put(firstName.name(), userDetails.getFirstName());
			extra.put(lastName.name(), userDetails.getLastName());
			extra.put(timezoneId.name(), userDetails.getTimezoneId());
			extra.put(honorific.name(), userDetails.getHonorific());
			// TODO add extra information such as account, business unit information
			// AppUser user = userDao.findByUsername(userDetails.getUsername());
			// that might be needed for resource server api from the principal
			extra.put(metaData.name(), new HashMap<String, Serializable>());
		}
		return extra;
	}
}
