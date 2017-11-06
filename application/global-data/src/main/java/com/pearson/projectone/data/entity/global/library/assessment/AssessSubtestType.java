package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AssessSubtestType extends RelationalEntity {

	@Column(unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
