package com.pearson.projectone.global.dto.assesstest;


import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.global.dto.subtesttype.SubtestTypeDTO;

public class BaseAssessSubtestDTO extends BaseDTO {

	private String abbreviation;

	private Integer averageDuration;

	private String description;

	private String displayName;

	private boolean observationsEnabled;

	private boolean disabled;

	private Integer maxAge;

	private Integer maxAgeMonths;

	private Integer minAge;

	private Integer minAgeMonths;

	private Integer minDelayMinutes;

	private Integer maxDelayMinutes;

	private String guid;

	private boolean usesStim;

	private SubtestTypeDTO subtestType;

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Integer getAverageDuration() {
		return averageDuration;
	}

	public void setAverageDuration(Integer averageDuration) {
		this.averageDuration = averageDuration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isObservationsEnabled() {
		return observationsEnabled;
	}

	public void setObservationsEnabled(boolean observationsEnabled) {
		this.observationsEnabled = observationsEnabled;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public Integer getMaxAgeMonths() {
		return maxAgeMonths;
	}

	public void setMaxAgeMonths(Integer maxAgeMonths) {
		this.maxAgeMonths = maxAgeMonths;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMinAgeMonths() {
		return minAgeMonths;
	}

	public void setMinAgeMonths(Integer minAgeMonths) {
		this.minAgeMonths = minAgeMonths;
	}

	public Integer getMinDelayMinutes() {
		return minDelayMinutes;
	}

	public void setMinDelayMinutes(Integer minDelayMinutes) {
		this.minDelayMinutes = minDelayMinutes;
	}

	public Integer getMaxDelayMinutes() {
		return maxDelayMinutes;
	}

	public void setMaxDelayMinutes(Integer maxDelayMinutes) {
		this.maxDelayMinutes = maxDelayMinutes;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public boolean isUsesStim() {
		return usesStim;
	}

	public void setUsesStim(boolean usesStim) {
		this.usesStim = usesStim;
	}

	public SubtestTypeDTO getSubtestType() {
		return subtestType;
	}

	public void setSubtestType(SubtestTypeDTO subtestType) {
		this.subtestType = subtestType;
	}
}
