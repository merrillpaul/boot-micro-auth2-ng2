package com.pearson.projectone.data.entity.global.library.content;

import com.pearson.projectone.core.support.data.RelationalEntity;
import com.pearson.projectone.data.entity.global.library.assessment.AssessTest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class ContentType extends RelationalEntity {

	@Column(unique = true)
	private String name;

	@Column
	private String displayName;

	@Column
	private String localizationKey;

	@ManyToOne(fetch = FetchType.LAZY)
	private AssessTest test;

	@Column
	private boolean usesManifest;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private Set<ContentVersion> versions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getLocalizationKey() {
		return localizationKey;
	}

	public void setLocalizationKey(String localizationKey) {
		this.localizationKey = localizationKey;
	}

	public AssessTest getTest() {
		return test;
	}

	public void setTest(AssessTest test) {
		this.test = test;
	}

	public boolean isUsesManifest() {
		return usesManifest;
	}

	public void setUsesManifest(boolean usesManifest) {
		this.usesManifest = usesManifest;
	}

	public Set<ContentVersion> getVersions() {
		return versions;
	}

	public void setVersions(Set<ContentVersion> versions) {
		this.versions = versions;
	}
}
