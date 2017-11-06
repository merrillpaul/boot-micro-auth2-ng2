package com.pearson.projectone.data.dao.global.library;

import com.pearson.projectone.data.entity.global.library.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainDao extends JpaRepository<Domain, String> {

	List<Domain> findByCode(String code);

	List<Domain> findByType(String type);
}
