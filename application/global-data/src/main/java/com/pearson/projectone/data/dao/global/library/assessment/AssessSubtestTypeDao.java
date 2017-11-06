package com.pearson.projectone.data.dao.global.library.assessment;


import com.pearson.projectone.data.entity.global.library.assessment.AssessSubtestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessSubtestTypeDao extends JpaRepository<AssessSubtestType, String> {
	int countByName(String name);

	AssessSubtestType findByName(String name);
}
