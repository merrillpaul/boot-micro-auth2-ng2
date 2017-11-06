package com.pearson.projectone.core.support.data.search;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchRequestDTO implements Serializable {

	private List<SearchCriteriaDTO> criteria = new ArrayList<>(0);

	public List<SearchCriteriaDTO> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<SearchCriteriaDTO> criteria) {
		this.criteria = criteria;
	}

}
