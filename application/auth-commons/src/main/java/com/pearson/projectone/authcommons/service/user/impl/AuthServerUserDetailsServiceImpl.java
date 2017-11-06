package com.pearson.projectone.authcommons.service.user.impl;

import com.pearson.projectone.authcommons.dto.AppUserDetails;
import com.pearson.projectone.authcommons.service.user.AuthServerUserDetailService;
import com.pearson.projectone.data.dao.security.user.AppUserDao;
import com.pearson.projectone.data.entity.security.user.AppRole;
import com.pearson.projectone.data.entity.security.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Auth server user details which uses JPA to
 * retrieve authenticated user information.
 */
@Transactional
public class AuthServerUserDetailsServiceImpl implements AuthServerUserDetailService {

	@Autowired
	AppUserDao appUserDao;

	@Override
	public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = appUserDao.findByUsername(username);
		if (ObjectUtils.isEmpty(user)) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}

		List<AppRole> roles = user.getRoles();
		List<GrantedAuthority> springRoles = new ArrayList<>(0);
		for (AppRole role : roles) {
			springRoles.add(new SimpleGrantedAuthority(role.getAuthority()));
		}

		// TODO get password expired info from DB
		boolean isPasswordExpired = false;
		return new AppUserDetails(user, springRoles, isPasswordExpired);
	}


	@Override
	@SuppressWarnings("unchecked")
	public AppUserDetails loadUserByDomainObject(AppUser user) throws UsernameNotFoundException {
		return loadUserByUsername(user.getUsername());
	}

}
