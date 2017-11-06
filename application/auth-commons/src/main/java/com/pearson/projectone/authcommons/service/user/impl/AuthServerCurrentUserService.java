package com.pearson.projectone.authcommons.service.user.impl;

import com.pearson.projectone.authcommons.dto.AppUserDetails;
import com.pearson.projectone.authcommons.service.user.AuthServerUserDetailService;
import com.pearson.projectone.authcommons.service.user.CurrentUserService;
import com.pearson.projectone.data.dao.security.user.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.util.ObjectUtils;

/**
 * Provides a service to be used within the auth server to return the logged on user
 */
public class AuthServerCurrentUserService extends CurrentUserService {

	@Autowired
	private AppUserDao appUserDao;

	@Autowired
	private AuthServerUserDetailService authServerUserDetailService;

	/**
	 * Get the DTO class instance associated with the current authentication.
	 *
	 * @return the user
	 */
	public AppUserDetails getCurrentUser() {
		Authentication authentication = getAuthentication();
		if (ObjectUtils.isEmpty(authentication) || !authentication.isAuthenticated()) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (!ObjectUtils.isEmpty(principal) && principal instanceof AppUserDetails) {
			return (AppUserDetails) principal;
		} else {
			String username = principal.toString();
			return authServerUserDetailService.loadUserByDomainObject(appUserDao.findByUsername(username));
		}
	}
}
