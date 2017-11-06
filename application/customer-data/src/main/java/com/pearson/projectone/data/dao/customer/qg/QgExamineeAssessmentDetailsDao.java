package com.pearson.projectone.data.dao.customer.qg;

import com.pearson.projectone.data.entity.customer.qg.QgExamineeAssessmentDetails;
import org.springframework.data.repository.CrudRepository;


public interface QgExamineeAssessmentDetailsDao extends CrudRepository<QgExamineeAssessmentDetails, String> {
	QgExamineeAssessmentDetails findByExamineeAssessmentId(String examineeAssessmentId);
}
