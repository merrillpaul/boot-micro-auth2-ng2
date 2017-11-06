package com.pearson.projectone.data.dao.security.user;

import com.pearson.projectone.data.entity.security.user.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleDao extends JpaRepository<AppRole, String> {
	AppRole findByAuthority(String authority);
}
