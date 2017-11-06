package com.pearson.projectone.global.globallibrary;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pearson.projectone.core.utils.ObjectResolver;

import java.util.HashMap;
import java.util.Map;


/**
 * The Class ScoringView.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({"id", "createdBy", "lastCreated", "lastUpdated", "updatedBy", "name", "acronym", "scoringEngineId",
		"scoringEngineIdType", "scoringEngineIdCode", "scoringTypeId", "scoringTypeIdType", "scoringTypeIdCode",
		"scoringDefinition", "description", "status", "statusCode", "statusType", "guid"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ScoringView.class
		, resolver = ObjectResolver.class)
public class ScoringView {

	/**
	 * The id.
	 */
	@JsonProperty("id")
	private String id;

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
	 * The name.
	 */
	@JsonProperty("name")
	private String name;

	/**
	 * The acronym.
	 */
	@JsonProperty("acronym")
	private String acronym;

	/**
	 * The scoring engine id.
	 */
	@JsonProperty("scoringEngineId")
	private Integer scoringEngineId;

	/**
	 * The scoring engine id type.
	 */
	@JsonProperty("scoringEngineIdType")
	private String scoringEngineIdType;

	/**
	 * The scoring engine id code.
	 */
	@JsonProperty("scoringEngineIdCode")
	private String scoringEngineIdCode;

	/**
	 * The scoring type id.
	 */
	@JsonProperty("scoringTypeId")
	private Integer scoringTypeId;

	/**
	 * The scoring type id type.
	 */
	@JsonProperty("scoringTypeIdType")
	private String scoringTypeIdType;

	/**
	 * The scoring type id code.
	 */
	@JsonProperty("scoringTypeIdCode")
	private String scoringTypeIdCode;

	/**
	 * The scoring definition.
	 */
	@JsonProperty("scoringDefinition")
	private String scoringDefinition;

	/**
	 * The description.
	 */
	@JsonProperty("description")
	private String description;

	/**
	 * The status.
	 */
	@JsonProperty("status")
	private Integer status;

	/**
	 * The status code.
	 */
	@JsonProperty("statusCode")
	private String statusCode;

	/**
	 * The status type.
	 */
	@JsonProperty("statusType")
	private String statusType;

	/**
	 * The guid.
	 */
	@JsonProperty("guid")
	private String guid;

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
	 * Gets the scoring engine id.
	 *
	 * @return The scoringEngineId
	 */
	@JsonProperty("scoringEngineId")
	public Integer getScoringEngineId() {
		return scoringEngineId;
	}

	/**
	 * Sets the scoring engine id.
	 *
	 * @param scoringEngineId The scoringEngineId
	 */
	@JsonProperty("scoringEngineId")
	public void setScoringEngineId(Integer scoringEngineId) {
		this.scoringEngineId = scoringEngineId;
	}

	/**
	 * Gets the scoring engine id type.
	 *
	 * @return The scoringEngineIdType
	 */
	@JsonProperty("scoringEngineIdType")
	public String getScoringEngineIdType() {
		return scoringEngineIdType;
	}

	/**
	 * Sets the scoring engine id type.
	 *
	 * @param scoringEngineIdType The scoringEngineIdType
	 */
	@JsonProperty("scoringEngineIdType")
	public void setScoringEngineIdType(String scoringEngineIdType) {
		this.scoringEngineIdType = scoringEngineIdType;
	}

	/**
	 * Gets the scoring engine id code.
	 *
	 * @return The scoringEngineIdCode
	 */
	@JsonProperty("scoringEngineIdCode")
	public String getScoringEngineIdCode() {
		return scoringEngineIdCode;
	}

	/**
	 * Sets the scoring engine id code.
	 *
	 * @param scoringEngineIdCode The scoringEngineIdCode
	 */
	@JsonProperty("scoringEngineIdCode")
	public void setScoringEngineIdCode(String scoringEngineIdCode) {
		this.scoringEngineIdCode = scoringEngineIdCode;
	}

	/**
	 * Gets the scoring type id.
	 *
	 * @return The scoringTypeId
	 */
	@JsonProperty("scoringTypeId")
	public Integer getScoringTypeId() {
		return scoringTypeId;
	}

	/**
	 * Sets the scoring type id.
	 *
	 * @param scoringTypeId The scoringTypeId
	 */
	@JsonProperty("scoringTypeId")
	public void setScoringTypeId(Integer scoringTypeId) {
		this.scoringTypeId = scoringTypeId;
	}

	/**
	 * Gets the scoring type id type.
	 *
	 * @return The scoringTypeIdType
	 */
	@JsonProperty("scoringTypeIdType")
	public String getScoringTypeIdType() {
		return scoringTypeIdType;
	}

	/**
	 * Sets the scoring type id type.
	 *
	 * @param scoringTypeIdType The scoringTypeIdType
	 */
	@JsonProperty("scoringTypeIdType")
	public void setScoringTypeIdType(String scoringTypeIdType) {
		this.scoringTypeIdType = scoringTypeIdType;
	}

	/**
	 * Gets the scoring type id code.
	 *
	 * @return The scoringTypeIdCode
	 */
	@JsonProperty("scoringTypeIdCode")
	public String getScoringTypeIdCode() {
		return scoringTypeIdCode;
	}

	/**
	 * Sets the scoring type id code.
	 *
	 * @param scoringTypeIdCode The scoringTypeIdCode
	 */
	@JsonProperty("scoringTypeIdCode")
	public void setScoringTypeIdCode(String scoringTypeIdCode) {
		this.scoringTypeIdCode = scoringTypeIdCode;
	}

	/**
	 * Gets the scoring definition.
	 *
	 * @return The scoringDefinition
	 */
	@JsonProperty("scoringDefinition")
	public String getScoringDefinition() {
		return scoringDefinition;
	}

	/**
	 * Sets the scoring definition.
	 *
	 * @param scoringDefinition The scoringDefinition
	 */
	@JsonProperty("scoringDefinition")
	public void setScoringDefinition(String scoringDefinition) {
		this.scoringDefinition = scoringDefinition;
	}

	/**
	 * Gets the description.
	 *
	 * @return The description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description The description
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
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
