package com.pearson.projectone.core.support.data.interceptor.trackchanges;


import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

public abstract class TrackableEntity {

	@TrackerField
	@Transient
	@org.springframework.data.annotation.Transient
	private TrackerInfo trackerInfo = new TrackerInfo();

	protected boolean isDirty(String propertyName) {
		return trackerInfo.changedValues != null ? trackerInfo.changedValues.containsKey(propertyName) : false;
	}

	protected boolean hasChanged() {
		return trackerInfo.changedValues != null ? !trackerInfo.changedValues.isEmpty() : false;
	}

	protected Set<String> listDirtyPropertyNames() {
		return trackerInfo.changedValues != null ? trackerInfo.changedValues.keySet() : new HashSet<>(0);
	}

	protected Object getOriginalValue(String propertyName) {
		return trackerInfo.oldValues != null ? trackerInfo.oldValues.get(propertyName) : null;
	}

	// https://docs.jboss.org/hibernate/entitymanager/3.6/reference/en/html/listeners.html

	/**
	 * Call back that is called before update. The caveat, as per the specification, is that we cannot modify
	 * the changing entity properties or its relations. We do get information
	 * whether the entity has changed and which property has changed
	 */
	public void onBeforeUpdate() {
		// a callback place holder for implementations to act on before updation
	}

	/**
	 * Called before a new entity is persisted. The same caveat as the previous exisits
	 */
	public void onBeforeInsert() {
		// a callback place holder for implementations to act on before insertion
	}

	/**
	 * Called before the entity is deleted
	 */
	public void onBeforeDelete() {
		// a callback place holder for implementations to act on before deletion
	}


	/**
	 * Called when a flush is called before an entity is just to be updated to the DB.
	 * This is where we can check whether the entity is dirty ( along with any property ) and modify properties and/or
	 * collection relations
	 */
	public void onBeforeFlushForUpdate() {

	}


}
