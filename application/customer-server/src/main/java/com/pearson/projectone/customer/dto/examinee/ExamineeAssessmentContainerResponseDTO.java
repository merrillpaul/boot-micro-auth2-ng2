package com.pearson.projectone.customer.dto.examinee;


import com.pearson.projectone.core.support.data.BaseDTO;

public class ExamineeAssessmentContainerResponseDTO extends BaseDTO {

	public ExamineeAssessmentContainerResponseDTO() {
	}

	public ExamineeAssessmentContainerResponseDTO(String id, GroupedIdHolderDTO[] examineeAssessmentIds) {
		super(id);
		this.examineeAssessmentIds = examineeAssessmentIds;
	}

	public ExamineeAssessmentContainerResponseDTO(String id, Long version, GroupedIdHolderDTO[] examineeAssessmentIds) {
		super(id, version);
		this.examineeAssessmentIds = examineeAssessmentIds;
	}

	private GroupedIdHolderDTO[] examineeAssessmentIds;

	public GroupedIdHolderDTO[] getExamineeAssessmentIds() {
		return examineeAssessmentIds;
	}

	public void setExamineeAssessmentIds(GroupedIdHolderDTO[] examineeAssessmentIds) {
		this.examineeAssessmentIds = examineeAssessmentIds;
	}
}
