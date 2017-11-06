package com.pearson.projectone.global.service;

import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.data.search.jpa.JpaSearchSpecificationBuilder;
import com.pearson.projectone.data.dao.global.library.assessment.AssessmentDao;
import com.pearson.projectone.data.entity.global.library.assessment.Assessment;
import com.pearson.projectone.global.dto.assessment.AssessmentDTO;
import com.pearson.projectone.global.dto.assessment.MapAssessTestCommandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssessmentService {

	@Autowired
	private AssessmentDao assessmentDao;

	public AssessmentDTO getAssessment(String id) {
		// TODO impl
		return null;
	}

	public Assessment createAssessment(AssessmentDTO assessmentDTO) {
		Assessment assessment = new Assessment();
		// TODO copy props
		return assessmentDao.saveAndFlush(assessment);
	}

	public Assessment updateAssessment(AssessmentDTO assessmentDTO) {
		Assessment assessment = assessmentDao.findOne(assessmentDTO.getId());
		// TODO copy props
		return assessmentDao.saveAndFlush(assessment);
	}

	public void deleteAssessment(String id) {
		// TODO impl
	}

	public Page<AssessmentDTO> list(PageableSearchRequestDTO searchRequest) {
		Specification<Assessment> specification = new JpaSearchSpecificationBuilder<>()
				.with(searchRequest.getCriteria()).build();
		List<AssessmentDTO> list = new ArrayList<>(0);
		if (ObjectUtils.isEmpty(specification)) {
			return assessmentDao.findAll(searchRequest.toPageable()).map(getAssessmentConverter());
		} else {
			return assessmentDao.findAll(specification, searchRequest.toPageable()).map(getAssessmentConverter());
		}
	}

	public void mapAssessTest(MapAssessTestCommandDTO mapCommand) {
		// TODO impl
	}


	private Converter<Assessment, AssessmentDTO> getAssessmentConverter() {
		return new Converter<Assessment, AssessmentDTO>() {
			@Override
			public AssessmentDTO convert(Assessment source) {
				AssessmentDTO dto = new AssessmentDTO();
				dto.setId(source.getId());
				dto.setVersion(source.getVersion());
				// TODO copy rest of props
				return dto;
			}
		};
	}

	/**
	 * This method checks whether assessment exists or not.
	 *
	 * @param id must not be null
	 * @return true if an entity with the given id exists, {@literal false} otherwise
	 */
	public boolean isAssessmentExist(final String id) {
		return ObjectUtils.isEmpty(id) ? false : this.assessmentDao.exists(id);
	}

	/**
	 * Finds assessment with the provided id.
	 *
	 * @param id Assessment id
	 * @return
	 */
	public Assessment findOne(final String id) {
		return this.assessmentDao.findOne(id);
	}
}
