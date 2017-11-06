package com.pearson.projectone.core.support.data.interceptor.trackchanges;

import com.pearson.projectone.core.support.data.AbstractEntity;
import com.pearson.projectone.core.support.data.interceptor.DefaultEntityInterceptor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.Embedded;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This interceptor provides aspects for tracking changes and for calling entity lifecycle hooks.
 * This will be the last interceptor in the chain.
 */
@Component
@Order // for lowest precedence
public class TrackChangesEntityInterceptor extends DefaultEntityInterceptor {

	@Override
	public void afterLoad(AbstractEntity trackableEntity) {
		// track changes for only those entities that have this annotation
		if (AnnotationUtils.findAnnotation(trackableEntity.getClass(), TrackChanges.class) != null) {
			// now we find only those fields that have been marked to track changes
			// this is so that we dont load everything and its explicitly marked in the
			// domain/entity class
			Map<String, Object> oldValues = new HashMap<>(0);
			prepareOldValues(oldValues, "", trackableEntity);
			TrackerInfo trackerField = getTrackerField(trackableEntity);
			trackerField.setOldValues(oldValues);
		}
	}

	@Override
	public void beforeInsert(AbstractEntity abstractEntity) {
		abstractEntity.onBeforeInsert();
	}

	@Override
	public void beforeUpdate(AbstractEntity abstractEntity) {
		abstractEntity.onBeforeUpdate();
		if (abstractEntity instanceof TrackableEntity &&
				AnnotationUtils.findAnnotation(abstractEntity.getClass(), TrackChanges.class) != null) {
			TrackerInfo trackerField = getTrackerField(abstractEntity);
			if (trackerField != null) {
				trackerField.clearChanges();
			}
		}
	}

	@Override
	public void beforeFlushForUpdate(AbstractEntity abstractEntity) {
		markChanges(abstractEntity);
	}

	@Override
	public void beforeDelete(AbstractEntity abstractEntity) {
		abstractEntity.onBeforeDelete();
	}


	/**
	 * Populates a map with old values for those that have been tagged to be tracked
	 *
	 * @param oldValues
	 * @param prefix
	 * @param trackable
	 */
	private void prepareOldValues(Map<String, Object> oldValues, String prefix, Object trackable) {
		List<Field> trackableFields = getTrackableFields(trackable);

		for (Field field : trackableFields) {
			field.setAccessible(true);
			try {
				if (field.getAnnotation(Embedded.class) != null) {
					Object value = field.get(trackable);
					if (value != null) {
						// recurse into the Embedded field
						prepareOldValues(oldValues, prefix + field.getName() + ".", value);
					}
				} else {
					oldValues.put(prefix + field.getName(), field.get(trackable));
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}


	/**
	 * returns a list of trackable fields from a class based on the presence of TrackChanges
	 * annotation on the fields
	 *
	 * @param trackable
	 * @return list of Fields
	 */
	private List<Field> getTrackableFields(Object trackable) {
		Class clazz = trackable.getClass();
		Field[] fields = clazz.getDeclaredFields();
		List<Field> trackableFields = new ArrayList<>(0);
		for (Field field : fields) {
			if (field.getAnnotation(TrackChanges.class) != null) {
				trackableFields.add(field);
			}
		}
		return trackableFields;
	}

	/**
	 * Returns the tracker info from the TrackerField annotation
	 *
	 * @param trackable
	 * @return TrackerInfo object
	 */
	private TrackerInfo getTrackerField(TrackableEntity trackable) {
		Field[] fields = TrackableEntity.class.getDeclaredFields();
		TrackerInfo trackerInfo = null;
		for (Field field : fields) {
			if (field.getAnnotation(TrackerField.class) != null) {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				try {
					trackerInfo = (TrackerInfo) field.get(trackable);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				break;
			}
		}

		return trackerInfo;
	}

	private void markChanges(Object entity) {
		// track changes for only those entities that have this annotation
		if (entity instanceof TrackableEntity &&
				AnnotationUtils.findAnnotation(entity.getClass(), TrackChanges.class) != null) {
			TrackerInfo trackerField = getTrackerField((TrackableEntity) entity);
			if (trackerField != null && trackerField.hasStartedTracking()) {
				collectChanges(trackerField, "", entity);
			}
			TrackableEntity trackableEntity = (TrackableEntity) entity;
			if (trackableEntity.hasChanged()) {
				trackableEntity.onBeforeFlushForUpdate();
			}
		}
	}

	/**
	 * Collects the changes into tracker field
	 *
	 * @param trackerField
	 * @param prefix
	 * @param entity
	 */
	private boolean collectChanges(TrackerInfo trackerField, String prefix, Object entity) {
		boolean changed = false;
		List<Field> trackableFields = getTrackableFields(entity);
		for (Field field : trackableFields) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			String fieldName = field.getName();
			try {
				if (field.getAnnotation(Embedded.class) != null) {
					Object value = field.get(entity);
					if (value != null) {
						// recurse into the Embedded field
						changed |= collectChanges(trackerField, prefix + field.getName() + ".", value);
					}
				} else {
					Object newValue = field.get(entity);
					if (newValue != null) {
						Object oldValue = trackerField.getOldValues().get(prefix + fieldName);
						changed |= addChanges(trackerField, prefix + fieldName, oldValue, newValue);
					}
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return changed;
	}

	/**
	 * Adds changes into the tracker field
	 *
	 * @param trackerField
	 * @param fieldName
	 * @param oldValue
	 * @param newValue
	 * @return
	 */
	private boolean addChanges(TrackerInfo trackerField, String fieldName, Object oldValue, Object newValue) {
		if (ObjectUtils.isEmpty(oldValue) && ObjectUtils.isEmpty(newValue)) {
			return false;
		}

		if (ObjectUtils.nullSafeEquals(oldValue, newValue)) {
			// no change
			return false;
		}
		if (ObjectUtils.isEmpty(trackerField.getChangedValues())) {
			trackerField.setChangedValues(new HashMap<>(0));
		}
		trackerField.getChangedValues().put(fieldName, newValue);
		return true;
	}

}
