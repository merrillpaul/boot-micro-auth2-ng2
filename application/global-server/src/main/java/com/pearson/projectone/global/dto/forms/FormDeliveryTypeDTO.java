package com.pearson.projectone.global.dto.forms;

/**
 * Created by ukakapr on 6/13/17.
 */
public class FormDeliveryTypeDTO {
	public FormDeliveryTypeDTO() {
		//default constructor
	}

	public FormDeliveryTypeDTO(String id, String value) {
		this.id = id;
		this.value = value;
	}

	private String id;
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
