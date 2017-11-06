package com.pearson.projectone.data.entity.customer;

import com.pearson.projectone.core.support.data.DocumentEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ExamineeDetails")
public class ExamineeDetails extends DocumentEntity {

	private String examineeId;

	private boolean diagnosisPending;

	private boolean researchOptIn;

	public String getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}

	public boolean isDiagnosisPending() {
		return diagnosisPending;
	}

	public void setDiagnosisPending(boolean diagnosisPending) {
		this.diagnosisPending = diagnosisPending;
	}

	public boolean isResearchOptIn() {
		return researchOptIn;
	}

	public void setResearchOptIn(boolean researchOptIn) {
		this.researchOptIn = researchOptIn;
	}
}
