
package com.pearson.projectone.global.globallibrary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pearson.projectone.core.utils.ObjectResolver;

import javax.xml.bind.annotation.XmlTransient;


/**
 * The Class AssessmentScoreView.
 */
@JsonPropertyOrder({"id", "createdBy", "lastCreated", "lastUpdated", "updatedBy", "scoringId", "scoringAcronym",
		"guid"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AssessmentScoreView.class
		, resolver = ObjectResolver.class)
public class AssessmentScoreView {

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
	 * The scoring id.
	 */
	@JsonProperty("scoringId")
	private ScoringView scoringId;

	/**
	 * The scoring acronym.
	 */
	@JsonProperty("scoringAcronym")
	private String scoringAcronym;

	/**
	 * The guid.
	 */
	@JsonProperty("guid")
	private String guid;

	/**
	 * The scoring guid.
	 */
	@XmlTransient
	private String scoringGuid;

	/**
	 * Gets the scoring guid.
	 *
	 * @return the scoring guid
	 */
	public String getScoringGuid() {
		return scoringGuid;
	}

	/**
	 * Sets the scoring guid.
	 *
	 * @param scoringGuid the new scoring guid
	 */
	public void setScoringGuid(final String scoringGuid) {
		this.scoringGuid = scoringGuid;
	}

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
	 * Gets the scoring id.
	 *
	 * @return The scoringId
	 */
	@JsonProperty("scoringId")
	public ScoringView getScoringId() {
		return scoringId;
	}

	/**
	 * Sets the scoring id.
	 *
	 * @param scoringId The scoringId
	 */
	@JsonProperty("scoringId")
	public void setScoringId(ScoringView scoringId) {
		this.scoringId = scoringId;
	}

	/**
	 * Gets the scoring acronym.
	 *
	 * @return The scoringAcronym
	 */
	@JsonProperty("scoringAcronym")
	public String getScoringAcronym() {
		return scoringAcronym;
	}

	/**
	 * Sets the scoring acronym.
	 *
	 * @param scoringAcronym The scoringAcronym
	 */
	@JsonProperty("scoringAcronym")
	public void setScoringAcronym(String scoringAcronym) {
		this.scoringAcronym = scoringAcronym;
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
