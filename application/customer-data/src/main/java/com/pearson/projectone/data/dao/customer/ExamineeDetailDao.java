package com.pearson.projectone.data.dao.customer;

import com.pearson.projectone.data.entity.customer.ExamineeDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ExamineeDetailDao extends CrudRepository<ExamineeDetails, String> {
	List<ExamineeDetails> findByExamineeId(String examineeId);
}
