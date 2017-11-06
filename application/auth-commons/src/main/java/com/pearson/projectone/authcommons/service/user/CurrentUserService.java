package com.pearson.projectone.authcommons.service.user;


import com.pearson.projectone.authcommons.dto.AppUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class CurrentUserService {
	public abstract AppUserDetails getCurrentUser();

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
