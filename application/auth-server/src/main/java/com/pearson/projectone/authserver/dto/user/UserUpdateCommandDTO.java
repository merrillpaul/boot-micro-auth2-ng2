package com.pearson.projectone.authserver.dto.user;


public class UserUpdateCommandDTO extends UserCreateCommandDTO {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

