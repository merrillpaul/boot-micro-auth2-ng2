package com.pearson.projectone.data.entity.security.oauth2.token;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Domain class to represent an Oauth2 refresh token. Doubles up as entity class for JPA and dynamo db
 */
@Document(collection = "OauthRefreshToken")
@Entity
public class RefreshToken {

	@Id
	@javax.persistence.Id
	private String tokenId;

	private String token;

	/* a base64 encoded string for ser-deser OAuth2Authentication object*/
	@Lob
	private String authentication;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
}
