package com.pearson.projectone.customer.dto.examinee;

import java.io.Serializable;

public class ExamineeAssessmentHolderDTO implements Serializable {

	public ExamineeAssessmentHolderDTO() {
		//default constructor
	}

	public ExamineeAssessmentHolderDTO(String name, String groupId, ExamineeAssessmentDTO[] examineeAssessmentDTOs) {
		this.name = name;
		this.examineeAssessments = examineeAssessmentDTOs;
		this.groupId = groupId;
	}

	private String name;
	private String groupId;
	private ExamineeAssessmentDTO[] examineeAssessments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExamineeAssessmentDTO[] getExamineeAssessments() {
		return examineeAssessments;
	}

	public void setExamineeAssessments(ExamineeAssessmentDTO[] examineeAssessments) {
		this.examineeAssessments = examineeAssessments;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
