package com.pearson.projectone.data.dao.security.oauth2.token;

import com.pearson.projectone.data.entity.security.oauth2.token.RefreshToken;
import org.springframework.data.repository.CrudRepository;


public interface RefreshTokenDao extends CrudRepository<RefreshToken, String> {
}
