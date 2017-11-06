
package com.pearson.projectone.global.globallibrary;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pearson.projectone.core.utils.ObjectResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The Class AssessmentView.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AssessmentView.class
		, resolver = ObjectResolver.class)
public class AssessmentView {

	/**
	 * The id.
	 */
	@JsonProperty("id")
	private String id;

	/**
	 * The acronym.
	 */
	@JsonProperty("acronym")
	private String acronym;

	/**
	 * The comments.
	 */
	@JsonProperty("comments")
	private String comments;

	/**
	 * The expiry date.
	 */
	@JsonProperty("expiryDate")
	private String expiryDate;

	/**
	 * The licensed by.
	 */
	@JsonProperty("licensedBy")
	private String licensedBy;

	/**
	 * The name.
	 */
	@JsonProperty("name")
	private String name;

	/**
	 * The rec qualification level.
	 */
	@JsonProperty("recQualificationLevel")
	private String recQualificationLevel;

	/**
	 * The start date.
	 */
	@JsonProperty("startDate")
	private String startDate;

	/**
	 * The status.
	 */
	@JsonProperty("status")
	private Integer status;

	/**
	 * The type id.
	 */
	@JsonProperty("typeId")
	private Long typeId;

	/**
	 * The guid.
	 */
	@JsonProperty("guid")
	private String guid;

	/**
	 * The assessment config source id.
	 */
	@JsonProperty("assessmentConfigSourceId")
	private Long assessmentConfigSourceId;

	/**
	 * The assessment definition.
	 */
	@JsonProperty("assessmentDefinition")
	private String assessmentDefinition;

	/**
	 * The data collection enabled.
	 */
	@JsonProperty("dataCollectionEnabled")
	private String dataCollectionEnabled;

	/**
	 * The api enabled.
	 */
	@JsonProperty("apiEnabled")
	private String apiEnabled;

	/**
	 * The product code.
	 */
	@JsonProperty("productCode")
	private String productCode;

	/**
	 * The forms.
	 */
	@JsonProperty("forms")
	private List<FormView> forms = new ArrayList<FormView>();

	/**
	 * The document.
	 */
	@JsonProperty("document")
	private List<DocumentView> document = new ArrayList<DocumentView>();

	/**
	 * The created by.
	 */
	@JsonProperty("createdBy")
	private String createdBy;

	/**
	 * The last created.
	 */
	@JsonProperty("lastCreated")
	private String lastCreated;

	/**
	 * The last updated.
	 */
	@JsonProperty("lastUpdated")
	private String lastUpdated;

	/**
	 * The updated by.
	 */
	@JsonProperty("updatedBy")
	private String updatedBy;

	/**
	 * The status type.
	 */
	@JsonProperty("statusType")
	private String statusType;

	/**
	 * The status code.
	 */
	@JsonProperty("statusCode")
	private String statusCode;

	/**
	 * The type domain type.
	 */
	@JsonProperty("typeDomainType")
	private String typeDomainType;

	/**
	 * The type domain code.
	 */
	@JsonProperty("typeDomainCode")
	private String typeDomainCode;

	/**
	 * The qgi version.
	 */
	@JsonProperty("qgiVersion")
	private Object qgiVersion;

	/**
	 * The assessment scores.
	 */
	@JsonProperty("assessmentScores")
	private List<AssessmentScoreView> assessmentScores = new ArrayList<AssessmentScoreView>();

	/**
	 * The assessment reports.
	 */
	@JsonProperty("assessmentReports")
	private List<AssessmentReportView> assessmentReports = new ArrayList<AssessmentReportView>();

	/**
	 * The additional properties.
	 */
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * Gets the id.
	 *
	 * @return The id
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id The id
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the acronym.
	 *
	 * @return The acronym
	 */
	@JsonProperty("acronym")
	public String getAcronym() {
		return acronym;
	}

	/**
	 * Sets the acronym.
	 *
	 * @param acronym The acronym
	 */
	@JsonProperty("acronym")
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	/**
	 * Gets the comments.
	 *
	 * @return The comments
	 */
	@JsonProperty("comments")
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments The comments
	 */
	@JsonProperty("comments")
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the expiry date.
	 *
	 * @return The expiryDate
	 */
	@JsonProperty("expiryDate")
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Sets the expiry date.
	 *
	 * @param expiryDate The expiryDate
	 */
	@JsonProperty("expiryDate")
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Gets the licensed by.
	 *
	 * @return The licensedBy
	 */
	@JsonProperty("licensedBy")
	public String getLicensedBy() {
		return licensedBy;
	}

