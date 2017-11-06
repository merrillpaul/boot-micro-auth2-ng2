package com.pearson.projectone.data.dao.customer.qg;

import com.pearson.projectone.data.entity.customer.examinee.assessment.ExamineeAssessmentContainer;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamineeAssessmentContainerDao
		extends PagingAndSortingRepository<ExamineeAssessmentContainer, String>,
		QueryDslPredicateExecutor<ExamineeAssessmentContainer> {
	//No custom methods yet.
}


