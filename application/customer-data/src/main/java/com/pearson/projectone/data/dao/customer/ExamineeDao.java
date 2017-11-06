package com.pearson.projectone.data.dao.customer;


import com.pearson.projectone.core.support.data.DatabaseJsonFieldRetriever;
import com.pearson.projectone.data.entity.customer.examinee.Examinee;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ExamineeDao extends PagingAndSortingRepository<Examinee, String>, QueryDslPredicateExecutor<Examinee>,
		DatabaseJsonFieldRetriever<Examinee> {

	/**
	 * searches by examinee firstName or LastName or examineeId.
	 *
	 * @param firstName
	 * @param lastName
	 * @param examineeId
	 * @return
	 */
	public List<Examinee> findByFirstNameContainingOrLastNameContainingOrExamineeIdContainingAllIgnoreCase(String firstName, String lastName, String examineeId);
	//public List<Examinee> findByFirstNameOrLastNameAllIgnoreCase(String name);

}
