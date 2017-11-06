package com.pearson.projectone.customer.dto.examinee;

import com.pearson.projectone.core.support.data.BaseDTO;

public class ExamineeAssessmentContainerDetailedDTO extends BaseDTO {

	public ExamineeAssessmentContainerDetailedDTO() {
		//default constructor.
	}

	public ExamineeAssessmentContainerDetailedDTO(String id, Long version, ExamineeDTO examineeDTO,
	                                              ExamineeAssessmentHolderDTO[] examineeAssessmentHolders) {
		super(id, version);
		this.examineeDTO = examineeDTO;
		this.examineeAssessmentHolders = examineeAssessmentHolders;
	}

	public ExamineeAssessmentContainerDetailedDTO(String id, ExamineeDTO examineeDTO,
	                                              ExamineeAssessmentHolderDTO[] examineeAssessmentHolders) {
		super(id);
		this.examineeDTO = examineeDTO;
		this.examineeAssessmentHolders = examineeAssessmentHolders;
	}

	private ExamineeDTO examineeDTO;
	private ExamineeAssessmentHolderDTO[] examineeAssessmentHolders;

	public ExamineeDTO getExamineeDTO() {
		return examineeDTO;
	}

	public void setExamineeDTO(ExamineeDTO examineeDTO) {
		this.examineeDTO = examineeDTO;
	}

	public ExamineeAssessmentHolderDTO[] getExamineeAssessmentHolders() {
		return examineeAssessmentHolders;
	}

	public void setExamineeAssessmentHolders(ExamineeAssessmentHolderDTO[] examineeAssessmentHolders) {
		this.examineeAssessmentHolders = examineeAssessmentHolders;
	}
}
