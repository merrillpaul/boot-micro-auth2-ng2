package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Entity;
import org.hibernate.validator.constraints.Length;

/**
 * Created by uphilji on 4/18/17.
 */
@Entity
public class Report extends RelationalEntity {

    /** The description. */
    private String description;

    /** The status. */
    private String statusId;

    /** The name. */
    @Length(max = 250)
    private String name;

    /** The product code. */
    @Length(max = 255)
    private String productCode;

    /** The report engine id. */
    private String reportEngineId;

    /** The report type id. */
    private String reportTypeId;

    /** The report definition. */
    private String reportDefinition;


    /*
        Getters and Setters
     */


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getReportEngineId() {
        return reportEngineId;
    }

    public void setReportEngineId(String reportEngineId) {
        this.reportEngineId = reportEngineId;
    }

    public String getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(String reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public String getReportDefinition() {
        return reportDefinition;
    }

    public void setReportDefinition(String reportDefinition) {
        this.reportDefinition = reportDefinition;
    }
}

