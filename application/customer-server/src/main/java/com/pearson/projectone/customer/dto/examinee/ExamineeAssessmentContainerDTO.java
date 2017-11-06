package com.pearson.projectone.customer.dto.examinee;

import com.pearson.projectone.core.support.data.BaseDTO;

/**
 * This is Container class for {@link ExamineeAssessmentDTO}.
 */
public class ExamineeAssessmentContainerDTO extends BaseDTO {

	public ExamineeAssessmentContainerDTO() {
		//default constructor
	}

	public ExamineeAssessmentContainerDTO(String examineeId, ExamineeAssessmentHolderDTO[] examineeAssessmentHolderDTOs) {
		this.examineeId = examineeId;
		this.examineeAssessmentHolders = examineeAssessmentHolderDTOs;
	}

	public ExamineeAssessmentContainerDTO(String id, String examineeId, ExamineeAssessmentHolderDTO[] examineeAssessmentHolderDTOs) {
		super(id);
		this.examineeId = examineeId;
		this.examineeAssessmentHolders = examineeAssessmentHolderDTOs;
	}

	private String examineeId;
	private ExamineeAssessmentHolderDTO[] examineeAssessmentHolders;

	public String getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}

	public ExamineeAssessmentHolderDTO[] getExamineeAssessmentHolders() {
		return examineeAssessmentHolders;
	}

	public void setExamineeAssessmentHolders(ExamineeAssessmentHolderDTO[] examineeAssessmentHolders) {
		this.examineeAssessmentHolders = examineeAssessmentHolders;
	}
}
