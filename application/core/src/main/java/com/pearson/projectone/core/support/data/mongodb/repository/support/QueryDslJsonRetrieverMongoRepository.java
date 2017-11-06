package com.pearson.projectone.core.support.data.mongodb.repository.support;


import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.QueryDslMongoRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
public class QueryDslJsonRetrieverMongoRepository<T, ID extends Serializable> extends AbstractJsonRetrieverMongoRepository<T, ID>
		implements QueryDslPredicateExecutor<T> {
	private QueryDslMongoRepository repository;

	public QueryDslJsonRetrieverMongoRepository(MongoEntityInformation<T, ID> entityInformation, MongoOperations mongoOperations) {
		super(entityInformation, mongoOperations);
		this.repository = new QueryDslMongoRepository(entityInformation, mongoOperations);
		this.delegate = this.repository;
	}

	public QueryDslJsonRetrieverMongoRepository(MongoEntityInformation<T, ID> entityInformation, MongoOperations mongoOperations, EntityPathResolver resolver) {
		super(entityInformation, mongoOperations, resolver);
		this.repository = new QueryDslMongoRepository(entityInformation, mongoOperations, resolver);
		this.delegate = this.repository;
	}

	@Override
	public T findOne(Predicate predicate) {
		return (T) repository.findOne(predicate);
	}

	@Override
	public List findAll(Predicate predicate) {
		return repository.findAll(predicate);
	}

	public List findAll(Predicate predicate, OrderSpecifier[] orders) {
		return repository.findAll(predicate, orders);
	}

	@Override
	public List findAll(Predicate predicate, Sort sort) {
		return repository.findAll(predicate, sort);
	}

	public Iterable findAll(OrderSpecifier[] orders) {
		return repository.findAll(orders);
	}

	@Override
	public Page findAll(Predicate predicate, Pageable pageable) {
		return repository.findAll(predicate, pageable);
	}

	@Override
	public Page findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public long count(Predicate predicate) {
		return repository.count(predicate);
	}

	@Override
	public boolean exists(Predicate predicate) {
		return repository.exists(predicate);
	}
}
