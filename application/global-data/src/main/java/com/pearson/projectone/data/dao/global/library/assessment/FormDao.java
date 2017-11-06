package com.pearson.projectone.data.dao.global.library.assessment;

import com.pearson.projectone.data.entity.global.library.assessment.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by uphilji on 4/18/17.
 */
public interface FormDao extends JpaRepository<Form, String>, JpaSpecificationExecutor<Form> {
		public List<Form> findByAcronym(final String acronym);

}
