package com.pearson.projectone.core.support.data.search;


import java.io.Serializable;

public class SearchCriteriaDTO implements Serializable {

	public SearchCriteriaDTO() {

	}

	public SearchCriteriaDTO(String key, String operation, Object value) {
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	private String key;

	private String operation;

	private Object value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public SearchOperation getOperation() {
		return SearchOperation.getSimpleOperation(this.operation);
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
