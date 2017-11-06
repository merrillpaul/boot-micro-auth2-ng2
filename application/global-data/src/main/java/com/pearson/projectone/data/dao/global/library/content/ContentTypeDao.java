package com.pearson.projectone.data.dao.global.library.content;


import com.pearson.projectone.data.entity.global.library.assessment.AssessTest;
import com.pearson.projectone.data.entity.global.library.content.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentTypeDao extends JpaRepository<ContentType, String> {
	List<ContentType> findByTest(AssessTest test);
}
