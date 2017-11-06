package com.pearson.projectone.core.support.data.search.page;


import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class OrderDTO implements Serializable {

	public OrderDTO() {

	}

	public OrderDTO(String property) {
		this.property = property;
	}

	public OrderDTO(String property, Sort.Direction direction) {
		this.property = property;
		this.direction = direction;
	}

	private String property;

	private Sort.Direction direction;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Sort.Direction getDirection() {
		return direction;
	}

	public void setDirection(Sort.Direction direction) {
		this.direction = direction;
	}
}
