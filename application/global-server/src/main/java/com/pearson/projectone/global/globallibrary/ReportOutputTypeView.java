
package com.pearson.projectone.global.globallibrary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pearson.projectone.core.utils.ObjectResolver;


/**
 * The Class ReportOutputTypeView.
 */
@JsonPropertyOrder({
		"id",
		"createdBy",
		"lastCreated",
		"lastUpdated",
		"updatedBy",
		"reportOutputTypeId",
		"reportOutputTypeIdType",
		"reportOutputTypeIdCode",
		"guid"
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ReportOutputTypeView.class
		, resolver = ObjectResolver.class)
public class ReportOutputTypeView {

	/**
	 * The id.
	 */
	@JsonProperty("id")
	private Integer id;

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
	 * The report output type id.
	 */
	@JsonProperty("reportOutputTypeId")
	private Integer reportOutputTypeId;

	/**
	 * The report output type id type.
	 */
	@JsonProperty("reportOutputTypeIdType")
	private String reportOutputTypeIdType;

	/**
	 * The report output type id code.
	 */
	@JsonProperty("reportOutputTypeIdCode")
	private String reportOutputTypeIdCode;

	/**
	 * The guid.
	 */
	@JsonProperty("guid")
	private String guid;


	/**
	 * Gets the id.
	 *
	 * @return The id
	 */
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id The id
	 */
	@JsonProperty("id")
	public void setId(Integer id) {
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
	 * Gets the report output type id.
	 *
	 * @return The reportOutputTypeId
	 */
	@JsonProperty("reportOutputTypeId")
	public Integer getReportOutputTypeId() {
		return reportOutputTypeId;
	}

	/**
	 * Sets the report output type id.
	 *
	 * @param reportOutputTypeId The reportOutputTypeId
	 */
	@JsonProperty("reportOutputTypeId")
	public void setReportOutputTypeId(Integer reportOutputTypeId) {
		this.reportOutputTypeId = reportOutputTypeId;
	}

	/**
	 * Gets the report output type id type.
	 *
	 * @return The reportOutputTypeIdType
	 */
	@JsonProperty("reportOutputTypeIdType")
	public String getReportOutputTypeIdType() {
		return reportOutputTypeIdType;
	}

	/**
	 * Sets the report output type id type.
	 *
	 * @param reportOutputTypeIdType The reportOutputTypeIdType
	 */
	@JsonProperty("reportOutputTypeIdType")
	public void setReportOutputTypeIdType(String reportOutputTypeIdType) {
		this.reportOutputTypeIdType = reportOutputTypeIdType;
	}

	/**
	 * Gets the report output type id code.
	 *
	 * @return The reportOutputTypeIdCode
	 */
	@JsonProperty("reportOutputTypeIdCode")
	public String getReportOutputTypeIdCode() {
		return reportOutputTypeIdCode;
	}

	/**
	 * Sets the report output type id code.
	 *
	 * @param reportOutputTypeIdCode The reportOutputTypeIdCode
	 */
	@JsonProperty("reportOutputTypeIdCode")
	public void setReportOutputTypeIdCode(String reportOutputTypeIdCode) {
		this.reportOutputTypeIdCode = reportOutputTypeIdCode;
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


}
