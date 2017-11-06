package com.pearson.projectone.authcommons.dto;

import java.util.HashMap;
import java.util.Map;

public class TokenMessagePayload {

	TokenMessageType type;

	Object payload;

	Map<String, String> tokenStoreCreds = new HashMap<>(0);

	public static final String DESTINATION = "tokenstore";

	public TokenMessagePayload(Map<String, String> tokenStoreCreds, TokenMessageType type, Object payload) {
		this.type = type;
		this.payload = payload;
		this.tokenStoreCreds = tokenStoreCreds;
	}

	public TokenMessageType getType() {
		return type;
	}

	public Object getPayload() {
		return payload;
	}

	public Map<String, String> getTokenStoreCreds() {
		return tokenStoreCreds;
	}
}
