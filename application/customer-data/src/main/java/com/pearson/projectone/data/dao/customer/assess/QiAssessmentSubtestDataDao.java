package com.pearson.projectone.data.dao.customer.assess;

import com.pearson.projectone.data.entity.customer.assess.QiAssessmentSubtestData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QiAssessmentSubtestDataDao extends CrudRepository<QiAssessmentSubtestData, String> {
	List<QiAssessmentSubtestData> findByQiAssessmentSubtestId(String qiAssessmentSubtestId);
}
