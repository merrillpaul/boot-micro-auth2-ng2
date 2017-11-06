package com.pearson.projectone.data.dao.customer;

import com.pearson.projectone.data.entity.customer.ExamineeAssessmentReportHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ExamineeAssessmentReportHistoryDao extends CrudRepository<ExamineeAssessmentReportHistory, String> {
	List<ExamineeAssessmentReportHistory> findByExamineeAssessmentId(String examineeAssessmentId);

	List<ExamineeAssessmentReportHistory> findByChildExamineeAssessmentId(String childExamineeAssessmentId);

}
