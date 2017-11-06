package com.pearson.projectone.core.support.jpa.listeners;

import com.pearson.projectone.core.support.data.RelationalEntity;
import com.pearson.projectone.core.support.data.interceptor.EntityInterceptor;
import com.pearson.projectone.core.support.data.interceptor.EntityInterceptorHolder;
import com.pearson.projectone.core.support.data.interceptor.trackchanges.TrackChanges;
import com.pearson.projectone.core.utils.AutoWireHelper;
import org.hibernate.EmptyInterceptor;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.util.Iterator;
import java.util.List;

/**
 * This is an amalgamation of JPA and hibernate interceptors for the sole purpose of providing a hook
 * before the 'actual' update of an entity. As per JPA spec, we cannot modify an entity property or its collection
 * relations in the JPA lifecycle methods. This kind of make the preUpdate JPA hook useless and can be realistically
 * used only for audit and not for adding/modifying properties. Hence we rely on raw hibernate interceptor's preFlush
 * callback and intelligently provide a hook for the above aspect. Of course, we cannot predict the timing of the
 * preFlush, but what we can predict is that this will happen before JPA's preUpdate callback and that at this phase
 * we can add properties freely. Also since, in a transaction, a preFlush can happen multiple times on a entity (
 * ie, every time the entity is saved after some change, we need to keep a stack of changes and deliver only the latest
 * changes ).
 * <p>
 * This listener only collects changes if the entity class is annotated with @see {@link TrackChanges}
 */
public class GenericJpaListener<T extends EntityInterceptor> extends EmptyInterceptor {

	@PrePersist
	public void onBeforeInsert(RelationalEntity relationalEntity) {
		for (T interceptor : getInterceptors()) {
			interceptor.beforeInsert(relationalEntity);
		}
	}

	@PostPersist
	public void onAfterInsert(RelationalEntity relationalEntity) {
		for (T interceptor : getInterceptors()) {
			interceptor.afterInsert(relationalEntity);
		}
	}

	@PreRemove
	public void onBeforeRemove(RelationalEntity relationalEntity) {
		for (T interceptor : getInterceptors()) {
			interceptor.beforeDelete(relationalEntity);
		}
	}

	@PostRemove
	public void onAfterRemove(RelationalEntity relationalEntity) {
		for (T interceptor : getInterceptors()) {
			interceptor.afterDelete(relationalEntity);
		}
	}

	@PreUpdate
	public void onBeforeUpdate(RelationalEntity relationalEntity) {
		for (T interceptor : getInterceptors()) {
			interceptor.beforeUpdate(relationalEntity);
		}
	}

	@PostUpdate
	public void onAfterUpdate(RelationalEntity relationalEntity) {
		for (T interceptor : getInterceptors()) {
			interceptor.afterUpdate(relationalEntity);
		}
	}

	@PostLoad
	public void onLoad(RelationalEntity relationalEntity) {
		for (T interceptor : getInterceptors()) {
			interceptor.afterLoad(relationalEntity);
		}
	}

	//called before commit into database
	@Override
	public void preFlush(Iterator iterator) {
		while (iterator.hasNext()) {
			Object entity = iterator.next();
			for (T interceptor : getInterceptors()) {
				if (entity instanceof RelationalEntity) {
					interceptor.beforeFlushForUpdate((RelationalEntity) entity);
				}
			}
		}
	}


	private List<T> getInterceptors() {
		EntityInterceptorHolder entityInterceptorHolder = AutoWireHelper.getBean(EntityInterceptorHolder.class);
		return entityInterceptorHolder.getInterceptors();
	}
}
