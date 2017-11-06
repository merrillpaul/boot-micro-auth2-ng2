package com.pearson.projectone.global.dto.globallibrary;

import com.pearson.projectone.data.entity.global.library.assessment.*;
import java.util.List;


/**
 * Created by uphilji on 4/18/17.
 */
public class GlobalLibraryDto {
    /** The assessments. */
    private List<Assessment> assessments;

    /** The forms. */
    private List<Form> forms;

    /** The form delivery types. */
    private List<FormDeliveryType> formDeliveryTypes;

    /** The form OSA map. */
    private List<FormOSAMap> formOSAMaps;

    /** The scorings. */
    private List<Scoring> scorings;

    /** The assessment scorings. */
    private List<AssessmentScoring> assessmentScorings;

    /** The reports. */
    private List<Report> reports;

    /** The assessment reports. */
    private List<AssessmentReport> assessmentReports;

    /** The report output types. */
    private List<ReportOutputType> reportOutputTypes;

    /** The form scoring reports. */
    private List<FormScoringReport> formScoringReports;

    /**
     * Gets the assessment scorings.
     *
     * @return the assessment scorings
     */
    public List<AssessmentScoring> getAssessmentScorings() {
        return assessmentScorings;
    }

    /**
     * Sets the assessment scorings.
     *
     * @param assessmentScorings
     *            the new assessment scorings
     */
    public void setAssessmentScorings(final List<AssessmentScoring> assessmentScorings) {
        this.assessmentScorings = assessmentScorings;
    }

    /**
     * Gets the form delivery types.
     *
     * @return the form delivery types
     */
    public List<FormDeliveryType> getFormDeliveryTypes() {
        return formDeliveryTypes;
    }

    /**
     * Sets the form delivery types.
     *
     * @param formDeliveryTypes
     *            the new form delivery types
     */
    public void setFormDeliveryTypes(final List<FormDeliveryType> formDeliveryTypes) {
        this.formDeliveryTypes = formDeliveryTypes;
    }

    /**
     * Gets the assessments.
     *
     * @return the assessments
     */
    public List<Assessment> getAssessments() {
        return assessments;
    }

    /**
     * Sets the assessments.
     *
     * @param assessments
     *            the new assessments
     */
    public void setAssessments(final List<Assessment> assessments) {
        this.assessments = assessments;
    }

    /**
     * Gets the forms.
     *
     * @return the forms
     */
    public List<Form> getForms() {
        return forms;
    }

    /**
     * Sets the forms.
     *
     * @param forms
     *            the new forms
     */
    public void setForms(final List<Form> forms) {
        this.forms = forms;
    }

    /**
     * Gets the scorings.
     *
     * @return the scorings
     */
    public List<Scoring> getScorings() {
        return scorings;
    }

    /**
     * Sets the scorings.
     *
     * @param scorings
     *            the new scorings
     */
    public void setScorings(final List<Scoring> scorings) {
        this.scorings = scorings;
    }

    /**
     * Gets the reports.
     *
     * @return the reports
     */
    public List<Report> getReports() {
        return reports;
    }

    /**
     * Sets the reports.
     *
     * @param reports the new reports
     */
    public void setReports(final List<Report> reports) {
        this.reports = reports;
    }

    /**
     * Gets the assessment reports.
     *
     * @return the assessment reports
     */
    public List<AssessmentReport> getAssessmentReports() {
        return assessmentReports;
    }

    /**
     * Sets the assessment reports.
     *
     * @param assessmentReports the new assessment reports
     */
    public void setAssessmentReports(final List<AssessmentReport> assessmentReports) {
        this.assessmentReports = assessmentReports;
    }

    /**
     * Gets the report output types.
     *
     * @return the report output types
     */
    public List<ReportOutputType> getReportOutputTypes() {
        return reportOutputTypes;
    }

    /**
     * Sets the report output types.
     *
     * @param reportOutputTypes the new report output types
     */
    public void setReportOutputTypes(final List<ReportOutputType> reportOutputTypes) {
        this.reportOutputTypes = reportOutputTypes;
    }

    /**
     * Gets the form OSA maps.
     *
     * @return the form OSA maps
     */
    public List<FormOSAMap> getFormOSAMaps() {
        return formOSAMaps;
    }

    /**
     * Sets the form OSA maps.
     *
     * @param formOSAMaps the new form OSA maps
     */
    public void setFormOSAMaps(final List<FormOSAMap> formOSAMaps) {
        this.formOSAMaps = formOSAMaps;
    }

    /**
     * Gets the form scoring reports.
     *
     * @return the form scoring reports
     */
    public List<FormScoringReport> getFormScoringReports() {
        return formScoringReports;
    }

    /**
     * Sets the form scoring reports.
     *
     * @param formScoringReports the new form scoring reports
     */
    public void setFormScoringReports(final List<FormScoringReport> formScoringReports) {
        this.formScoringReports = formScoringReports;
    }
}
