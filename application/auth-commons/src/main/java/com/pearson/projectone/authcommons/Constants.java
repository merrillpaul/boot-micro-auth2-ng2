package com.pearson.projectone.authcommons;

public enum Constants {
	REALM("oauth2/client");
	private String code;

	Constants(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
