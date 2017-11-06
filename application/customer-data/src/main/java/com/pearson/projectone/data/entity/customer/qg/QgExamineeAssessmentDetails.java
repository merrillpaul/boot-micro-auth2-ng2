package com.pearson.projectone.data.entity.customer.qg;

import com.pearson.projectone.core.support.data.DocumentEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "QgExamineeAssessmentDetails")
public class QgExamineeAssessmentDetails extends DocumentEntity {
	private String examineeAssessmentId;

	private String diagnosisPending;

	private String batchJobId;

	private boolean researchOptIn;

	private Date rosaStartDate;

	private String responseJsonParam;

	public String getExamineeAssessmentId() {
		return examineeAssessmentId;
	}

	public void setExamineeAssessmentId(String examineeAssessmentId) {
		this.examineeAssessmentId = examineeAssessmentId;
	}

	public String getDiagnosisPending() {
		return diagnosisPending;
	}

	public void setDiagnosisPending(String diagnosisPending) {
		this.diagnosisPending = diagnosisPending;
	}

	public String getBatchJobId() {
		return batchJobId;
	}

	public void setBatchJobId(String batchJobId) {
		this.batchJobId = batchJobId;
	}

	public boolean isResearchOptIn() {
		return researchOptIn;
	}

	public void setResearchOptIn(boolean researchOptIn) {
		this.researchOptIn = researchOptIn;
	}

	public Date getRosaStartDate() {
		return rosaStartDate;
	}

	public void setRosaStartDate(Date rosaStartDate) {
		this.rosaStartDate = rosaStartDate;
	}

	public String getResponseJsonParam() {
		return responseJsonParam;
	}

	public void setResponseJsonParam(String responseJsonParam) {
		this.responseJsonParam = responseJsonParam;
	}
}
