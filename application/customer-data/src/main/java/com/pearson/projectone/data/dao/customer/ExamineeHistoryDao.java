package com.pearson.projectone.data.dao.customer;

import com.pearson.projectone.core.support.data.DatabaseJsonFieldRetriever;
import com.pearson.projectone.data.entity.customer.ExamineeHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ExamineeHistoryDao extends CrudRepository<ExamineeHistory, String>,
		DatabaseJsonFieldRetriever<ExamineeHistory> {
	List<ExamineeHistory> findByExamineeId(String examineeId);
}