	/**
	 * Sets the licensed by.
	 *
	 * @param licensedBy The licensedBy
	 */
	@JsonProperty("licensedBy")
	public void setLicensedBy(String licensedBy) {
		this.licensedBy = licensedBy;
	}

	/**
	 * Gets the name.
	 *
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the rec qualification level.
	 *
	 * @return The recQualificationLevel
	 */
	@JsonProperty("recQualificationLevel")
	public String getRecQualificationLevel() {
		return recQualificationLevel;
	}

	/**
	 * Sets the rec qualification level.
	 *
	 * @param recQualificationLevel The recQualificationLevel
	 */
	@JsonProperty("recQualificationLevel")
	public void setRecQualificationLevel(String recQualificationLevel) {
		this.recQualificationLevel = recQualificationLevel;
	}

	/**
	 * Gets the start date.
	 *
	 * @return The startDate
	 */
	@JsonProperty("startDate")
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate The startDate
	 */
	@JsonProperty("startDate")
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the status.
	 *
	 * @return The status
	 */
	@JsonProperty("status")
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status The status
	 */
	@JsonProperty("status")
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Gets the type id.
	 *
	 * @return The typeId
	 */
	@JsonProperty("typeId")
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * Sets the type id.
	 *
	 * @param typeId The typeId
	 */
	@JsonProperty("typeId")
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * Gets the guid.
	 *
	 * @return The guid
	 */
	@JsonProperty("guid")
	public String getGuid() {
		return guid;
	}

	/**
	 * Sets the guid.
	 *
	 * @param guid The guid
	 */
	@JsonProperty("guid")
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * Gets the assessment definition.
	 *
	 * @return The assessmentDefinition
	 */
	@JsonProperty("assessmentDefinition")
	public String getAssessmentDefinition() {
		return assessmentDefinition;
	}

	/**
	 * Sets the assessment definition.
	 *
	 * @param assessmentDefinition The assessmentDefinition
	 */
	@JsonProperty("assessmentDefinition")
	public void setAssessmentDefinition(String assessmentDefinition) {
		this.assessmentDefinition = assessmentDefinition;
	}

	/**
	 * Gets the data collection enabled.
	 *
	 * @return The dataCollectionEnabled
	 */
	@JsonProperty("dataCollectionEnabled")
	public String getDataCollectionEnabled() {
		return dataCollectionEnabled;
	}

	/**
	 * Sets the data collection enabled.
	 *
	 * @param dataCollectionEnabled The dataCollectionEnabled
	 */
	@JsonProperty("dataCollectionEnabled")
	public void setDataCollectionEnabled(String dataCollectionEnabled) {
		this.dataCollectionEnabled = dataCollectionEnabled;
	}

	/**
	 * Gets the api enabled.
	 *
	 * @return The apiEnabled
	 */
	@JsonProperty("apiEnabled")
	public String getApiEnabled() {
		return apiEnabled;
	}

	/**
	 * Sets the api enabled.
	 *
	 * @param apiEnabled The apiEnabled
	 */
	@JsonProperty("apiEnabled")
	public void setApiEnabled(String apiEnabled) {
		this.apiEnabled = apiEnabled;
	}

	/**
	 * Gets the product code.
	 *
	 * @return The productCode
	 */
	@JsonProperty("productCode")
	public String getProductCode() {
		return productCode;
	}

	/**
	 * Sets the product code.
	 *
	 * @param productCode The productCode
	 */
	@JsonProperty("productCode")
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * Gets the forms.
	 *
	 * @return The forms
	 */
	@JsonProperty("forms")
	public List<FormView> getForms() {
		return forms;
	}

	/**
	 * Sets the forms.
	 *
	 * @param forms The forms
	 */
	@JsonProperty("forms")
	public void setForms(List<FormView> forms) {
		this.forms = forms;
	}

	/**
	 * Gets the document.
	 *
	 * @return The document
	 */
	@JsonProperty("document")
	public List<DocumentView> getDocument() {
		return document;
	}

