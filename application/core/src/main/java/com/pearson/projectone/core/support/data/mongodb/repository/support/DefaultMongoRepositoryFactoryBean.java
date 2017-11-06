package com.pearson.projectone.core.support.data.mongodb.repository.support;


import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

public class DefaultMongoRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>  extends
		MongoRepositoryFactoryBean<T, S, ID> {

	@Override
	protected RepositoryFactorySupport getFactoryInstance(MongoOperations operations) {
		return new MongoJsonRetrieverRepositoryFactory(operations);
	}
}
