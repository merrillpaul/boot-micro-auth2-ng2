package com.pearson.projectone.core.support.data.search.querydsl;


import com.pearson.projectone.core.support.data.AbstractEntity;
import com.pearson.projectone.core.support.data.search.SearchCriteriaDTO;
import com.pearson.projectone.core.support.data.search.SearchOperation;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryDslPredicateBuilder<T extends AbstractEntity> {
	private List<SearchCriteriaDTO> params = new ArrayList<>(0);

	/**
	 * Adds all search params from a list
	 *
	 * @param _params
	 * @return
	 */
	public QueryDslPredicateBuilder with(List<SearchCriteriaDTO> _params) {
		params.addAll(_params);
		return this;
	}

	/**
	 * Adds a single search criteria
	 *
	 * @param searchCriteria
	 * @return
	 */
	public QueryDslPredicateBuilder with(SearchCriteriaDTO searchCriteria) {
		params.add(searchCriteria);
		return this;
	}

	public QueryDslPredicateBuilder with(SearchCriteriaDTO... searchCriteria) {
		for (SearchCriteriaDTO searchCriterion: searchCriteria ) {
			params.add(searchCriterion);
		}
		return this;
	}

	/**
	 * Adds a params with individual items
	 *
	 * @param attributeName
	 * @param operation
	 * @param value
	 * @return
	 */
	public QueryDslPredicateBuilder with(String attributeName, String operation, Object value) {
		SearchOperation op = SearchOperation.getSimpleOperation(operation);
		if (op != null) {
			SearchCriteriaDTO criteria = new SearchCriteriaDTO();
			criteria.setKey(attributeName);
			criteria.setOperation(operation);
			criteria.setValue(value);
			params.add(criteria);
		}
		return this;
	}


	/**
	 * Composes a list of specifications based on the search criteria
	 *
	 * @return
	 */
	public BooleanExpression build(Class<? extends T> type) {
		if (params.size() == 0) {
			return null;
		}

		List<BooleanExpression> predicates = new ArrayList<BooleanExpression>();
		QueryDslPredicate predicate;
		for (SearchCriteriaDTO param : params) {
			predicate = new QueryDslPredicate(param, type);
			BooleanExpression exp = predicate.toPredicate();
			if (exp != null) {
				predicates.add(exp);
			}
		}

		BooleanExpression result = predicates.get(0);
		for (int i = 1; i < predicates.size(); i++) {
			result = result.and(predicates.get(i));
		}
		return result;
	}
}
