package com.pearson.projectone.global.service;

import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.data.search.jpa.JpaSearchSpecificationBuilder;
import com.pearson.projectone.data.dao.global.library.account.BusinessUnitDao;
import com.pearson.projectone.data.dao.global.library.assessment.AssessmentDao;
import com.pearson.projectone.data.dao.global.library.assessment.FormDao;
import com.pearson.projectone.data.entity.global.library.account.BusinessUnit;
import com.pearson.projectone.data.entity.global.library.assessment.Assessment;
import com.pearson.projectone.data.entity.global.library.assessment.BusinessUnitAssessment;
import com.pearson.projectone.data.entity.global.library.assessment.Form;
import com.pearson.projectone.global.dto.businessunit.BusinessAssessmentListDTO;
import com.pearson.projectone.global.dto.businessunit.BusinessUnitCreateCommandDTO;
import com.pearson.projectone.global.dto.businessunit.BusinessUnitDTO;
import com.pearson.projectone.global.dto.businessunit.BusinessUnitFormListDTO;
import com.pearson.projectone.global.dto.businessunit.BusinessUnitUpdateCommandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessUnitService {

	@Autowired
	private BusinessUnitDao businessUnitDao;

	@Autowired
	private AssessmentDao assessmentDao;

	@Autowired
	private FormDao formDao;

	public BusinessUnitDTO findByIdNameOrGuid(String id, String name, String guid) {
		BusinessUnitDTO businessUnitDTO = null;
		BusinessUnit businessUnit;
		if (!StringUtils.isEmpty(name)) {
			businessUnit = businessUnitDao.findByName(name);
		} else if (!StringUtils.isEmpty(guid)) {
			businessUnit = businessUnitDao.findByGuid(guid);
		} else {
			businessUnit = businessUnitDao.findOne(id);
		}
		businessUnitDTO = new BusinessUnitDTO();
		businessUnitDTO.setVersion(businessUnit.getVersion());
		// TODO rest
		return businessUnitDTO;
	}

	public BusinessUnit createBusinessUnit(BusinessUnitCreateCommandDTO createCommand) {
		// TODO call save on dao
		return null;
	}

	public BusinessUnit updateBusinessUnit(BusinessUnitUpdateCommandDTO updateCommand) {
		// TODO call save on dao
		return null;
	}

	public void deleteBusinessUnit(String id) {
		// TODO
	}

	public Page<BusinessUnitDTO> list(PageableSearchRequestDTO searchRequest) {
		Specification<BusinessUnit> specification = new JpaSearchSpecificationBuilder<BusinessUnit>()
				.with(searchRequest.getCriteria()).build();
		List<BusinessUnitDTO> list = new ArrayList<>(0);
		return businessUnitDao.findAll(specification, searchRequest.toPageable()).map(new Converter<BusinessUnit, BusinessUnitDTO>() {
			@Override
			public BusinessUnitDTO convert(BusinessUnit source) {
				BusinessUnitDTO dto = new BusinessUnitDTO();
				dto.setId(source.getId());
				// TODO rest
				return dto;
			}
		});
	}


	/**
	 * Pagination and search
	 *
	 * @param businessUnitId
	 * @param searchRequest
	 * @return
	 */
	public Page<BusinessAssessmentListDTO> listAssessments(String businessUnitId, PageableSearchRequestDTO searchRequest) {
		Specification<Assessment> specification = new JpaSearchSpecificationBuilder<Assessment>()
				.with(searchRequest.getCriteria()).build();
		Specification<Assessment> businessUnitAssessmentSpecification = hasBusinessUnit(businessUnitId);
		if (ObjectUtils.isEmpty(specification)) {
			specification = businessUnitAssessmentSpecification;
		} else {
			specification = Specifications.where(businessUnitAssessmentSpecification).and(specification);
		}
		return assessmentDao.findAll(specification, searchRequest.toPageable()).map(new Converter<Assessment, BusinessAssessmentListDTO>() {
			@Override
			public BusinessAssessmentListDTO convert(Assessment source) {
				BusinessAssessmentListDTO dto = new BusinessAssessmentListDTO();
				dto.setId(source.getId());
				dto.setVersion(source.getVersion());
				dto.setAssessmentName(source.getName());
				return dto;
			}
		});
	}


	/**
	 * Pagination and search
	 *
	 * @param businessUnitId
	 * @param searchRequest
	 * @return
	 */
	public Page<BusinessUnitFormListDTO> listForms(String businessUnitId, PageableSearchRequestDTO searchRequest) {
		Specification<Form> specification = new JpaSearchSpecificationBuilder<Form>()
				.with(searchRequest.getCriteria()).build();
		Specification<Form> businessUnitAssessmentFormSpecification = hasBusinessUnitForForm(businessUnitId);

		if (ObjectUtils.isEmpty(specification)) {
			specification = businessUnitAssessmentFormSpecification;
		} else {
			specification = Specifications.where(businessUnitAssessmentFormSpecification).and(specification);
		}

		return formDao.findAll(specification, searchRequest.toPageable()).map(new Converter<Form, BusinessUnitFormListDTO>() {
			@Override
			public BusinessUnitFormListDTO convert(Form source) {
				BusinessUnitFormListDTO dto = new BusinessUnitFormListDTO();
				dto.setId(source.getId());
				dto.setVersion(source.getVersion());
				dto.setAssessmentName(source.getAssessment().getName());
				dto.setAssessmentId(source.getAssessment().getId());
				dto.setFormType(source.getFormType());
				dto.setAcronym(source.getAcronym());
				dto.setDescription(source.getDescription());
				return dto;
			}
		});
	}


	private Specification<Assessment> hasBusinessUnit(final String businessUnitId) {
		BusinessUnit businessUnit = businessUnitDao.findOne(businessUnitId);
		return (root, query, cb) -> {
			query.distinct(true);
			Root<Assessment> assessment = root;
			Root<BusinessUnitAssessment> businessUnitAssessmentRoot = query.from(BusinessUnitAssessment.class);
			Expression<Assessment> assessmentExpression = businessUnitAssessmentRoot.get("assessment");
			Expression<BusinessUnit> businessUnitExpressionExpression = businessUnitAssessmentRoot.get("businessUnit");
			return cb.and(cb.equal(businessUnitExpressionExpression, businessUnit), cb.equal(assessment, assessmentExpression));
		};
	}

	private Specification<Form> hasBusinessUnitForForm(final String businessUnitId) {
		BusinessUnit businessUnit = businessUnitDao.findOne(businessUnitId);
		return (root, query, cb) -> {
			query.distinct(true);
			Root<Form> form = root;
			Root<Assessment> assessment = query.from(Assessment.class);
			Expression<Assessment> formAssessmentExpression = root.get("assessment");
			Root<BusinessUnitAssessment> businessUnitAssessmentRoot = query.from(BusinessUnitAssessment.class);
			Expression<BusinessUnit> businessUnitExpression = businessUnitAssessmentRoot.get("businessUnit");
			Expression<Assessment> businessUnitAssessmentExpression = businessUnitAssessmentRoot.get("assessment");
			return cb.and(
					cb.equal(businessUnitExpression, businessUnit),
					cb.equal(assessment, formAssessmentExpression),
					cb.equal(businessUnitAssessmentExpression, formAssessmentExpression)
			);
		};
	}
}
