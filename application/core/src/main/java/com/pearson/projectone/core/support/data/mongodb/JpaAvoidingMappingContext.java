package com.pearson.projectone.core.support.data.mongodb;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import javax.persistence.Entity;
import java.util.Set;

public class JpaAvoidingMappingContext extends MongoMappingContext {

	@Override
	public void setInitialEntitySet(Set<? extends Class<?>> initialEntitySet) {
		if (initialEntitySet != null) {
			initialEntitySet.removeIf(clazz -> {
				return AnnotationUtils.findAnnotation(clazz, Entity.class) == null;
			});
		}
		super.setInitialEntitySet(initialEntitySet);
	}
}
