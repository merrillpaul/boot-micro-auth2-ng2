package com.pearson.projectone.global.dto.content;


public class CreateContentTypeCommandDTO {
	private String testId;

	private String typeName;

	private boolean usesManifest;

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public boolean isUsesManifest() {
		return usesManifest;
	}

	public void setUsesManifest(boolean usesManifest) {
		this.usesManifest = usesManifest;
	}
}
