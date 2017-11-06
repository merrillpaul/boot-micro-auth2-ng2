package com.pearson.projectone.core.support.data.search.jpa;


import com.pearson.projectone.core.support.data.AbstractEntity;
import com.pearson.projectone.core.support.data.search.SearchCriteriaDTO;
import com.pearson.projectone.core.support.data.search.SearchOperation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

/**
 * Composes and builds a set of Specifications for search queries
 *
 * @param <Entity>
 */
public class JpaSearchSpecificationBuilder<Entity extends AbstractEntity> {

	private List<SearchCriteriaDTO> params = new ArrayList<>(0);

	/**
	 * Adds all search params from a list
	 *
	 * @param _params
	 * @return
	 */
	public JpaSearchSpecificationBuilder with(List<SearchCriteriaDTO> _params) {
		params.addAll(_params);
		return this;
	}

	/**
	 * Adds a single search criteria
	 *
	 * @param searchCriteria
	 * @return
	 */
	public JpaSearchSpecificationBuilder with(SearchCriteriaDTO searchCriteria) {
		params.add(searchCriteria);
		return this;
	}

	public JpaSearchSpecificationBuilder with(SearchCriteriaDTO... searchCriteria) {
		for(SearchCriteriaDTO criterion: searchCriteria) {
			params.add(criterion);
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
	public JpaSearchSpecificationBuilder with(String attributeName, String operation, Object value) {
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
	public Specification<Entity> build() {
		if (params.size() == 0) {
			return null;
		}
		List<Specification<Entity>> specs = new ArrayList<Specification<Entity>>();
		for (SearchCriteriaDTO param : params) {
			specs.add(new JpaSearchSpecification<Entity>(param));
		}

		Specification<Entity> result = specs.get(0);
		for (int i = 1; i < specs.size(); i++) {
			result = Specifications.where(result).and(specs.get(i));
		}
		return result;
	}

}
