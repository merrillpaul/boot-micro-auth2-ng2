package com.pearson.projectone.data.entity.customer;

import com.pearson.projectone.core.support.data.DocumentEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ExamineeAssessmentReportHistory")
public class ExamineeAssessmentReportHistory extends DocumentEntity {
	private String examineeAssessmentId;

	private String buReportId;

	private String reportProductCode;

	private String charged;

	private String childExamineeAssessmentId;

	public String getExamineeAssessmentId() {
		return examineeAssessmentId;
	}

	public void setExamineeAssessmentId(String examineeAssessmentId) {
		this.examineeAssessmentId = examineeAssessmentId;
	}

	public String getBuReportId() {
		return buReportId;
	}

	public void setBuReportId(String buReportId) {
		this.buReportId = buReportId;
	}

	public String getReportProductCode() {
		return reportProductCode;
	}

	public void setReportProductCode(String reportProductCode) {
		this.reportProductCode = reportProductCode;
	}

	public String getCharged() {
		return charged;
	}

	public void setCharged(String charged) {
		this.charged = charged;
	}

	public String getChildExamineeAssessmentId() {
		return childExamineeAssessmentId;
	}

	public void setChildExamineeAssessmentId(String childExamineeAssessmentId) {
		this.childExamineeAssessmentId = childExamineeAssessmentId;
	}
}
