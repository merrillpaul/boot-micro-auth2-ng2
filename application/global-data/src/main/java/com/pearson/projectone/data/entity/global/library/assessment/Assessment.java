package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Date;

@Entity
public class Assessment extends RelationalEntity {


	public Assessment() {
		//default constructor
	}

	public Assessment(String acronym, String name, String typeId, String licensedBy, String requiredQualLevel,
	                  Date startDate, Date expiryDate, String comments, String statusId, String productCode,
	                  String configSourceId, String definitionJson, String qgiVersion, Boolean dataCollectionEnabled,
	                  boolean apiEnabled, String assessmentDefinition, String guid) {
		this.acronym = acronym;
		this.name = name;
		this.typeId = typeId;
		this.licensedBy = licensedBy;
		this.requiredQualLevel = requiredQualLevel;
		this.startDate = startDate;
		this.expiryDate = expiryDate;
		this.comments = comments;
		this.statusId = statusId;
		this.productCode = productCode;
		this.configSourceId = configSourceId;
		this.definitionJson = definitionJson;
		this.qgiVersion = qgiVersion;
		this.dataCollectionEnabled = dataCollectionEnabled;
		this.apiEnabled = apiEnabled;
		this.assessmentDefinition = assessmentDefinition;
		this.guid = guid;
	}

	@Column
	private String acronym;

	@Column
	private String name;


	@Column
	private String typeId;

	@Column
	private String licensedBy;

	@Column
	private String requiredQualLevel;

	@Column
	private Date startDate;

	@Column
	private Date expiryDate;

	@Lob
	@Column
	private String comments;

	@Column
	private String statusId;

	@Column
	private String productCode;

	@Column
	private String configSourceId;

	@Lob
	@Column
	private String definitionJson;

	@Column
	private String qgiVersion;

	@Column
	private Boolean dataCollectionEnabled = Boolean.TRUE;

	@Column
	private boolean apiEnabled;

	@Column
	private String assessmentDefinition;

	@Column
	private String guid;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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


	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
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
