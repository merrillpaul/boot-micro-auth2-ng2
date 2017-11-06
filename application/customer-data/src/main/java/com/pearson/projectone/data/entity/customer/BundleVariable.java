package com.pearson.projectone.data.entity.customer;

import com.pearson.projectone.data.entity.customer.examinee.assessment.ExamineeAssessment;

/**
 * This class is used inside {@link ExamineeAssessment}  to hold bundle variables.
 */
public class BundleVariable {

	public BundleVariable() {
		//default constructor
	}

	public BundleVariable(String name, String value, String dataType, boolean required) {
		this.name = name;
		this.value = value;
		this.dataType = dataType;
		this.required = required;

	}

	private String name;
	private String value;
	private String dataType;
	private boolean required;
	private String examineeAssessmentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
}
