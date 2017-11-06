package com.pearson.projectone.global.dto.assesstest;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.global.dto.testdomain.TestDomainDTO;

import java.util.List;

public class AssessTestResultDTO extends BaseDTO {

	private TestDomainDTO domain;
	private List<BaseAssessSubtestDTO> subtests;
	private String description;
	private String displayName;
	private String name;
	private String normType;
	private String guid;
	private boolean observationsEnabled;
	private boolean useQgComparisonApi;
	private String assessmentId;

	public TestDomainDTO getDomain() {
		return domain;
	}

	public void setDomain(TestDomainDTO domain) {
		this.domain = domain;
	}

	public List<BaseAssessSubtestDTO> getSubtests() {
		return subtests;
	}

	public void setSubtests(List<BaseAssessSubtestDTO> subtests) {
		this.subtests = subtests;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNormType() {
		return normType;
	}

	public void setNormType(String normType) {
		this.normType = normType;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public boolean isObservationsEnabled() {
		return observationsEnabled;
	}

	public void setObservationsEnabled(boolean observationsEnabled) {
		this.observationsEnabled = observationsEnabled;
	}

	public boolean isUseQgComparisonApi() {
		return useQgComparisonApi;
	}

	public void setUseQgComparisonApi(boolean useQgComparisonApi) {
		this.useQgComparisonApi = useQgComparisonApi;
	}

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
}
