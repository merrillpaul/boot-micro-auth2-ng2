package com.pearson.projectone.global.dto.businessunit;


public class BusinessUnitUpdateCommandDTO extends BusinessUnitCreateCommandDTO {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
