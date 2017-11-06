package com.pearson.projectone.core.support.data.interceptor.trackchanges;


import java.util.HashMap;
import java.util.Map;

public class TrackerInfo {
	protected Map<String, Object> oldValues = new HashMap<>(0);
	protected Map<String, Object> changedValues = new HashMap<>(0);

	protected Map<String, Object> getOldValues() {
		return oldValues;
	}

	protected void setOldValues(Map<String, Object> oldValues) {
		this.oldValues = oldValues;
	}

	protected Map<String, Object> getChangedValues() {
		return changedValues;
	}

	protected void setChangedValues(Map<String, Object> changedValues) {
		this.changedValues = changedValues;
	}

	protected boolean hasStartedTracking() {
		return oldValues != null && !oldValues.isEmpty();
	}

	protected void clearChanges() {
		this.oldValues.clear();
		this.changedValues.clear();
	}

}
