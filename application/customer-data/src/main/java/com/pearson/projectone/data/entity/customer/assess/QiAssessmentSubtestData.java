package com.pearson.projectone.data.entity.customer.assess;

import com.pearson.projectone.core.support.data.DocumentEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "QiAssessmentSubtestData")
public class QiAssessmentSubtestData extends DocumentEntity {

	private String qiAssessmentSubtestId;

	private String key;

	private String value;

	public String getQiAssessmentSubtestId() {
		return qiAssessmentSubtestId;
	}

	public void setQiAssessmentSubtestId(String qiAssessmentSubtestId) {
		this.qiAssessmentSubtestId = qiAssessmentSubtestId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
