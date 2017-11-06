package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AssessTestDomain extends RelationalEntity {
	@Column(unique = true)
	private String name;

	public AssessTestDomain() {}

	public AssessTestDomain(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
