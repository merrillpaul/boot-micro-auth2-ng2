package com.pearson.projectone.authserver.dto.user;

import com.pearson.projectone.core.support.data.BaseDTO;


public class ResetPasswordCommandDTO extends BaseDTO {
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
