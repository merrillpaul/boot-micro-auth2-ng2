package com.pearson.projectone.core.support.data.search.jpa;


import com.pearson.projectone.core.support.data.AbstractEntity;
import com.pearson.projectone.core.support.data.search.SearchCriteriaDTO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JpaSearchSpecification<Entity extends AbstractEntity> implements Specification<Entity> {

	private SearchCriteriaDTO criteria;

	public JpaSearchSpecification(SearchCriteriaDTO criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		switch (criteria.getOperation()) {
			case EQUALITY:
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			case NEGATION:
				return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
			case GREATER_THAN:
				return builder.greaterThan(root.<String>get(
						criteria.getKey()), criteria.getValue().toString());
			case LESS_THAN:
				return builder.lessThan(root.<String>get(
						criteria.getKey()), criteria.getValue().toString());
			case LIKE:
				return builder.like(builder.upper(root.<String>get(criteria.getKey())),
						criteria.getValue().toString().toUpperCase());
			case STARTS_WITH:
				return builder.like(builder.upper(root.<String>get(criteria.getKey())),
						criteria.getValue().toString().toUpperCase() + "%");
			case ENDS_WITH:
				return builder.like(builder.upper(root.<String>get(criteria.getKey())),
						"%" + criteria.getValue().toString().toUpperCase());
			case CONTAINS:
				return builder.like(builder.upper(root.<String>get(criteria.getKey())),
						"%" + criteria.getValue().toString().toUpperCase() + "%");
			default:
				return null;
		}
	}
}
