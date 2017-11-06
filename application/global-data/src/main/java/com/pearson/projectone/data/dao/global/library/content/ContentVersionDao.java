package com.pearson.projectone.data.dao.global.library.content;


import com.pearson.projectone.data.entity.global.library.content.ContentVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentVersionDao extends JpaRepository<ContentVersion, String> {
}
