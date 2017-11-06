package com.pearson.projectone.authcommons.provider.token.converters;

import com.pearson.projectone.authcommons.dto.AppUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.util.ObjectUtils;

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
 * This unifies the principal into AppUserDetails if for some reason
 * the local token store didnt have the information
 */
public class ResourceServerAccessTokenConverter implements AccessTokenConverter {

	private AccessTokenConverter delegate;

	public ResourceServerAccessTokenConverter() {
		this.delegate = new DefaultAccessTokenConverter();
	}

	public ResourceServerAccessTokenConverter(AccessTokenConverter delegate) {
		this.delegate = delegate;
	}

	@Override
	public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		return delegate.convertAccessToken(token, authentication);
	}

	@Override
	public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
		return delegate.extractAccessToken(value, map);
	}

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
		OAuth2Authentication authentication = delegate.extractAuthentication(map);
		Object principal = authentication.getPrincipal();
		if (!(principal instanceof AppUserDetails)) {
			@SuppressWarnings("unchecked")
			Map<String, Serializable> extraData = (Map<String, Serializable>) map.get(metaData.name());

			AppUserDetails user = new AppUserDetails(map.get(id.name()).toString(),
					principal.toString(),
					map.get(firstName.name()).toString(),
					map.get(lastName.name()).toString(),
					map.get(honorific.name()).toString(),
					map.get(timezoneId.name()).toString(),
					ObjectUtils.isEmpty(extraData) ? new HashMap<>(0) : extraData,
					authentication.getAuthorities());

			authentication = new OAuth2Authentication(authentication.getOAuth2Request(),
					new UsernamePasswordAuthenticationToken(user, "N/A", authentication.getAuthorities()));
		}
		return authentication;
	}
}
