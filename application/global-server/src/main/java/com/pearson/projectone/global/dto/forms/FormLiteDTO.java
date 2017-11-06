package com.pearson.projectone.global.dto.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pearson.projectone.core.support.data.BaseDTO;

@JsonIgnoreProperties({"version"})
public class FormLiteDTO extends BaseDTO {

	public FormLiteDTO() {
		// default Constructor
	}

	public FormLiteDTO(String id, String name) {
		super(id);
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
