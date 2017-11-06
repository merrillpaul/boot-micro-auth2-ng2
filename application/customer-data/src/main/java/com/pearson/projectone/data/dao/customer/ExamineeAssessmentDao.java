package com.pearson.projectone.data.dao.customer;

import com.pearson.projectone.data.entity.customer.examinee.assessment.ExamineeAssessment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ExamineeAssessmentDao extends CrudRepository<ExamineeAssessment, String> {
	List<ExamineeAssessment> findByExamineeId(String examineeId);
	// not extra methods yet.

}
