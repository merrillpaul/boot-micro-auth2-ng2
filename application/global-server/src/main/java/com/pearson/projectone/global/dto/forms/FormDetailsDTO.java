package com.pearson.projectone.global.dto.forms;


public class FormDetailsDTO extends FormDTO {

	public FormDetailsDTO() {
		//default constructor
	}

	public FormDetailsDTO(boolean displayExaminer) {
		this.displayExaminer = displayExaminer;
	}

	public FormDetailsDTO(final String id, final Long version, final String acronym, final String description,
	        final String assessmentName, final String formType, final String[] formDeliveryTypes,
	                      final boolean displayExaminer) {

		super(id, version, acronym, description, assessmentName, formType, formDeliveryTypes);
		this.displayExaminer = displayExaminer;
	}

	public FormDetailsDTO(final String id, final Long version, final String acronym, final String description,
	                      final String assessmentName, final String formType, final String[] formdeliveryTypes) {

		super(id, version, acronym, description, assessmentName, formType, formdeliveryTypes);
	}

	private boolean displayExaminer;

	public boolean isDisplayExaminer() {
		return displayExaminer;
	}

	public void setDisplayExaminer(boolean displayExaminer) {
		this.displayExaminer = displayExaminer;
	}
}
