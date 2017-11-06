
package com.pearson.projectone.global.globallibrary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pearson.projectone.core.utils.ObjectResolver;


/**
 * The Class FormScoringReportView.
 */
@JsonPropertyOrder({
		"id",
		"createdBy",
		"lastCreated",
		"lastUpdated",
		"updatedBy",
		"scoring",
		"report",
		"formScoringMap",
		"scoringReportMap",
		"parentFormScoringReportId",
		"comboScoring",
		"scoringComboScoringMap",
		"comboParent",
		"guid",
		"updateFlag"
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = FormScoringReportView.class
		, resolver = ObjectResolver.class)
public class FormScoringReportView {
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
	 * The scoring.
	 */
	@JsonProperty("scoring")
	private ScoringView scoring;

	/**
	 * The report.
	 */
	@JsonProperty("report")
	private ReportView report;

	/**
	 * The form scoring map.
	 */
	@JsonProperty("formScoringMap")
	private String formScoringMap;

	/**
	 * The scoring report map.
	 */
	@JsonProperty("scoringReportMap")
	private String scoringReportMap;

	/**
	 * The parent form scoring report id.
	 */
	@JsonProperty("parentFormScoringReportId")
	private String parentFormScoringReportId;

	/**
	 * The combo scoring.
	 */
	@JsonProperty("comboScoring")
	private ScoringView comboScoring;

	/**
	 * The scoring combo scoring map.
	 */
	@JsonProperty("scoringComboScoringMap")
	private String scoringComboScoringMap;

	/**
	 * The combo parent.
	 */
	@JsonProperty("comboParent")
	private Boolean comboParent;

	/**
	 * The guid.
	 */
	@JsonProperty("guid")
	private String guid;

	/**
	 * The update flag.
	 */
	@JsonProperty("updateFlag")
	private Boolean updateFlag;


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
	 * Gets the scoring.
	 *
	 * @return The scoring
	 */
	@JsonProperty("scoring")
	public ScoringView getScoring() {
		return scoring;
	}

	/**
	 * Sets the scoring.
	 *
	 * @param scoring The scoring
	 */
	@JsonProperty("scoring")
	public void setScoring(ScoringView scoring) {
		this.scoring = scoring;
	}

	/**
	 * Gets the report.
	 *
	 * @return The report
	 */
	@JsonProperty("report")
	public ReportView getReport() {
		return report;
	}

	/**
	 * Sets the report.
	 *
	 * @param report The report
	 */
	@JsonProperty("report")
	public void setReport(ReportView report) {
		this.report = report;
	}

	/**
	 * Gets the form scoring map.
	 *
	 * @return The formScoringMap
	 */
	@JsonProperty("formScoringMap")
	public String getFormScoringMap() {
		return formScoringMap;
	}

	/**
	 * Sets the form scoring map.
	 *
	 * @param formScoringMap The formScoringMap
	 */
	@JsonProperty("formScoringMap")
	public void setFormScoringMap(String formScoringMap) {
		this.formScoringMap = formScoringMap;
	}

	/**
	 * Gets the scoring report map.
	 *
	 * @return The scoringReportMap
	 */
	@JsonProperty("scoringReportMap")
	public String getScoringReportMap() {
		return scoringReportMap;
	}

	/**
	 * Sets the scoring report map.
	 *
	 * @param scoringReportMap The scoringReportMap
	 */
	@JsonProperty("scoringReportMap")
	public void setScoringReportMap(String scoringReportMap) {
		this.scoringReportMap = scoringReportMap;
	}

	/**
	 * Gets the parent form scoring report id.
	 *
	 * @return The parentFormScoringReportId
	 */
	@JsonProperty("parentFormScoringReportId")
	public String getParentFormScoringReportId() {
		return parentFormScoringReportId;
	}

	/**
	 * Sets the parent form scoring report id.
	 *
	 * @param parentFormScoringReportId The parentFormScoringReportId
	 */
	@JsonProperty("parentFormScoringReportId")
	public void setParentFormScoringReportId(String parentFormScoringReportId) {
		this.parentFormScoringReportId = parentFormScoringReportId;
	}

	/**
	 * Gets the combo scoring.
	 *
	 * @return The comboScoring
	 */
	@JsonProperty("comboScoring")
	public ScoringView getComboScoring() {
		return comboScoring;
	}

	/**
	 * Sets the combo scoring.
	 *
	 * @param comboScoring The comboScoring
	 */
	@JsonProperty("comboScoring")
	public void setComboScoring(ScoringView comboScoring) {
		this.comboScoring = comboScoring;
	}

	/**
	 * Gets the scoring combo scoring map.
	 *
	 * @return The scoringComboScoringMap
	 */
	@JsonProperty("scoringComboScoringMap")
	public String getScoringComboScoringMap() {
		return scoringComboScoringMap;
	}

	/**
	 * Sets the scoring combo scoring map.
	 *
	 * @param scoringComboScoringMap The scoringComboScoringMap
	 */
	@JsonProperty("scoringComboScoringMap")
	public void setScoringComboScoringMap(String scoringComboScoringMap) {
		this.scoringComboScoringMap = scoringComboScoringMap;
	}

	/**
	 * Gets the combo parent.
	 *
	 * @return The comboParent
	 */
	@JsonProperty("comboParent")
	public Boolean getComboParent() {
		return comboParent;
	}

	/**
	 * Sets the combo parent.
	 *
	 * @param comboParent The comboParent
	 */
	@JsonProperty("comboParent")
	public void setComboParent(Boolean comboParent) {
		this.comboParent = comboParent;
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
	 * Gets the update flag.
	 *
	 * @return The updateFlag
	 */
	@JsonProperty("updateFlag")
	public Boolean getUpdateFlag() {
		return updateFlag;
	}

	/**
	 * Sets the update flag.
	 *
	 * @param updateFlag The updateFlag
	 */
	@JsonProperty("updateFlag")
	public void setUpdateFlag(Boolean updateFlag) {
		this.updateFlag = updateFlag;
	}


}
