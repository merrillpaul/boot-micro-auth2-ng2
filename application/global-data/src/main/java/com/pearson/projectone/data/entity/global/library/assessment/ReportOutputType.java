package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Entity;

/**
 * Created by uphilji on 4/18/17.
 */
@Entity
public class ReportOutputType extends RelationalEntity {
    /** The report output type id. */
    private String reportOutputTypeId;

    // bi-directional many-to-one association to Report
    /** The report. */
    private String reportId;

    public String getReportOutputTypeId() {
        return reportOutputTypeId;
    }

    public void setReportOutputTypeId(String reportOutputTypeId) {
        this.reportOutputTypeId = reportOutputTypeId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
}
