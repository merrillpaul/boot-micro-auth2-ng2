package com.pearson.projectone.data.dao.customer.assess;

import com.pearson.projectone.core.support.data.DatabaseJsonFieldRetriever;
import com.pearson.projectone.data.entity.customer.assess.QiAssessmentDetails;
import org.springframework.data.repository.CrudRepository;


public interface QiAssessmentDao extends CrudRepository<QiAssessmentDetails, String>,
		DatabaseJsonFieldRetriever<QiAssessmentDetails> {
	QiAssessmentDetails findByExamineeAssessmentId(String examineeAssessmentId);
}