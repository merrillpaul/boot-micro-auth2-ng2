
package com.pearson.projectone.global.globallibrary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pearson.projectone.core.utils.ObjectResolver;


/**
 * The Class DocumentView.
 */
@JsonPropertyOrder({
		"id",
		"documentName",
		"description",
		"status",
		"fileName",
		"flexPaper",
		"createdBy",
		"lastCreated",
		"lastUpdated",
		"updatedBy"
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = DocumentView.class
		, resolver = ObjectResolver.class)
public class DocumentView {

	/**
	 * The id.
	 */
	@JsonProperty("id")
	private Integer id;

	/**
	 * The document name.
	 */
	@JsonProperty("documentName")
	private String documentName;

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
	 * The file name.
	 */
	@JsonProperty("fileName")
	private String fileName;

	/**
	 * The flex paper.
	 */
	@JsonProperty("flexPaper")
	private Boolean flexPaper;

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
	 * Gets the document name.
	 *
	 * @return The documentName
	 */
	@JsonProperty("documentName")
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * Sets the document name.
	 *
	 * @param documentName The documentName
	 */
	@JsonProperty("documentName")
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
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
	 * Gets the file name.
	 *
	 * @return The fileName
	 */
	@JsonProperty("fileName")
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName The fileName
	 */
	@JsonProperty("fileName")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the flex paper.
	 *
	 * @return The flexPaper
	 */
	@JsonProperty("flexPaper")
	public Boolean getFlexPaper() {
		return flexPaper;
	}

	/**
	 * Sets the flex paper.
	 *
	 * @param flexPaper The flexPaper
	 */
	@JsonProperty("flexPaper")
	public void setFlexPaper(Boolean flexPaper) {
		this.flexPaper = flexPaper;
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


}
