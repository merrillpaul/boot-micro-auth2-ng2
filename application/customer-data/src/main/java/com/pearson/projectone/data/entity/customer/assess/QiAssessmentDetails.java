package com.pearson.projectone.data.entity.customer.assess;

import com.pearson.projectone.core.support.data.DocumentEntity;
import com.pearson.projectone.core.support.data.mongodb.Json;
import com.pearson.projectone.data.constants.AssessProgressState;
import com.pearson.projectone.data.constants.AssessmentType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "QiAssessmentDetails")
public class QiAssessmentDetails extends DocumentEntity {

	private String examineeAssessmentId;

	private AssessProgressState progressState;

	private Date exportTime;

	private AssessmentType assessmentType;

	private String externalApp;

	private boolean syncSucceeded;

	@Json
	private String resultsJson;

	public String getExamineeAssessmentId() {
		return examineeAssessmentId;
	}

	public void setExamineeAssessmentId(String examineeAssessmentId) {
		this.examineeAssessmentId = examineeAssessmentId;
	}

	public AssessProgressState getProgressState() {
		return progressState;
	}

	public void setProgressState(AssessProgressState progressState) {
		this.progressState = progressState;
	}

	public Date getExportTime() {
		return exportTime;
	}

	public void setExportTime(Date exportTime) {
		this.exportTime = exportTime;
	}

	public AssessmentType getAssessmentType() {
		return assessmentType;
	}

	public void setAssessmentType(AssessmentType assessmentType) {
		this.assessmentType = assessmentType;
	}

	public String getExternalApp() {
		return externalApp;
	}

	public void setExternalApp(String externalApp) {
		this.externalApp = externalApp;
	}

	public boolean isSyncSucceeded() {
		return syncSucceeded;
	}

	public void setSyncSucceeded(boolean syncSucceeded) {
		this.syncSucceeded = syncSucceeded;
	}

	public String getResultsJson() {
		return resultsJson;
	}

	public void setResultsJson(String resultsJson) {
		this.resultsJson = resultsJson;
	}
}
