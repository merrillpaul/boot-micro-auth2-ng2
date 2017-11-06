package com.pearson.projectone.data.entity.security.user;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PasswordHistory extends RelationalEntity {

	private String password;

	@ManyToOne
	private AppUser user;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

}
