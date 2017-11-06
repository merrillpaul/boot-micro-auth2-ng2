package com.pearson.projectone.global.dto.account;


import java.util.ArrayList;
import java.util.List;

public class AccountUserAssignCommandDTO {
	private List<String> userIds = new ArrayList<>(0);

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
}
