package com.pearson.projectone.core.support.data.mongodb.repository.support;


import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.querydsl.EntityPathResolver;

import java.io.Serializable;

public class SimpleJsonRetrieverMongoRepository<T, ID extends Serializable>  extends AbstractJsonRetrieverMongoRepository<T, ID> {
	public SimpleJsonRetrieverMongoRepository(MongoEntityInformation<T, ID> entityInformation, MongoOperations mongoOperations) {
		super(entityInformation, mongoOperations);
		this.delegate = new SimpleMongoRepository(entityInformation, mongoOperations);

	}

	public SimpleJsonRetrieverMongoRepository(MongoEntityInformation<T, ID> entityInformation,
	                                          MongoOperations mongoOperations, EntityPathResolver resolver) {
		super(entityInformation, mongoOperations, resolver);
		this.delegate = new SimpleMongoRepository(entityInformation, mongoOperations);
	}
}
