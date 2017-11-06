package com.pearson.projectone.authserver.dto.client;

import com.pearson.projectone.core.support.data.AbstractDTO;


public class OauthClientDeleteCommandDTO extends AbstractDTO {
	private String clientId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
