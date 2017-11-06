
package com.pearson.projectone.global.globallibrary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pearson.projectone.core.utils.ObjectResolver;


/**
 * The Class FormDeliveryTypeView.
 */
@JsonPropertyOrder({
		"id",
		"createdBy",
		"lastCreated",
		"lastUpdated",
		"updatedBy",
		"guid",
		"deliveryTypeId",
		"deliveryTypeIdType",
		"deliveryTypeIdCode"
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = FormDeliveryTypeView.class
		, resolver = ObjectResolver.class)
public class FormDeliveryTypeView {

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
	 * The guid.
	 */
	@JsonProperty("guid")
	private String guid;

	/**
	 * The delivery type id.
	 */
	@JsonProperty("deliveryTypeId")
	private Integer deliveryTypeId;

	/**
	 * The delivery type id type.
	 */
	@JsonProperty("deliveryTypeIdType")
	private String deliveryTypeIdType;

	/**
	 * The delivery type id code.
	 */
	@JsonProperty("deliveryTypeIdCode")
	private String deliveryTypeIdCode;


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
	 * Gets the delivery type id.
	 *
	 * @return The deliveryTypeId
	 */
	@JsonProperty("deliveryTypeId")
	public Integer getDeliveryTypeId() {
		return deliveryTypeId;
	}

	/**
	 * Sets the delivery type id.
	 *
	 * @param deliveryTypeId The deliveryTypeId
	 */
	@JsonProperty("deliveryTypeId")
	public void setDeliveryTypeId(Integer deliveryTypeId) {
		this.deliveryTypeId = deliveryTypeId;
	}

	/**
	 * Gets the delivery type id type.
	 *
	 * @return The deliveryTypeIdType
	 */
	@JsonProperty("deliveryTypeIdType")
	public String getDeliveryTypeIdType() {
		return deliveryTypeIdType;
	}

	/**
	 * Sets the delivery type id type.
	 *
	 * @param deliveryTypeIdType The deliveryTypeIdType
	 */
	@JsonProperty("deliveryTypeIdType")
	public void setDeliveryTypeIdType(String deliveryTypeIdType) {
		this.deliveryTypeIdType = deliveryTypeIdType;
	}

	/**
	 * Gets the delivery type id code.
	 *
	 * @return The deliveryTypeIdCode
	 */
	@JsonProperty("deliveryTypeIdCode")
	public String getDeliveryTypeIdCode() {
		return deliveryTypeIdCode;
	}

	/**
	 * Sets the delivery type id code.
	 *
	 * @param deliveryTypeIdCode The deliveryTypeIdCode
	 */
	@JsonProperty("deliveryTypeIdCode")
	public void setDeliveryTypeIdCode(String deliveryTypeIdCode) {
		this.deliveryTypeIdCode = deliveryTypeIdCode;
	}


}
