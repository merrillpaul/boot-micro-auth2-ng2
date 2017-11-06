package com.pearson.projectone.core.support.data;

import java.io.Serializable;

public abstract class AbstractDTO implements Serializable {
	private Long version;

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public AbstractDTO() {

	}

	public AbstractDTO(Long version) {
		this.version = version;
	}

}
