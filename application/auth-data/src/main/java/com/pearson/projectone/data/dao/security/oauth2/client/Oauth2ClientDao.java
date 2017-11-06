package com.pearson.projectone.data.dao.security.oauth2.client;

import com.pearson.projectone.data.entity.security.oauth2.client.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Oauth2ClientDao extends JpaRepository<OauthClientDetails, String> {
	OauthClientDetails findByClientId(String clientId);
}
