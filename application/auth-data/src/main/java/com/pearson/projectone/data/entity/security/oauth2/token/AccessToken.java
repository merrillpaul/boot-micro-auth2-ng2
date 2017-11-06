package com.pearson.projectone.data.entity.security.oauth2.token;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Domain class for representing an Oauth2 access token. Doubles up for JPA and Mongo.
 * Makes the token storage agnostic of underlying persistence mechanism.
 */
@Document(collection = "OauthAccessToken")
@Entity
public class AccessToken {

	@Id
	@javax.persistence.Id
	private String tokenId;

	/* a base64 encoded string for ser-deser OAuth2Authentication object*/
	@Lob
	private String token;

	private String authenticationId;

	private String userName;

	private String clientId;

	/* a base64 encoded string for ser-deser OAuth2Authentication object*/
	@Lob
	private String authentication;

	private String refreshToken;

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

	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
