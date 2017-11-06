package com.pearson.projectone.core.support.data;

import com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackableEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Abstract base class for all JPA entity/domain classes.
 * This provides common aspects such as created , updated user and timestamps etc
 */
@MappedSuperclass
public abstract class AbstractEntity extends TrackableEntity {

	public AbstractEntity() {
		//default constructor
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "com.pearson.projectone.core.support.data.hibernate.EnvironmentAwareUUIDHexGenerator")
	@Column(columnDefinition = "CHAR(32)")
	@org.springframework.data.annotation.Id
	private String id;

	/**
	 * The created by.
	 */
	@Column
	private String createdBy;

	/**
	 * The last created.
	 */
	@Column
	private Date lastCreated;

	/**
	 * The last updated.
	 */
	@Column
	private Date lastUpdated;

	/**
	 * The updated by.
	 */
	@Column
	private String updatedBy;

	@Column(columnDefinition = "BIT(1) DEFAULT '\\0'")
	private boolean deleted;

	@Column
	private String deletedBy;

	@Column
	private Date dateDeleted;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastCreated() {
		return lastCreated;
	}

	public void setLastCreated(Date lastCreated) {
		this.lastCreated = lastCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getDateDeleted() {
		return dateDeleted;
	}

	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}

	public abstract Long getVersion();

	public abstract void setVersion(Long version);
}
