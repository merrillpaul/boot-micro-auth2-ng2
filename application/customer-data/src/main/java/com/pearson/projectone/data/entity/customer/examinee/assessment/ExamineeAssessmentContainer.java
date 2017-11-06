package com.pearson.projectone.data.entity.customer.examinee.assessment;

import com.pearson.projectone.core.support.data.DocumentEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "ExamineeAssessmentContainer")
public class ExamineeAssessmentContainer extends DocumentEntity {

	public ExamineeAssessmentContainer() {
		//default constructor
	}

	public ExamineeAssessmentContainer(String examineeId, Map<String, List<String>> examineeAssessmentsMap) {
		this.examineeId = examineeId;
		this.examineeAssessmentsMap = examineeAssessmentsMap;
	}

	private String examineeId;
	private Map<String, List<String>> examineeAssessmentsMap;

	public String getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}

	public Map<String, List<String>> getExamineeAssessmentsMap() {
		return examineeAssessmentsMap;
	}

	public void setExamineeAssessmentsMap(Map<String, List<String>> examineeAssessmentsMap) {
		this.examineeAssessmentsMap = examineeAssessmentsMap;
	}
}
