package com.pearson.projectone.global.mapper;

import com.pearson.projectone.data.entity.global.library.assessment.Assessment;
import com.pearson.projectone.global.dto.assessment.AssessmentDTO;
import com.pearson.projectone.core.utils.DateUtil;

/**
 * Created by uphilji on 4/14/17.
 * <p>
 * This class maps an AsessmentDTO to the Assessment entity  and vice versa.
 * These mapper classes are meant to be static and not instantiated.
 */
public class AssessmentMapper {

	/**
	 * Map the dto to its entity object
	 *
	 * @param dto
	 * @return
	 */
	public static Assessment mapDTOtoEntity(AssessmentDTO dto) {
		Assessment entity = null;
		if (dto != null) {
			entity = new Assessment();
			entity.setAcronym(dto.getAcronym());
			entity.setApiEnabled(dto.isApiEnabled());
			entity.setStartDate(DateUtil.getDate(dto.getStartDate().getYear(),
					dto.getStartDate().getMonth(), dto.getStartDate().getDay(), null));
			entity.setExpiryDate(DateUtil.getDate(dto.getExpiryDate().getYear(),
					dto.getExpiryDate().getMonth(), dto.getExpiryDate().getDay(), null));
			entity.setConfigSourceId(dto.getConfigSourceId());
			entity.setName(dto.getName());
			entity.setTypeId(dto.getTypeId());
			entity.setLicensedBy(dto.getLicensedBy());
			entity.setRequiredQualLevel(dto.getRequiredQualLevel());


			entity.setApiEnabled(dto.isApiEnabled());
			entity.setDataCollectionEnabled(dto.getDataCollectionEnabled());
			entity.setQgiVersion(dto.getQgiVersion());
			entity.setAssessmentDefinition(dto.getAssessmentDefinition());

		}
		return entity;
	}

	/**
	 * Map the entity to the DTO
	 *
	 * @param entity
	 * @return
	 */
	public static AssessmentDTO mapEntityToDTO(Assessment entity) {
		AssessmentDTO dto = null;
		if (entity != null) {
			dto = new AssessmentDTO();
			dto.setApiEnabled(entity.isApiEnabled());
			dto.setStartDate(DateUtil.getDayDTO(entity.getStartDate()));
			dto.setExpiryDate(DateUtil.getDayDTO(entity.getExpiryDate()));
			dto.setAcronym(entity.getAcronym());
			dto.setAcronym(entity.getAcronym());
			dto.setAcronym(entity.getAcronym());
			dto.setAcronym(entity.getAcronym());
			dto.setAcronym(entity.getAcronym());
			dto.setAcronym(entity.getAcronym());
			dto.setAcronym(entity.getAcronym());
			dto.setAcronym(entity.getAcronym());
			dto.setAcronym(entity.getAcronym());
			dto.setAcronym(entity.getAcronym());
			dto.setAcronym(entity.getAcronym());

		}
		return dto;

	}
}
