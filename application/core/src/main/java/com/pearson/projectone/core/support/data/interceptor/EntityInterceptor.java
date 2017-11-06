package com.pearson.projectone.core.support.data.interceptor;

import com.pearson.projectone.core.support.data.AbstractEntity;

/**
 * A unified interceptor interface for listening to entity life cycle agnostic of the underlying persistence layer.
 * I.e, it can be JPA, dynamo or any other persistence layer. This interceptor hooks on to the lifecycle events provided
 * by the framework. For JPA/Hibernate this could be by a combination of Jpa listeners and Hibernate interceptors,
 * dynamo db can use spring application context events and so forth.
 * <p>
 * Interceptor implementations should only listen to lifecycle changes of entities that are instances of
 *
 * @see com.pearson.projectone.core.support.data.AbstractEntity
 */
public interface EntityInterceptor {

	void beforeInsert(AbstractEntity abstractEntity);

	void afterInsert(AbstractEntity abstractEntity);

	void beforeUpdate(AbstractEntity abstractEntity);

	void afterUpdate(AbstractEntity abstractEntity);

	void beforeDelete(AbstractEntity abstractEntity);

	void afterDelete(AbstractEntity abstractEntity);

	void afterLoad(AbstractEntity abstractEntity);

	void beforeFlushForUpdate(AbstractEntity abstractEntity);

}
