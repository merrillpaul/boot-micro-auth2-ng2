package com.pearson.projectone.core.support.data.interceptor;

import com.pearson.projectone.core.support.data.AbstractEntity;

/**
 * A no-op interceptor
 */
public class DefaultEntityInterceptor implements EntityInterceptor {
	@Override
	public void beforeInsert(AbstractEntity abstractEntity) {
		// noop
	}

	@Override
	public void afterInsert(AbstractEntity abstractEntity) {
		// noop
	}

	@Override
	public void beforeUpdate(AbstractEntity abstractEntity) {
		// noop
	}

	@Override
	public void afterUpdate(AbstractEntity abstractEntity) {
		// noop
	}

	@Override
	public void beforeDelete(AbstractEntity abstractEntity) {
		// noop
	}

	@Override
	public void afterDelete(AbstractEntity abstractEntity) {
		// noop
	}

	@Override
	public void afterLoad(AbstractEntity abstractEntity) {
		// noop
	}

	@Override
	public void beforeFlushForUpdate(AbstractEntity abstractEntity) {
		// noop
	}
}
