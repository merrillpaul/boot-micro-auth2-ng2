package com.pearson.projectone.data.dao.customer;

import com.pearson.projectone.data.entity.customer.ExamineeReportOptions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ExamineeReportOptionsDao extends CrudRepository<ExamineeReportOptions, String> {
	List<ExamineeReportOptions> findByExamineeAssessmentScoreId(String examineeAssessmentScoreId);
}
