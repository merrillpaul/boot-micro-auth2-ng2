package com.pearson.projectone.global.dto.assessment;

import com.pearson.projectone.core.support.data.BaseDTO;


public class MapAssessTestCommandDTO extends BaseDTO {
	private String assessTestId;

	public String getAssessTestId() {
		return assessTestId;
	}

	public void setAssessTestId(String assessTestId) {
		this.assessTestId = assessTestId;
	}
}
