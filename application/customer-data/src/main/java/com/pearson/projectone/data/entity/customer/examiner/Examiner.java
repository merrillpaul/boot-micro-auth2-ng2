package com.pearson.projectone.data.entity.customer.examiner;

import com.pearson.projectone.core.support.data.DocumentEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Examiner")
public class Examiner extends DocumentEntity {

	public Examiner() {
		//default constructor.
	}

	public Examiner(String title, String firstName, String lastName, String middleName, String suffix,
	                String examinerId) {
		construct(title, firstName, lastName, middleName, suffix, examinerId);
	}

	public void construct(String title, String firstName, String lastName, String middleName, String suffix,
	                      String examinerId) {
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.suffix = suffix;
		this.examinerId = examinerId;
	}

	String title;
	String firstName;
	String lastName;
	String middleName;
	String suffix;
	String examinerId;

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
