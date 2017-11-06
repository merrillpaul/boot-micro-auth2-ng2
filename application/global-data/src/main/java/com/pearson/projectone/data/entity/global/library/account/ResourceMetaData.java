package com.pearson.projectone.data.entity.global.library.account;


import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(
				name = "UK_RSMDATA_KEY_BU",
				columnNames = {"business_unit_id", "resourceKey"}
		)
})
public class ResourceMetaData extends RelationalEntity {

	@Column
	private String resourceType;

	@ManyToOne(fetch = FetchType.LAZY)
	private BusinessUnit businessUnit;

	@Column(nullable = false)
	private String resourceKey;

	@Column(nullable = false)
	private String resourceValue;

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public String getResourceValue() {
		return resourceValue;
	}

	public void setResourceValue(String resourceValue) {
		this.resourceValue = resourceValue;
	}
}
