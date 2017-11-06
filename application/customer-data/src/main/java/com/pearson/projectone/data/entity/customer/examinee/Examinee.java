package com.pearson.projectone.data.entity.customer.examinee;

import com.pearson.projectone.core.support.data.DocumentEntity;
import com.pearson.projectone.data.constants.Gender;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Examinee")
public class Examinee extends DocumentEntity {

	private String examineeId;

	private String hashedIdentifier;

	private String firstName;

	private String lastName;

	private String middleName;

	private Gender gender;

	private Date dob;

	private Date dateOfLastAssessment;

	private String email;

	private String accountId;

	private String comments;

	private boolean archived;

	private boolean practiceMode;

	public String getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
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

	public String getHashedIdentifier() {
		return hashedIdentifier;
	}

	public void setHashedIdentifier(String hashedIdentifier) {
		this.hashedIdentifier = hashedIdentifier;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDateOfLastAssessment() {
		return dateOfLastAssessment;
	}

	public void setDateOfLastAssessment(Date dateOfLastAssessment) {
		this.dateOfLastAssessment = dateOfLastAssessment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public boolean isPracticeMode() {
		return practiceMode;
	}

	public void setPracticeMode(boolean practiceMode) {
		this.practiceMode = practiceMode;
	}
}
