package com.pearson.projectone.customer.dto.sync;

public class SyncSubtestDTO {
	private String abbr;
	private String normType;
	private String subtestGUID;
	private String subtestName;
	private String testGUID;
	private String testName;

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getNormType() {
		return normType;
	}

	public void setNormType(String normType) {
		this.normType = normType;
	}

	public String getSubtestGUID() {
		return subtestGUID;
	}

	public void setSubtestGUID(String subtestGUID) {
		this.subtestGUID = subtestGUID;
	}

	public String getSubtestName() {
		return subtestName;
	}

	public void setSubtestName(String subtestName) {
		this.subtestName = subtestName;
	}

	public String getTestGUID() {
		return testGUID;
	}

	public void setTestGUID(String testGUID) {
		this.testGUID = testGUID;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}
}
