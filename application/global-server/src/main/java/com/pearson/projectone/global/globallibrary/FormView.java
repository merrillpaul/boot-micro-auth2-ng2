
package com.pearson.projectone.global.globallibrary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pearson.projectone.core.utils.ObjectResolver;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class FormView.
 */
@JsonPropertyOrder({
		"id",
		"acronym",
		"description",
		"status",
		"groupAdministration",
		"name",
		"guid",
		"productCode",
		"formDeliveryType",
		"formDefinition",
		"ngExportDefinition",
		"osaEngineId",
		"formOsaMap",
		"createdBy",
		"lastCreated",
		"lastUpdated",
		"updatedBy",
		"statusType",
		"statusCode",
		"formScoringReports",
		"parentformAcronym",
		"parentformGuid",
		"osaEngineType",
		"osaEngineCode"
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = FormView.class
		, resolver = ObjectResolver.class)
public class FormView {

	/**
	 * The id.
	 */
	@JsonProperty("id")
	private Integer id;

	/**
	 * The acronym.
	 */
	@JsonProperty("acronym")
	private String acronym;

	/**
	 * The description.
	 */
	@JsonProperty("description")
	private String description;

	/**
	 * The status.
	 */
	@JsonProperty("status")
	private String status;

	/**
	 * The group administration.
	 */
	@JsonProperty("groupAdministration")
	private Boolean groupAdministration;

	/**
	 * The name.
	 */
	@JsonProperty("name")
	private String name;

	/**
	 * The guid.
	 */
	@JsonProperty("guid")
	private String guid;

	/**
	 * The product code.
	 */
	@JsonProperty("productCode")
	private String productCode;

	/**
	 * The form delivery type.
	 */
	@JsonProperty("formDeliveryType")
	private List<FormDeliveryTypeView> formDeliveryType = new ArrayList<FormDeliveryTypeView>();

	/**
	 * The form definition.
	 */
	@JsonProperty("formDefinition")
	private String formDefinition;

	/**
	 * The ng export definition.
	 */
	@JsonProperty("ngExportDefinition")
	private String ngExportDefinition;

	/**
	 * The osa engine id.
	 */
	@JsonProperty("osaEngineId")
	private String osaEngineId;

	/**
	 * The form osa map.
	 */
	@JsonProperty("formOsaMap")
	private FormOsaMapView formOsaMap;

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
	 * The form scoring reports.
	 */
	@JsonProperty("formScoringReports")
	private List<FormScoringReportView> formScoringReports = new ArrayList<FormScoringReportView>();

	/**
	 * The parentform acronym.
	 */
	@JsonProperty("parentformAcronym")
	private String parentformAcronym;

	/**
	 * The parentform guid.
	 */
	@JsonProperty("parentformGuid")
	private String parentformGuid;

	/**
	 * The osa engine type.
	 */
	@JsonProperty("osaEngineType")
	private String osaEngineType;

	/**
	 * The osa engine code.
	 */
	@JsonProperty("osaEngineCode")
	private String osaEngineCode;


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
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status The status
	 */
	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the group administration.
	 *
	 * @return The groupAdministration
	 */
	@JsonProperty("groupAdministration")
	public Boolean getGroupAdministration() {
		return groupAdministration;
	}

	/**
	 * Sets the group administration.
	 *
	 * @param groupAdministration The groupAdministration
	 */
	@JsonProperty("groupAdministration")
	public void setGroupAdministration(Boolean groupAdministration) {
		this.groupAdministration = groupAdministration;
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
	 * Gets the form delivery type.
	 *
	 * @return The formDeliveryType
	 */
	@JsonProperty("formDeliveryType")
	public List<FormDeliveryTypeView> getFormDeliveryType() {
		return formDeliveryType;
	}

	/**
	 * Sets the form delivery type.
	 *
	 * @param formDeliveryType The formDeliveryType
	 */
	@JsonProperty("formDeliveryType")
	public void setFormDeliveryType(List<FormDeliveryTypeView> formDeliveryType) {
		this.formDeliveryType = formDeliveryType;
	}

	/**
	 * Gets the form definition.
	 *
	 * @return The formDefinition
	 */
	@JsonProperty("formDefinition")
	public String getFormDefinition() {
		return formDefinition;
	}

	/**
	 * Sets the form definition.
	 *
	 * @param formDefinition The formDefinition
	 */
	@JsonProperty("formDefinition")
	public void setFormDefinition(String formDefinition) {
		this.formDefinition = formDefinition;
	}

	/**
	 * Gets the ng export definition.
	 *
	 * @return The ngExportDefinition
	 */
	@JsonProperty("ngExportDefinition")
	public String getNgExportDefinition() {
		return ngExportDefinition;
	}

	/**
	 * Sets the ng export definition.
	 *
	 * @param ngExportDefinition The ngExportDefinition
	 */
	@JsonProperty("ngExportDefinition")
	public void setNgExportDefinition(String ngExportDefinition) {
		this.ngExportDefinition = ngExportDefinition;
	}

	/**
	 * Gets the osa engine id.
	 *
	 * @return The osaEngineId
	 */
	@JsonProperty("osaEngineId")
	public String getOsaEngineId() {
		return osaEngineId;
	}

	/**
	 * Sets the osa engine id.
	 *
	 * @param osaEngineId The osaEngineId
	 */
	@JsonProperty("osaEngineId")
	public void setOsaEngineId(String osaEngineId) {
		this.osaEngineId = osaEngineId;
	}

	/**
	 * Gets the form osa map.
	 *
	 * @return The formOsaMap
	 */
	@JsonProperty("formOsaMap")
	public FormOsaMapView getFormOsaMap() {
		return formOsaMap;
	}

	/**
	 * Sets the form osa map.
	 *
	 * @param formOsaMap The formOsaMap
	 */
	@JsonProperty("formOsaMap")
	public void setFormOsaMap(FormOsaMapView formOsaMap) {
		this.formOsaMap = formOsaMap;
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
	 * Gets the form scoring reports.
	 *
	 * @return The formScoringReports
	 */
	@JsonProperty("formScoringReports")
	public List<FormScoringReportView> getFormScoringReports() {
		return formScoringReports;
	}

	/**
	 * Sets the form scoring reports.
	 *
	 * @param formScoringReports The formScoringReports
	 */
	@JsonProperty("formScoringReports")
	public void setFormScoringReports(List<FormScoringReportView> formScoringReports) {
		this.formScoringReports = formScoringReports;
	}

	/**
	 * Gets the parentform acronym.
	 *
	 * @return The parentformAcronym
	 */
	@JsonProperty("parentformAcronym")
	public String getParentformAcronym() {
		return parentformAcronym;
	}

	/**
	 * Sets the parentform acronym.
	 *
	 * @param parentformAcronym The parentformAcronym
	 */
	@JsonProperty("parentformAcronym")
	public void setParentformAcronym(String parentformAcronym) {
		this.parentformAcronym = parentformAcronym;
	}

	/**
	 * Gets the parentform guid.
	 *
	 * @return The parentformGuid
	 */
	@JsonProperty("parentformGuid")
	public String getParentformGuid() {
		return parentformGuid;
	}

	/**
	 * Sets the parentform guid.
	 *
	 * @param parentformGuid The parentformGuid
	 */
	@JsonProperty("parentformGuid")
	public void setParentformGuid(String parentformGuid) {
		this.parentformGuid = parentformGuid;
	}

	/**
	 * Gets the osa engine type.
	 *
	 * @return The osaEngineType
	 */
	@JsonProperty("osaEngineType")
	public String getOsaEngineType() {
		return osaEngineType;
	}

	/**
	 * Sets the osa engine type.
	 *
	 * @param osaEngineType The osaEngineType
	 */
	@JsonProperty("osaEngineType")
	public void setOsaEngineType(String osaEngineType) {
		this.osaEngineType = osaEngineType;
	}

	/**
	 * Gets the osa engine code.
	 *
	 * @return The osaEngineCode
	 */
	@JsonProperty("osaEngineCode")
	public String getOsaEngineCode() {
		return osaEngineCode;
	}

	/**
	 * Sets the osa engine code.
	 *
	 * @param osaEngineCode The osaEngineCode
	 */
	@JsonProperty("osaEngineCode")
	public void setOsaEngineCode(String osaEngineCode) {
		this.osaEngineCode = osaEngineCode;
	}


}
