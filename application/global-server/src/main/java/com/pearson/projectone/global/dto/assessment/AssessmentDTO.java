package com.pearson.projectone.global.dto.assessment;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.DayDTO;
import com.pearson.projectone.global.dto.assesstest.SimpleAssessTestDTO;


public class AssessmentDTO extends BaseDTO {
	private String acronym;

	private String name;

	private String typeId;

	private String licensedBy;

	private String requiredQualLevel;

	private DayDTO startDate;

	private DayDTO expiryDate;

	private String comments;

	private String status;

	private String productCode;

	private String configSourceId;

	private String definitionJson;

	private String qgiVersion;


	private SimpleAssessTestDTO assessTest;


	private Boolean dataCollectionEnabled = Boolean.TRUE;


	private boolean apiEnabled;


	private String assessmentDefinition;


	private String guid;


	/*
	Getters and Setters
	 */

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getLicensedBy() {
		return licensedBy;
	}

	public void setLicensedBy(String licensedBy) {
		this.licensedBy = licensedBy;
	}

	public String getRequiredQualLevel() {
		return requiredQualLevel;
	}

	public void setRequiredQualLevel(String requiredQualLevel) {
		this.requiredQualLevel = requiredQualLevel;
	}

	public DayDTO getStartDate() {
		return startDate;
	}

	public void setStartDate(DayDTO startDate) {
		this.startDate = startDate;
	}

	public DayDTO getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(DayDTO expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getConfigSourceId() {
		return configSourceId;
	}

	public void setConfigSourceId(String configSourceId) {
		this.configSourceId = configSourceId;
	}

	public String getDefinitionJson() {
		return definitionJson;
	}

	public void setDefinitionJson(String definitionJson) {
		this.definitionJson = definitionJson;
	}

	public String getQgiVersion() {
		return qgiVersion;
	}

	public void setQgiVersion(String qgiVersion) {
		this.qgiVersion = qgiVersion;
	}

	public Boolean getDataCollectionEnabled() {
		return dataCollectionEnabled;
	}

	public void setDataCollectionEnabled(Boolean dataCollectionEnabled) {
		this.dataCollectionEnabled = dataCollectionEnabled;
	}

	public boolean isApiEnabled() {
		return apiEnabled;
	}

	public void setApiEnabled(boolean apiEnabled) {
		this.apiEnabled = apiEnabled;
	}

	public SimpleAssessTestDTO getAssessTest() {
		return assessTest;
	}

	public void setAssessTest(SimpleAssessTestDTO assessTest) {
		this.assessTest = assessTest;
	}

	public String getAssessmentDefinition() {
		return assessmentDefinition;
	}

	public void setAssessmentDefinition(String assessmentDefinition) {
		this.assessmentDefinition = assessmentDefinition;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}
