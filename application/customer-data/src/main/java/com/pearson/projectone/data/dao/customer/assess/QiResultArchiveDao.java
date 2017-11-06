package com.pearson.projectone.data.dao.customer.assess;

import com.pearson.projectone.data.entity.customer.assess.QiResultArchive;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface QiResultArchiveDao extends CrudRepository<QiResultArchive, String> {
	List<QiResultArchive> findByQiAssessmentId(String qiAssessmentId);
}
