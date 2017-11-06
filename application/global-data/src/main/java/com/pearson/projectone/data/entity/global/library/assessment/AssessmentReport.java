package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Entity;

/**
 * Created by uphilji on 4/18/17.
 */
@Entity
public class AssessmentReport extends RelationalEntity {

    private String id;

    private String assessmentId;

    private String reportId;


    /*
     * Getters and Setters...
     *
     */
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
}
