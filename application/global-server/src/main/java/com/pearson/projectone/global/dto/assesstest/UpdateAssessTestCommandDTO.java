package com.pearson.projectone.global.dto.assesstest;

public class UpdateAssessTestCommandDTO extends CreateAssessTestCommandDTO {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