	/**
	 * Sets the document.
	 *
	 * @param document The document
	 */
	@JsonProperty("document")
	public void setDocument(List<DocumentView> document) {
		this.document = document;
	}

	/**
	 * Gets the created by.
	 *
	 * @return The createdBy
	 */
	@JsonProperty("createdBy")
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy The createdBy
	 */
	@JsonProperty("createdBy")
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the last created.
	 *
	 * @return The lastCreated
	 */
	@JsonProperty("lastCreated")
	public String getLastCreated() {
		return lastCreated;
	}

	/**
	 * Sets the last created.
	 *
	 * @param lastCreated The lastCreated
	 */
	@JsonProperty("lastCreated")
	public void setLastCreated(String lastCreated) {
		this.lastCreated = lastCreated;
	}

	/**
	 * Gets the last updated.
	 *
	 * @return The lastUpdated
	 */
	@JsonProperty("lastUpdated")
	public String getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * Sets the last updated.
	 *
	 * @param lastUpdated The lastUpdated
	 */
	@JsonProperty("lastUpdated")
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return The updatedBy
	 */
	@JsonProperty("updatedBy")
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy The updatedBy
	 */
	@JsonProperty("updatedBy")
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Gets the status type.
	 *
	 * @return The statusType
	 */
	@JsonProperty("statusType")
	public String getStatusType() {
		return statusType;
	}

	/**
	 * Sets the status type.
	 *
	 * @param statusType The statusType
	 */
	@JsonProperty("statusType")
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	/**
	 * Gets the status code.
	 *
	 * @return The statusCode
	 */
	@JsonProperty("statusCode")
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode The statusCode
	 */
	@JsonProperty("statusCode")
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Gets the type domain type.
	 *
	 * @return The typeDomainType
	 */
	@JsonProperty("typeDomainType")
	public String getTypeDomainType() {
		return typeDomainType;
	}

	/**
	 * Sets the type domain type.
	 *
	 * @param typeDomainType The typeDomainType
	 */
	@JsonProperty("typeDomainType")
	public void setTypeDomainType(String typeDomainType) {
		this.typeDomainType = typeDomainType;
	}

	/**
	 * Gets the type domain code.
	 *
	 * @return The typeDomainCode
	 */
	@JsonProperty("typeDomainCode")
	public String getTypeDomainCode() {
		return typeDomainCode;
	}

	/**
	 * Sets the type domain code.
	 *
	 * @param typeDomainCode The typeDomainCode
	 */
	@JsonProperty("typeDomainCode")
	public void setTypeDomainCode(String typeDomainCode) {
		this.typeDomainCode = typeDomainCode;
	}

	/**
	 * Gets the qgi version.
	 *
	 * @return The qgiVersion
	 */
	@JsonProperty("qgiVersion")
	public Object getQgiVersion() {
		return qgiVersion;
	}

	/**
	 * Sets the qgi version.
	 *
	 * @param qgiVersion The qgiVersion
	 */
	@JsonProperty("qgiVersion")
	public void setQgiVersion(Object qgiVersion) {
		this.qgiVersion = qgiVersion;
	}

	/**
	 * Gets the assessment scores.
	 *
	 * @return The assessmentScores
	 */
	@JsonProperty("assessmentScores")
	public List<AssessmentScoreView> getAssessmentScores() {
		return assessmentScores;
	}

	/**
	 * Sets the assessment scores.
	 *
	 * @param assessmentScores The assessmentScores
	 */
	@JsonProperty("assessmentScores")
	public void setAssessmentScores(List<AssessmentScoreView> assessmentScores) {
		this.assessmentScores = assessmentScores;
	}

	/**
	 * Gets the assessment reports.
	 *
	 * @return The assessmentReports
	 */
	@JsonProperty("assessmentReports")
	public List<AssessmentReportView> getAssessmentReports() {
		return assessmentReports;
	}

	/**
	 * Sets the assessment reports.
	 *
	 * @param assessmentReports The assessmentReports
	 */
	@JsonProperty("assessmentReports")
	public void setAssessmentReports(List<AssessmentReportView> assessmentReports) {
		this.assessmentReports = assessmentReports;
	}

	/**
	 * Gets the additional properties.
	 *
	 * @return the additional properties
	 */
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	/**
	 * Sets the additional property.
	 *
	 * @param name  the name
	 * @param value the value
	 */
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
