package com.pearson.projectone.global.dto.account;


public class UpdateAccountCommandDTO extends CreateAccountCommandDTO {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
