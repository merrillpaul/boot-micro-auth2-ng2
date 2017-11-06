package com.pearson.projectone.data.dao.customer;

import com.pearson.projectone.data.entity.customer.ExamineeAssessmentScores;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ExamineeAssessmentScoresDao extends CrudRepository<ExamineeAssessmentScores, String> {
	List<ExamineeAssessmentScores> findByExamineeAssessmentId(String examineeAssessmentId);
}
