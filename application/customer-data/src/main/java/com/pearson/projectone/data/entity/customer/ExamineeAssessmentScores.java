package com.pearson.projectone.data.entity.customer;

import com.pearson.projectone.core.support.data.DocumentEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ExamineeAssessmentScores")
public class ExamineeAssessmentScores extends DocumentEntity {
	private String examineeAssessmentId;

	private String reportId;

	private String reportOptionsId;

	public String getExamineeAssessmentId() {
		return examineeAssessmentId;
	}

	public void setExamineeAssessmentId(String examineeAssessmentId) {
		this.examineeAssessmentId = examineeAssessmentId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportOptionsId() {
		return reportOptionsId;
	}

	public void setReportOptionsId(String reportOptionsId) {
		this.reportOptionsId = reportOptionsId;
	}
}
