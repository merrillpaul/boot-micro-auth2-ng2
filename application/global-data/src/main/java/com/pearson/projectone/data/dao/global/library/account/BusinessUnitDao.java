package com.pearson.projectone.data.dao.global.library.account;

import com.pearson.projectone.data.entity.global.library.account.BusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BusinessUnitDao extends JpaRepository<BusinessUnit, String>, JpaSpecificationExecutor<BusinessUnit> {
	BusinessUnit findByGuid(String guid);

	BusinessUnit findByName(String name);
}
