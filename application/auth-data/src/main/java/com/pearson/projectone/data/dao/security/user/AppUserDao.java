package com.pearson.projectone.data.dao.security.user;


import com.pearson.projectone.data.entity.security.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppUserDao extends JpaRepository<AppUser, String>, JpaSpecificationExecutor<AppUser> {
	AppUser findByUsername(String username);
}
