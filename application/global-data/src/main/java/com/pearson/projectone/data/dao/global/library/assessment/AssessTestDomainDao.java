package com.pearson.projectone.data.dao.global.library.assessment;


import com.pearson.projectone.data.entity.global.library.assessment.AssessTestDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessTestDomainDao extends JpaRepository<AssessTestDomain, String> {
	int countByName(String name);

	AssessTestDomain findByName(String name);
}
