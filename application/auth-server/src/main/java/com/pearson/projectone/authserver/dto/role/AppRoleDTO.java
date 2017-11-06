package com.pearson.projectone.authserver.dto.role;

import com.pearson.projectone.core.support.data.BaseDTO;

public class AppRoleDTO extends BaseDTO {
	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
