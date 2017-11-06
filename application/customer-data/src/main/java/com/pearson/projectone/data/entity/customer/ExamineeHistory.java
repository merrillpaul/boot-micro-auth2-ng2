package com.pearson.projectone.data.entity.customer;

import com.pearson.projectone.core.support.data.DocumentEntity;
import com.pearson.projectone.core.support.data.mongodb.Json;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ExamineeHistory")
public class ExamineeHistory extends DocumentEntity {

	private String examineeId;

	@Json
	private String historyJson;

	public String getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}

	public String getHistoryJson() {
		return historyJson;
	}

	public void setHistoryJson(String historyJson) {
		this.historyJson = historyJson;
	}

}
