package com.pearson.projectone.global.dto.content;


public class ContentTypesDTO {
	private String name;

	private boolean usesManifest;

	private String displayName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUsesManifest() {
		return usesManifest;
	}

	public void setUsesManifest(boolean usesManifest) {
		this.usesManifest = usesManifest;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
