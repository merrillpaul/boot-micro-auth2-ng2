package com.pearson.projectone.core.support.data;

/**
 * A simple dto for commands with just 'name' attribute that can be reused
 */
public class NameCommandDTO extends AbstractDTO {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
