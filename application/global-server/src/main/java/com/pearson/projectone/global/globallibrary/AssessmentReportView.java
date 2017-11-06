
package com.pearson.projectone.global.globallibrary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pearson.projectone.core.utils.ObjectResolver;

import javax.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.Map;


/**
 * The Class AssessmentReportView.
 */
@JsonPropertyOrder({
		"id",
		"reportId",
		"createdBy",
		"lastCreated",
		"lastUpdated",
		"updatedBy",
		"guid"
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AssessmentReportView.class
		, resolver = ObjectResolver.class)
public class AssessmentReportView {

	/**
	 * The id.
	 */
	@JsonProperty("id")
	private Integer id;

	/**
	 * The report id.
	 */
	@JsonProperty("reportId")
	private String reportId;

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
	 * The guid.
	 */
	@JsonProperty("guid")
	private String guid;

	/**
	 * The report guid.
	 */
	@XmlTransient
	private String reportGuid;

	/**
	 * Gets the report guid.
	 *
	 * @return the report guid
	 */
	public String getReportGuid() {
		return reportGuid;
	}

	/**
	 * Sets the report guid.
	 *
	 * @param reportGuid the new report guid
	 */
	public void setReportGuid(final String reportGuid) {
		this.reportGuid = reportGuid;
	}

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
	 * Gets the report id.
	 *
	 * @return The reportId
	 */
	@JsonProperty("reportId")
	public String getReportId() {
		return reportId;
	}

	/**
	 * Sets the report id.
	 *
	 * @param reportId The reportId
	 */
	@JsonProperty("reportId")
	public void setReportId(String reportId) {
		this.reportId = reportId;
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
