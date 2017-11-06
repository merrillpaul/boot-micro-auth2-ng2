package com.pearson.projectone.core.support.data.mongodb.repository.support;

import com.pearson.projectone.core.support.data.DatabaseJsonFieldRetriever;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.QueryDslMongoRepository;
import org.springframework.data.repository.core.RepositoryMetadata;


public class MongoJsonRetrieverRepositoryFactory extends MongoRepositoryFactory {
	/**
	 * Creates a new {@link MongoRepositoryFactory} with the given {@link MongoOperations}.
	 *
	 * @param mongoOperations must not be {@literal null}.
	 */
	public MongoJsonRetrieverRepositoryFactory(MongoOperations mongoOperations) {
		super(mongoOperations);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getRepositoryBaseClass(org.springframework.data.repository.core.RepositoryMetadata)
	 */
	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		boolean isDocumentRetriever = DatabaseJsonFieldRetriever.class
				.isAssignableFrom(metadata.getRepositoryInterface());
		Class clazz =  super.getRepositoryBaseClass(metadata);
		if (clazz == QueryDslMongoRepository.class) {
			return isDocumentRetriever ? QueryDslJsonRetrieverMongoRepository.class: clazz;
		} else {
			return isDocumentRetriever ? SimpleJsonRetrieverMongoRepository.class : clazz;
		}
	}
}
