package com.pearson.projectone.global.dto.businessunit;


import com.pearson.projectone.core.support.data.BaseDTO;

public class BusinessAssessmentListDTO extends BaseDTO {
	private String assessmentName;


	private String accronym;

	public String getAssessmentName() {
		return assessmentName;
	}

	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}

	public String getAccronym() {
		return accronym;
	}

	public void setAccronym(String accronym) {
		this.accronym = accronym;
	}
}
