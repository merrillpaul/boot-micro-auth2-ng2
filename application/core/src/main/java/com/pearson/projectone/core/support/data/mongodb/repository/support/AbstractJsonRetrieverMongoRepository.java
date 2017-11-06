package com.pearson.projectone.core.support.data.mongodb.repository.support;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.pearson.projectone.core.support.data.DatabaseJsonFieldRetriever;
import com.pearson.projectone.core.support.data.DocumentEntity;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractJsonRetrieverMongoRepository<T, ID extends Serializable> implements MongoRepository<T, ID>,
		DatabaseJsonFieldRetriever<T> {

	private final PathBuilder<T> builder;
	private final MongoEntityInformation<T, ID> entityInformation;
	private final MongoOperations mongoOperations;
	protected MongoRepository delegate;



	public AbstractJsonRetrieverMongoRepository(MongoEntityInformation<T, ID> entityInformation, MongoOperations mongoOperations) {
		this(entityInformation, mongoOperations, SimpleEntityPathResolver.INSTANCE);
	}


	public AbstractJsonRetrieverMongoRepository(MongoEntityInformation<T, ID> entityInformation, MongoOperations mongoOperations,
	                                            EntityPathResolver resolver) {

		Assert.notNull(resolver);
		EntityPath<T> path = resolver.createPath(entityInformation.getJavaType());

		this.builder = new PathBuilder<T>(path.getType(), path.getMetadata());
		this.entityInformation = entityInformation;
		this.mongoOperations = mongoOperations;
	}

	@Override
	public <S extends DocumentEntity, O extends Serializable> Map<String, O> retrieveDocument(S entity, String projection) {
		return getByProjection(entity.getId(), projection);
	}

	@Override
	public <O extends Serializable> Map<String, O> retrieveDocument(String id, String projection) {
		return getByProjection(id, projection);
	}


	private <O extends Serializable> Map<String, O> getByProjection(String id, String projection) {
		DBObject idMatch = getIdMatch(id);
		BasicDBObject projections = new BasicDBObject();
		projections.put("_id", 0);
		projections.put(projection, 1);
		return mongoOperations.getCollection(entityInformation.getCollectionName())
				.findOne(idMatch, projections).toMap();
	}

	@Override
	public <S extends DocumentEntity, O extends Serializable> List<Map<String, O>> retrieveDocumentByCommands(S entity, String... jsonCommands) {
		return getByCommands(entity.getId(), jsonCommands);
	}

	@Override
	public <O extends Serializable> List<Map<String, O>> retrieveDocumentByCommands(String id, String... jsonCommands) {
		return getByCommands(id, jsonCommands);
	}

	public List save(Iterable entites) {
		return delegate.save(entites);
	}

	@Override
	public List findAll() {
		return delegate.findAll();
	}

	@Override
	public List findAll(Sort sort) {
		return delegate.findAll(sort);
	}

	public Object insert(Object entity) {
		return delegate.insert(entity);
	}

	public List insert(Iterable entities) {
		return delegate.insert(entities);
	}

	public List findAll(Example example) {
		return delegate.findAll(example);
	}

	public List findAll(Example example, Sort sort) {
		return delegate.findAll(example, sort);
	}

	@Override
	public Page findAll(Pageable pageable) {
		return delegate.findAll(pageable);
	}

	public Object save(Object entity) {
		return delegate.save(entity);
	}

	public Object findOne(Serializable serializable) {
		return delegate.findOne(serializable);
	}

	public boolean exists(Serializable serializable) {
		return delegate.exists(serializable);
	}

	public Iterable findAll(Iterable iterable) {
		return delegate.findAll(iterable);
	}

	@Override
	public long count() {
		return delegate.count();
	}

	public void delete(Serializable serializable) {
		delegate.delete(serializable);
	}

	public void delete(Object entity) {
		delegate.delete(entity);
	}

	public void delete(Iterable entities) {
		delegate.delete(entities);
	}

	@Override
	public void deleteAll() {
		delegate.deleteAll();
	}

	public Object findOne(Example example) {
		return delegate.findOne(example);
	}

	public Page findAll(Example example, Pageable pageable) {
		return delegate.findAll(example, pageable);
	}

	public long count(Example example) {
		return delegate.count(example);
	}

	public boolean exists(Example example) {
		return delegate.exists(example);
	}

	private <O extends Serializable> List<Map<String, O>> getByCommands(String id, String... jsonCommmands) {
		List<BasicDBObject> aggregatePipeLine = new ArrayList<>(0);
		List<Map<String, O>> results = new ArrayList<>(0);
		aggregatePipeLine.add(new BasicDBObject("$match", getIdMatch(id)));

		for (String command : jsonCommmands) {
			aggregatePipeLine.add(BasicDBObject.parse(command));
		}
		mongoOperations.getCollection(entityInformation.getCollectionName())
				.aggregate(aggregatePipeLine).results().forEach(it -> {
			results.add(it.toMap());
		});
		return results;
	}

	private DBObject getIdMatch(String id) {
		String collectioName = entityInformation.getCollectionName();
		String idProperty = entityInformation.getIdAttribute();
		return new BasicDBObject("_id", new ObjectId(id));
	}
}
