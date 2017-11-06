package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class AssessTest extends RelationalEntity {

	@Column(length = 4000)
	private String description;

	@Column
	private String displayName;

	@Column
	private String name;

	@Column
	private String normType;

	@Column
	private String guid;

	@Column
	private boolean observationsEnabled;

	@Column
	private boolean useQgComparisonApi;

	@ManyToOne
	private AssessTestDomain domain;

	@OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
	private Set<AssessSubtest> subtests;


	@OneToOne(mappedBy = "assessTest")
	private Form form;

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

	public AssessTestDomain getDomain() {
		return domain;
	}

	public void setDomain(AssessTestDomain domain) {
		this.domain = domain;
	}

	public Set<AssessSubtest> getSubtests() {
		return subtests;
	}

	public void setSubtests(Set<AssessSubtest> subtests) {
		this.subtests = subtests;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
}
