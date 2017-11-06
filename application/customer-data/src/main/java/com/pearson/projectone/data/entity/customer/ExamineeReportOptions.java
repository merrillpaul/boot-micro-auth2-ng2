package com.pearson.projectone.data.entity.customer;

import com.pearson.projectone.core.support.data.DocumentEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ExamineeReportOptions")
public class ExamineeReportOptions extends DocumentEntity {
	private String examineeAssessmentScoreId;

	private String examineeId;

	private String buReportId;

	private String reportOptions;

	public String getExamineeAssessmentScoreId() {
		return examineeAssessmentScoreId;
	}

	public void setExamineeAssessmentScoreId(String examineeAssessmentScoreId) {
		this.examineeAssessmentScoreId = examineeAssessmentScoreId;
	}

	public String getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}

	public String getBuReportId() {
		return buReportId;
	}

	public void setBuReportId(String buReportId) {
		this.buReportId = buReportId;
	}

	public String getReportOptions() {
		return reportOptions;
	}

	public void setReportOptions(String reportOptions) {
		this.reportOptions = reportOptions;
	}
}
