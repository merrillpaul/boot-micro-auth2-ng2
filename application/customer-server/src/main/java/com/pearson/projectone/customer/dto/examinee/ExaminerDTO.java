package com.pearson.projectone.customer.dto.examinee;

import com.pearson.projectone.core.support.data.BaseDTO;

public class ExaminerDTO extends BaseDTO {

	public ExaminerDTO() {
	}

	public ExaminerDTO(String id, Long version) {
		super(id, version);
	}

	public ExaminerDTO(String id, Long version, String title, String firstName, String lastName, String middleName,
	                   String suffix, String examinerId) {
		super(id, version);
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.suffix = suffix;
		this.examinerId = examinerId;
	}

	public ExaminerDTO(String title, String firstName, String lastName, String middleName, String suffix,
	                   String examinerId) {
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.suffix = suffix;
		this.examinerId = examinerId;
	}

	private String title;
	private String firstName;
	private String lastName;
	private String middleName;
	private String suffix;
	private String examinerId;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getExaminerId() {
		return examinerId;
	}

	public void setExaminerId(String examinerId) {
		this.examinerId = examinerId;
	}
}
