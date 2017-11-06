package com.pearson.projectone.data.dao.global.library.account;

import com.pearson.projectone.data.entity.global.library.account.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountDao extends JpaRepository<UserAccount, String> {
	UserAccount findByUserId(String userId);
}
