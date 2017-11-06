package com.pearson.projectone.customer.dto.sync;


import java.util.List;

public class CheckAuthDTO {
	private String userName;
	private String userId;
	private List<String> eligibleSubtestGUIDs;
	private String timeZoneOffset;
	private String timeZoneLabel;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getEligibleSubtestGUIDs() {
		return eligibleSubtestGUIDs;
	}

	public void setEligibleSubtestGUIDs(List<String> eligibleSubtestGUIDs) {
		this.eligibleSubtestGUIDs = eligibleSubtestGUIDs;
	}

	public String getTimeZoneOffset() {
		return timeZoneOffset;
	}

	public void setTimeZoneOffset(String timeZoneOffset) {
		this.timeZoneOffset = timeZoneOffset;
	}

	public String getTimeZoneLabel() {
		return timeZoneLabel;
	}

	public void setTimeZoneLabel(String timeZoneLabel) {
		this.timeZoneLabel = timeZoneLabel;
	}
}
