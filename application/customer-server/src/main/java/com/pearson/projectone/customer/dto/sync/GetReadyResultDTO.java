package com.pearson.projectone.customer.dto.sync;


import java.util.List;

public class GetReadyResultDTO {

	private String message;

	private List<SyncAssessmentDTO> assessment;

	private List<String> deletedList;

	public List<SyncAssessmentDTO> getAssessment() {
		return assessment;
	}

	public void setAssessment(List<SyncAssessmentDTO> assessment) {
		this.assessment = assessment;
	}

	public List<String> getDeletedList() {
		return deletedList;
	}

	public void setDeletedList(List<String> deletedList) {
		this.deletedList = deletedList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
