package com.pearson.projectone.data.dao.global.library.assessment;


import com.pearson.projectone.data.entity.global.library.assessment.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssessmentDao extends JpaRepository<Assessment, String>, JpaSpecificationExecutor<Assessment> {
}
