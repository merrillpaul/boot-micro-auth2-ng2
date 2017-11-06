package com.pearson.projectone.data.dao.global.library.account;


import com.pearson.projectone.data.entity.global.library.account.ResourceMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceMetaDataDao extends JpaRepository<ResourceMetaData, String> {
}
