package com.pearson.projectone.data.dao.customer.qg;

import com.pearson.projectone.data.entity.customer.examiner.Examiner;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExaminerDao extends PagingAndSortingRepository<Examiner, String>,
		QueryDslPredicateExecutor<Examiner> {
}
