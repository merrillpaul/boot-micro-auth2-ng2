package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Column;
import org.hibernate.annotations.Type;

/**
 * Created by uphilji on 4/18/17.
 */
@Entity
public class FormScoringReport extends RelationalEntity {

       /** The form. */
    private String formId;

    /** The scoring. */
    private String scoringId;

    /** The report. */
    private String reportId;

    /** The form scoring map. */
    @Lob
    private String formScoringMap;

    /** The scoring report map. */
    @Lob
    private String scoringReportMap;

    /** The parent form scoring report id. */
    private String parentFormScoringReportId;

    /** The combo scoring. */
    private String comboScoringId;

    /** The combo parent. */
    @Column(name = "is_combo_parent")
    private boolean comboParent;

    /** The scoring combo scoring map. */
    @Lob
    private String scoringComboScoringMap;

    /** The update flag. */
    @Type(type = "yes_no")
    private boolean updateFlag;



    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getScoringId() {
        return scoringId;
    }

    public void setScoringId(String scoringId) {
        this.scoringId = scoringId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getFormScoringMap() {
        return formScoringMap;
    }

    public void setFormScoringMap(String formScoringMap) {
        this.formScoringMap = formScoringMap;
    }

    public String getScoringReportMap() {
        return scoringReportMap;
    }

    public void setScoringReportMap(String scoringReportMap) {
        this.scoringReportMap = scoringReportMap;
    }

    public String getParentFormScoringReportId() {
        return parentFormScoringReportId;
    }

    public void setParentFormScoringReportId(String parentFormScoringReportId) {
        this.parentFormScoringReportId = parentFormScoringReportId;
    }

    public String getComboScoringId() {
        return comboScoringId;
    }

    public void setComboScoringId(String comboScoringId) {
        this.comboScoringId = comboScoringId;
    }

    public boolean isComboParent() {
        return comboParent;
    }

    public void setComboParent(boolean comboParent) {
        this.comboParent = comboParent;
    }

    public String getScoringComboScoringMap() {
        return scoringComboScoringMap;
    }

    public void setScoringComboScoringMap(String scoringComboScoringMap) {
        this.scoringComboScoringMap = scoringComboScoringMap;
    }

    public boolean isUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(boolean updateFlag) {
        this.updateFlag = updateFlag;
    }
}
