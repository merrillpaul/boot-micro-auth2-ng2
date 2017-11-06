package com.pearson.projectone.data.dao.global.library.assessment;

import com.pearson.projectone.data.entity.global.library.assessment.AssessSubtest;
import com.pearson.projectone.data.entity.global.library.assessment.AssessSubtestType;
import com.pearson.projectone.data.entity.global.library.assessment.AssessTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AssessSubtestDao extends JpaRepository<AssessSubtest, String>, JpaSpecificationExecutor<AssessSubtest> {
	AssessSubtest findByGuid(String guid);

	List<AssessSubtest> findBySubtestType(AssessSubtestType assessSubtestType);

	List<AssessSubtest> findByTest(AssessTest test);
}
