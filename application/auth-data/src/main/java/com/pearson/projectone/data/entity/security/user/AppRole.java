package com.pearson.projectone.data.entity.security.user;


import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Resolves to app_role table
 */
@Entity
public class AppRole extends RelationalEntity {
	@Column(nullable = false, unique = true)
	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Transient
	public String toString() {
		return authority;
	}
}
