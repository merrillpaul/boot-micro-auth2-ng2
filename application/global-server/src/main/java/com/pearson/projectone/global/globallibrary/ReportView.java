
package com.pearson.projectone.global.globallibrary;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pearson.projectone.core.utils.ObjectResolver;


import java.util.ArrayList;
import java.util.List;


/**
 * The Class ReportView.
 */
@JsonPropertyOrder({
    "id",
    "createdBy",
    "lastCreated",
    "lastUpdated",
    "updatedBy",
    "description",
    "status",
    "name",
    "productCode",
    "reportDefinition",
    "guid",
    "needsInventory",
    "multiAssessment",
    "statusType",
    "statusCode",
    "reportOutputTypes",
    "reportEngineId",
    "reportEngineIdType",
    "reportEngineIdCode",
    "reportTypeId",
    "reportTypeIdType",
    "reportTypeIdCode"
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ReportView.class
, resolver = ObjectResolver.class)
public class ReportView {

    /** The id. */
    @JsonProperty("id")
    private String id;
    
    /** The created by. */
    @JsonProperty("createdBy")
    private String createdBy;
    
    /** The last created. */
    @JsonProperty("lastCreated")
    private String lastCreated;
    
    /** The last updated. */
    @JsonProperty("lastUpdated")
    private String lastUpdated;
    
    /** The updated by. */
    @JsonProperty("updatedBy")
    private String updatedBy;
    
    /** The description. */
    @JsonProperty("description")
    private String description;
    
    /** The status. */
    @JsonProperty("status")
    private Integer status;
    
    /** The name. */
    @JsonProperty("name")
    private String name;
    
    /** The product code. */
    @JsonProperty("productCode")
    private String productCode;
    
    /** The report definition. */
    @JsonProperty("reportDefinition")
    private String reportDefinition;
    
    /** The guid. */
    @JsonProperty("guid")
    private String guid;
    
    /** The needs inventory. */
    @JsonProperty("needsInventory")
    private String needsInventory;
    
    /** The multi assessment. */
    @JsonProperty("multiAssessment")
    private Boolean multiAssessment;
    
    /** The status type. */
    @JsonProperty("statusType")
    private String statusType;
    
    /** The status code. */
    @JsonProperty("statusCode")
    private String statusCode;
    
    /** The report output types. */
    @JsonProperty("reportOutputTypes")
    private List<ReportOutputTypeView> reportOutputTypes = new ArrayList<ReportOutputTypeView>();
    
    /** The report engine id. */
    @JsonProperty("reportEngineId")
    private Integer reportEngineId;
    
    /** The report engine id type. */
    @JsonProperty("reportEngineIdType")
    private String reportEngineIdType;
    
    /** The report engine id code. */
    @JsonProperty("reportEngineIdCode")
    private String reportEngineIdCode;
    
    /** The report type id. */
    @JsonProperty("reportTypeId")
    private Integer reportTypeId;
    
    /** The report type id type. */
    @JsonProperty("reportTypeIdType")
    private String reportTypeIdType;
    
    /** The report type id code. */
    @JsonProperty("reportTypeIdCode")
    private String reportTypeIdCode;
   

    /**
     * Gets the id.
     *
     * @return     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the created by.
     *
     * @return     The createdBy
     */
    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy     The createdBy
     */
    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the last created.
     *
     * @return     The lastCreated
     */
    @JsonProperty("lastCreated")
    public String getLastCreated() {
        return lastCreated;
    }

    /**
     * Sets the last created.
     *
     * @param lastCreated     The lastCreated
     */
    @JsonProperty("lastCreated")
    public void setLastCreated(String lastCreated) {
        this.lastCreated = lastCreated;
    }

    /**
     * Gets the last updated.
     *
     * @return     The lastUpdated
     */
    @JsonProperty("lastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the last updated.
     *
     * @param lastUpdated     The lastUpdated
     */
    @JsonProperty("lastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Gets the updated by.
     *
     * @return     The updatedBy
     */
    @JsonProperty("updatedBy")
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Sets the updated by.
     *
     * @param updatedBy     The updatedBy
     */
    @JsonProperty("updatedBy")
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Gets the description.
     *
     * @return     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the status.
     *
     * @return     The status
     */
    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status     The status
     */
    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets the name.
     *
     * @return     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product code.
     *
     * @return     The productCode
     */
    @JsonProperty("productCode")
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the product code.
     *
     * @param productCode     The productCode
     */
    @JsonProperty("productCode")
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * Gets the report definition.
     *
     * @return     The reportDefinition
     */
    @JsonProperty("reportDefinition")
    public String getReportDefinition() {
        return reportDefinition;
    }

    /**
     * Sets the report definition.
     *
     * @param reportDefinition     The reportDefinition
     */
    @JsonProperty("reportDefinition")
    public void setReportDefinition(String reportDefinition) {
        this.reportDefinition = reportDefinition;
    }

    /**
     * Gets the guid.
     *
     * @return     The guid
     */
    @JsonProperty("guid")
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the guid.
     *
     * @param guid     The guid
     */
    @JsonProperty("guid")
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * Gets the needs inventory.
     *
     * @return     The needsInventory
     */
    @JsonProperty("needsInventory")
    public String getNeedsInventory() {
        return needsInventory;
    }

    /**
     * Sets the needs inventory.
     *
     * @param needsInventory     The needsInventory
     */
    @JsonProperty("needsInventory")
    public void setNeedsInventory(String needsInventory) {
        this.needsInventory = needsInventory;
    }

    /**
     * Gets the multi assessment.
     *
     * @return     The multiAssessment
     */
    @JsonProperty("multiAssessment")
    public Boolean getMultiAssessment() {
        return multiAssessment;
    }

    /**
     * Sets the multi assessment.
     *
     * @param multiAssessment     The multiAssessment
     */
    @JsonProperty("multiAssessment")
    public void setMultiAssessment(Boolean multiAssessment) {
        this.multiAssessment = multiAssessment;
    }

    /**
     * Gets the status type.
     *
     * @return     The statusType
     */
    @JsonProperty("statusType")
    public String getStatusType() {
        return statusType;
    }

    /**
     * Sets the status type.
     *
     * @param statusType     The statusType
     */
    @JsonProperty("statusType")
    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    /**
     * Gets the status code.
     *
     * @return     The statusCode
     */
    @JsonProperty("statusCode")
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the status code.
     *
     * @param statusCode     The statusCode
     */
    @JsonProperty("statusCode")
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets the report output types.
     *
     * @return     The reportOutputTypes
     */
    @JsonProperty("reportOutputTypes")
    public List<ReportOutputTypeView> getReportOutputTypes() {
        return reportOutputTypes;
    }

    /**
     * Sets the report output types.
     *
     * @param reportOutputTypes     The reportOutputTypes
     */
    @JsonProperty("reportOutputTypes")
    public void setReportOutputTypes(List<ReportOutputTypeView> reportOutputTypes) {
        this.reportOutputTypes = reportOutputTypes;
    }

    /**
     * Gets the report engine id.
     *
     * @return     The reportEngineId
     */
    @JsonProperty("reportEngineId")
    public Integer getReportEngineId() {
        return reportEngineId;
    }

    /**
     * Sets the report engine id.
     *
     * @param reportEngineId     The reportEngineId
     */
    @JsonProperty("reportEngineId")
    public void setReportEngineId(Integer reportEngineId) {
        this.reportEngineId = reportEngineId;
    }

    /**
     * Gets the report engine id type.
     *
     * @return     The reportEngineIdType
     */
    @JsonProperty("reportEngineIdType")
    public String getReportEngineIdType() {
        return reportEngineIdType;
    }

    /**
     * Sets the report engine id type.
     *
     * @param reportEngineIdType     The reportEngineIdType
     */
    @JsonProperty("reportEngineIdType")
    public void setReportEngineIdType(String reportEngineIdType) {
        this.reportEngineIdType = reportEngineIdType;
    }

    /**
     * Gets the report engine id code.
     *
     * @return     The reportEngineIdCode
     */
    @JsonProperty("reportEngineIdCode")
    public String getReportEngineIdCode() {
        return reportEngineIdCode;
    }

    /**
     * Sets the report engine id code.
     *
     * @param reportEngineIdCode     The reportEngineIdCode
     */
    @JsonProperty("reportEngineIdCode")
    public void setReportEngineIdCode(String reportEngineIdCode) {
        this.reportEngineIdCode = reportEngineIdCode;
    }

    /**
     * Gets the report type id.
     *
     * @return     The reportTypeId
     */
    @JsonProperty("reportTypeId")
    public Integer getReportTypeId() {
        return reportTypeId;
    }

    /**
     * Sets the report type id.
     *
     * @param reportTypeId     The reportTypeId
     */
    @JsonProperty("reportTypeId")
    public void setReportTypeId(Integer reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    /**
     * Gets the report type id type.
     *
     * @return     The reportTypeIdType
     */
    @JsonProperty("reportTypeIdType")
    public String getReportTypeIdType() {
        return reportTypeIdType;
    }

    /**
     * Sets the report type id type.
     *
     * @param reportTypeIdType     The reportTypeIdType
     */
    @JsonProperty("reportTypeIdType")
    public void setReportTypeIdType(String reportTypeIdType) {
        this.reportTypeIdType = reportTypeIdType;
    }

    /**
     * Gets the report type id code.
     *
     * @return     The reportTypeIdCode
     */
    @JsonProperty("reportTypeIdCode")
    public String getReportTypeIdCode() {
        return reportTypeIdCode;
    }

    /**
     * Sets the report type id code.
     *
     * @param reportTypeIdCode     The reportTypeIdCode
     */
    @JsonProperty("reportTypeIdCode")
    public void setReportTypeIdCode(String reportTypeIdCode) {
        this.reportTypeIdCode = reportTypeIdCode;
    }

   

}
