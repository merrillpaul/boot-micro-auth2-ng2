package com.pearson.projectone.core.support.modelmapper;

import com.pearson.projectone.core.support.data.AbstractDTO;
import com.pearson.projectone.core.support.data.AbstractEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService {

	@Autowired
	private ModelMapper modelMapper;

	public <DTO extends AbstractDTO, Entity extends AbstractEntity> DTO convertToDTO(Entity entity, Class<DTO> dtoClass) {
		return modelMapper.map(entity, dtoClass);
	}

	public <DTO extends AbstractDTO, Entity extends AbstractEntity> Entity convertToEntity(DTO dto, Class<Entity> entityClass) {
		return modelMapper.map(dto, entityClass);
	}
}
