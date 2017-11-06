package com.pearson.projectone.data.dao.global.library.assessment;


import com.pearson.projectone.data.entity.global.library.account.BusinessUnit;
import com.pearson.projectone.data.entity.global.library.assessment.BusinessUnitAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessUnitAssessmentDao extends JpaRepository<BusinessUnitAssessment, String> {
	List<BusinessUnitAssessment> findByBusinessUnit(BusinessUnit businessUnit);
}
