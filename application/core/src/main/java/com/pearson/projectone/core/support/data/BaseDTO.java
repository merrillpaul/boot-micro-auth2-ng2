package com.pearson.projectone.core.support.data;


public class BaseDTO extends AbstractDTO {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BaseDTO(String id) {
		this.id = id;
	}

	public BaseDTO() {

	}

	public BaseDTO(String id, Long version) {
		super(version);
		this.id = id;
	}
}
