package com.pearson.projectone.data.dao.customer.assess;

import com.pearson.projectone.core.support.data.DatabaseJsonFieldRetriever;
import com.pearson.projectone.data.entity.customer.assess.QiAssessmentSubtest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface QiAssessmentSubtestDao extends CrudRepository<QiAssessmentSubtest, String>,
		DatabaseJsonFieldRetriever<QiAssessmentSubtest> {
	List<QiAssessmentSubtest> findByQiAssessmentId(String qiAssessmentId);
}
