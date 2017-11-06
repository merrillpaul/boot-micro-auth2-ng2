package com.pearson.projectone.global.dto.forms;


import com.pearson.projectone.core.support.data.BaseDTO;

public class FormDTO extends BaseDTO {


	private String acronym;
	private String description;
	private String assessmentName;
	private String formType;
	private String[] formDeliveryTypes;

	public FormDTO() {
		//Default Constructor
	}

	public FormDTO(final String id, final Long version, final String acronym, final String description,
	               final String assessmentName, final String formType, final String[] formDeliveryTypes) {
		super(id, version);
		this.acronym = acronym;
		this.description = description;
		this.assessmentName = assessmentName;
		this.formType = formType;
		this.formDeliveryTypes = formDeliveryTypes;

	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssessmentName() {
		return assessmentName;
	}

	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String[] getFormDeliveryTypes() {
		return formDeliveryTypes;
	}

	public void setFormDeliveryTypes(String[] formDeliveryTypes) {
		this.formDeliveryTypes = formDeliveryTypes;
	}
}
