package com.pearson.projectone.authcommons.service.user;

import com.pearson.projectone.data.entity.security.user.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Interface to be used by auth server to get current user
 * credentials and principal
 */
public interface AuthServerUserDetailService extends UserDetailsService {
	<T extends UserDetails> T loadUserByDomainObject(AppUser user) throws UsernameNotFoundException;
}
