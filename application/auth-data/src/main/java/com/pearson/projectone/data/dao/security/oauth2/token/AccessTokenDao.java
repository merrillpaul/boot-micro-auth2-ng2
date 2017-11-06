package com.pearson.projectone.data.dao.security.oauth2.token;


import com.pearson.projectone.data.entity.security.oauth2.token.AccessToken;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AccessTokenDao extends CrudRepository<AccessToken, String> {
	void deleteAccessTokenByRefreshToken(String refreshToken);

	AccessToken findByAuthenticationId(String authenticationId);

	AccessToken findByRefreshToken(String refreshToken);

	List<AccessToken> findByClientId(String clientId);

	List<AccessToken> findByClientIdAndUserName(String clientId, String userName);
}
