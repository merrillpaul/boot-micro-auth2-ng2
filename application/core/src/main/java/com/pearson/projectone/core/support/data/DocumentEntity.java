package com.pearson.projectone.core.support.data;

import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

public class DocumentEntity extends AbstractEntity {

	@Version
	private Long version;

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}
}
