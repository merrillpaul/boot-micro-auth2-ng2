package com.pearson.projectone.core.support.data.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.pearson.projectone.core.support.data.AbstractEntity;
import com.pearson.projectone.core.support.data.interceptor.EntityInterceptor;
import com.pearson.projectone.core.support.data.interceptor.EntityInterceptorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Stack;

/**
 * This provides hook into higher entity classes for their persistence life cycle from mongo db
 */
@Component
public class MongoDBEntityListener<T extends EntityInterceptor, K extends Object> {

	@Autowired
	private EntityInterceptorHolder entityInterceptorHolder;

	@Autowired
	private MongoOperations mongoOperations;

	private class EntityStackMap {
		String key;
		AbstractEntity entity;
	}

	private Stack<EntityStackMap> saveStack = new Stack<>();
	private Stack<EntityStackMap> deleteStack = new Stack<>();

	/**
	 * mongotemplate uses BeforeConvertEvent before saving to
	 * serialize the json from the attributes. Once the serialization done
	 * it doesnt re-serialize the attributes from the entity, hence
	 * we cannot reuse the BeforeSaveEvent.
	 * refer MongoTemplate.doSave , also in
	 * https://github.com/spring-projects/spring-data-mongodb/blob/master/spring-data-mongodb/src/main/java/org/springframework/data/mongodb/core/MongoTemplate.java#L1039
	 *
	 * @param event
	 */
	@EventListener
	public void handleMongoBeforeConvert(BeforeConvertEvent<K> event) {
		if (!(event.getSource() instanceof AbstractEntity)) {
			return;
		}
		AbstractEntity abstractEntity = (AbstractEntity) event.getSource();
		// we need to check whether its an insert or update.
		// for this all we have is the id
		EntityStackMap entry = new EntityStackMap();
		entry.entity = abstractEntity;
		entry.key = abstractEntity.getId();
		saveStack.push(entry);

		if (ObjectUtils.isEmpty(abstractEntity.getId())) {
			for (T interceptor : getInterceptors()) {
				interceptor.beforeInsert(abstractEntity);
			}
		} else {
			for (T interceptor : getInterceptors()) {
				interceptor.beforeFlushForUpdate(abstractEntity);
			}
			for (T interceptor : getInterceptors()) {
				interceptor.beforeUpdate(abstractEntity);
			}
		}
	}

	/**
	 * After convert is called after loading a DBObject from MongoTemplate
	 *
	 * @param event
	 */
	@EventListener
	public void handleMongoAfterLoad(AfterConvertEvent<K> event) {
		if (!(event.getSource() instanceof AbstractEntity)) {
			return;
		}
		AbstractEntity abstractEntity = (AbstractEntity) event.getSource();
		getInterceptors().forEach(it -> {
			it.afterLoad(abstractEntity);
		});
	}

	/**
	 * Mongo operations do not provide the entity thats about to be deleted.
	 * Hence we need to get the 'tobeDeleted' entity so that our interceptors are unified to work
	 * regardless of the underlying repo.
	 *
	 * @param event
	 */
	@EventListener
	public void handleMongoBeforeDelete(BeforeDeleteEvent<K> event) {

		// Mongo uses the same Event for all deletions.
		// Here there is no way to get the ids deleted
		if (ObjectUtils.isEmpty(event.getType())) {
			return;
		}

		K entity =
				mongoOperations.findById(event.getDBObject().get("id"),
						event.getType(), event.getCollectionName());
		if (!(entity instanceof AbstractEntity)) {
			return;
		}
		AbstractEntity toBeDeletedEntity = (AbstractEntity) entity;
		EntityStackMap entry = new EntityStackMap();
		entry.entity = toBeDeletedEntity;
		entry.key = toBeDeletedEntity.getId();
		deleteStack.push(entry);

		getInterceptors().forEach(it -> {
			it.beforeDelete(toBeDeletedEntity);
		});
	}

	@EventListener
	public void handleMongoAfterDelete(AfterDeleteEvent<K> event) {
		if (deleteStack.isEmpty()) {
			return;
		}
		EntityStackMap entry = deleteStack.peek();
		if (entry != null && entry.key != null && entry.key.equals(event.getDBObject().get("id"))) {
			AbstractEntity deletedEntity = entry.entity;
			getInterceptors().forEach(it -> {
				it.afterDelete(deletedEntity);
			});
			deleteStack.pop();
		}
	}


	@EventListener
	public <K extends Object> void handleMongoAfterSave(AfterSaveEvent<K> event) {
		if (!(event.getSource() instanceof AbstractEntity)) {
			return;
		}
		AbstractEntity abstractEntity = (AbstractEntity) event.getSource();
		EntityStackMap entry = saveStack.peek();
		// if the entry had a key, we fire the afterUpdate , if not afterInsert
		if (entry.entity == abstractEntity) {
			if (entry.key != null) {
				getInterceptors().forEach(it -> {
					it.afterUpdate(abstractEntity);
				});
			} else {
				getInterceptors().forEach(it -> {
					it.afterInsert(abstractEntity);
				});
			}
			saveStack.pop();
		}
	}

	/**
	 * To process json attributes
	 *
	 * @param event
	 * @param <K>
	 */
	@EventListener
	public <K extends Object> void handleMongoBeforeSave(BeforeSaveEvent<K> event) {
		DBObject savingDBObject = event.getDBObject();
		K entity = event.getSource();
		processTransientJsons(savingDBObject, entity);
	}

	List<T> getInterceptors() {
		return entityInterceptorHolder.getInterceptors();
	}

	<K extends Object> void processTransientJsons(DBObject savingDBObject, K entity) {

		Field[] fields = entity.getClass().getDeclaredFields();
		Object fieldValue;
		String fieldName;
		for (Field field : fields) {
			fieldValue = null;
			if (field.getAnnotation(Json.class) != null) {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				try {
					fieldValue = field.get(entity);
					fieldName = field.getName();
					if (fieldValue instanceof String) {
						savingDBObject.put(fieldName, BasicDBObject.parse(fieldValue.toString()));
					}
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				break;
			}
		}


	}
}
