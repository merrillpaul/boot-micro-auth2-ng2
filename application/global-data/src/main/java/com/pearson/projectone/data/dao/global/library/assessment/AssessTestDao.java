package com.pearson.projectone.data.dao.global.library.assessment;


import com.pearson.projectone.data.entity.global.library.assessment.AssessTest;
import com.pearson.projectone.data.entity.global.library.assessment.AssessTestDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AssessTestDao extends JpaRepository<AssessTest, String>, JpaSpecificationExecutor<AssessTest> {
	List<AssessTest> findByDomain(AssessTestDomain domain);

	AssessTest findByGuid(String guid);

	List<AssessTest> findByNormType(String normType);
}
