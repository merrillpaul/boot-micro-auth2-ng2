package com.pearson.projectone.core.support.data;

import com.pearson.projectone.core.support.jpa.listeners.GenericJpaListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@EntityListeners({GenericJpaListener.class})
@MappedSuperclass
public class RelationalEntity extends AbstractEntity {

	public RelationalEntity() {
		//default constructor
	}


	/**
	 * for optimistic concurrency checks
	 */


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
