package com.pearson.projectone.data.dao.global.library.account;


import com.pearson.projectone.data.entity.global.library.account.Account;
import com.pearson.projectone.data.entity.global.library.account.BusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountDao extends JpaRepository<Account, String> {
	List<Account> findByBusinessUnit(BusinessUnit businessUnit);
}
