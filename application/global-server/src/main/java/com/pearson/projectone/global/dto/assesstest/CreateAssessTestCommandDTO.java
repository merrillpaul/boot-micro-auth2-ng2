package com.pearson.projectone.global.dto.assesstest;


import com.pearson.projectone.core.support.data.AbstractDTO;

public class CreateAssessTestCommandDTO extends AbstractDTO {
	private String description;
	private String displayName;
	private String name;
	private String normType;
	private String guid;
	private boolean observationsEnabled;
	private boolean useQgComparisonApi;
	private String domainId;

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
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
}
