package com.pearson.projectone.data.entity.global.library.account;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class BusinessUnit extends RelationalEntity {

	@Column
	private String guid;

	@Column
	private String name;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String middleName;

	@Column
	private String mainContactPhone;

	@Column
	private String mainContactEmail;

	@Column
	private String languageId;

	@Column
	private String timezoneId;

	@Column
	private String dateFormatId;

	@Column
	private String numberFormatId;

	@Column
	private Integer passwordExpiryDays;

	@Column
	private String status;

	@Column
	private Boolean allowUserCreation = Boolean.TRUE;

	@Column
	private String countryId;

	@OneToMany(mappedBy = "businessUnit")
	private Set<ResourceMetaData> metaDataSet;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMainContactPhone() {
		return mainContactPhone;
	}

	public void setMainContactPhone(String mainContactPhone) {
		this.mainContactPhone = mainContactPhone;
	}

	public String getMainContactEmail() {
		return mainContactEmail;
	}

	public void setMainContactEmail(String mainContactEmail) {
		this.mainContactEmail = mainContactEmail;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getTimezoneId() {
		return timezoneId;
	}

	public void setTimezoneId(String timezoneId) {
		this.timezoneId = timezoneId;
	}

	public String getDateFormatId() {
		return dateFormatId;
	}

	public void setDateFormatId(String dateFormatId) {
		this.dateFormatId = dateFormatId;
	}

	public String getNumberFormatId() {
		return numberFormatId;
	}

	public void setNumberFormatId(String numberFormatId) {
		this.numberFormatId = numberFormatId;
	}

	public Integer getPasswordExpiryDays() {
		return passwordExpiryDays;
	}

	public void setPasswordExpiryDays(Integer passwordExpiryDays) {
		this.passwordExpiryDays = passwordExpiryDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getAllowUserCreation() {
		return allowUserCreation;
	}

	public void setAllowUserCreation(Boolean allowUserCreation) {
		this.allowUserCreation = allowUserCreation;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public Set<ResourceMetaData> getMetaDataSet() {
		return metaDataSet;
	}

	public void setMetaDataSet(Set<ResourceMetaData> metaDataSet) {
		this.metaDataSet = metaDataSet;
	}
}
