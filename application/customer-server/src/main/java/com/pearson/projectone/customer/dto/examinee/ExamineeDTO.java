package com.pearson.projectone.customer.dto.examinee;

import com.pearson.projectone.core.support.data.BaseDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExamineeDTO extends BaseDTO {

	private String firstName;
	private String lastName;
	private String middleName;
	private String gender;
	private Date dob;
	private String examineeId;

	public ExamineeDTO() {

	}

	public ExamineeDTO(String id, String firstName, String lastName, String middleName, String gender, Date dob, String examineeId) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.gender = gender;
		this.dob = dob;
		this.examineeId = examineeId;
	}

	public ExamineeDTO(String id, String firstName, String lastName, String middleName, String gender, String dob, String examineeId) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.gender = gender;
		this.dob = this.dateConverter(dob);
		this.examineeId = examineeId;


	}

	private Date dateConverter(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}
}
