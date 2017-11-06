package com.pearson.projectone.global.service;

import com.pearson.projectone.data.dao.global.library.assessment.AssessSubtestTypeDao;
import com.pearson.projectone.data.entity.global.library.assessment.AssessSubtestType;
import com.pearson.projectone.global.dto.subtesttype.SubtestTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubtestTypeService {
	@Autowired
	private AssessSubtestTypeDao subtestTypeDao;


	public SubtestTypeDTO getType(String id) {
		SubtestTypeDTO type = new SubtestTypeDTO();
		AssessSubtestType subtestType = subtestTypeDao.findOne(id);
		type.setId(id);
		type.setName(subtestType.getName());
		type.setVersion(subtestType.getVersion());
		return type;
	}

	public SubtestTypeDTO getTypeByName(String name) {
		// TODO impl
		return null;
	}

	public AssessSubtestType createType(SubtestTypeDTO createCommand) {
		AssessSubtestType type = new AssessSubtestType();
		type.setName(createCommand.getName());
		return subtestTypeDao.saveAndFlush(type);
	}

	public AssessSubtestType updateType(SubtestTypeDTO updateCommand) {
		AssessSubtestType type = subtestTypeDao.findOne(updateCommand.getId());
		type.setName(updateCommand.getName());
		return subtestTypeDao.saveAndFlush(type);
	}

	public void deleteType(String id) {
		subtestTypeDao.delete(id);
	}

	public List<SubtestTypeDTO> getAllTypes() {
		List<SubtestTypeDTO> types = new ArrayList<>(0);
		subtestTypeDao.findAll().forEach(type -> {
			SubtestTypeDTO sType = new SubtestTypeDTO();
			sType.setName(type.getName());
			sType.setId(type.getId());
			sType.setVersion(type.getVersion());
			types.add(sType);
		});
		return types;
	}
}
