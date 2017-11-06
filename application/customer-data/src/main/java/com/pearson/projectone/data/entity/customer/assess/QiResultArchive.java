package com.pearson.projectone.data.entity.customer.assess;

import com.pearson.projectone.core.support.data.DocumentEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "QiResultArchive")
public class QiResultArchive extends DocumentEntity {
	private String qiAssessmentId;

	/* its just audit and we never need to query into the json, hence laid out as plain string */
	private String resultJson;

	public String getQiAssessmentId() {
		return qiAssessmentId;
	}

	public void setQiAssessmentId(String qiAssessmentId) {
		this.qiAssessmentId = qiAssessmentId;
	}

	public String getResultJson() {
		return resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}
}
