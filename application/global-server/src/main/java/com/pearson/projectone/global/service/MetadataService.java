package com.pearson.projectone.global.service;

import com.pearson.projectone.data.dao.global.library.account.ResourceMetaDataDao;
import com.pearson.projectone.data.entity.global.library.account.ResourceMetaData;
import com.pearson.projectone.global.dto.metadata.MetadataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetadataService {
	@Autowired
	private ResourceMetaDataDao resourceMetaDataDao;

	public MetadataDTO getForKey(String businessUnitId, String key) {
		// TODO impl
		return null;
	}

	public List<MetadataDTO> getForType(String businessUnitId, String type) {
		// TODO impl
		return null;
	}

	public List<MetadataDTO> getForBusinessUnit(String businessUnitId) {
		// TODO impl
		return null;
	}

	public ResourceMetaData createMetadata(MetadataDTO createCommand, String businessUnitId) {
		// TODO impl
		return null;
	}

	public ResourceMetaData updateMetadata(MetadataDTO updateCommand, String businessUnitId) {
		// TODO impl
		return null;
	}

	public void deleteMetadata(String id) {
		// TODO impl
	}
}
