package com.pearson.projectone.authcommons.service.user.impl;

import com.pearson.projectone.authcommons.dto.AppUserDetails;
import com.pearson.projectone.authcommons.service.user.CurrentUserService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.util.ObjectUtils;

/**
 * Service to be used by all resource servers ( clients to the auth server )
 * to get the current ( logged ) user information
 */
public class ResourceCurrentUserService extends CurrentUserService {

	public AppUserDetails getCurrentUser() {
		AppUserDetails appUserDetails;
		Authentication authentication = getAuthentication();

		Object principal = null;
		if (authentication != null) {
			principal = authentication.getPrincipal();
		}
		if (ObjectUtils.isEmpty(principal) || !(principal instanceof AppUserDetails)) {
			throw new AuthenticationServiceException("Invalid authentication principal");
		}
		return (AppUserDetails) principal;
	}
}
