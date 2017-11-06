package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class AssessSubtest extends RelationalEntity {

	@Column
	private String abbreviation;

	@Column
	private Integer averageDuration;

	@Column(length = 4000)
	private String description;

	@Column
	private String displayName;

	@Column
	private boolean observationsEnabled;

	@Column
	private boolean disabled;

	@Column
	private Integer maxAge;

	@Column
	private Integer maxAgeMonths;

	@Column
	private Integer minAge;

	@Column
	private Integer minAgeMonths;

	@Column
	private Integer minDelayMinutes;

	@Column
	private Integer maxDelayMinutes;

	@Column
	private String guid;

	@Column
	private boolean usesStim;

	@ManyToOne
	private AssessSubtestType subtestType;

	@Column
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private AssessTest test;

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

	public AssessSubtestType getSubtestType() {
		return subtestType;
	}

	public void setSubtestType(AssessSubtestType subtestType) {
		this.subtestType = subtestType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AssessTest getTest() {
		return test;
	}

	public void setTest(AssessTest test) {
		this.test = test;
	}
}
