package com.pearson.projectone.global.service;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.data.search.jpa.JpaSearchSpecificationBuilder;
import com.pearson.projectone.data.dao.global.library.assessment.FormDao;
import com.pearson.projectone.data.entity.global.library.assessment.AssessTest;
import com.pearson.projectone.data.entity.global.library.assessment.Assessment;
import com.pearson.projectone.data.entity.global.library.assessment.Form;
import com.pearson.projectone.global.dto.forms.FormDTO;
import com.pearson.projectone.global.dto.forms.FormDetailsDTO;
import com.pearson.projectone.global.dto.forms.FormHeavyDTO;
import com.pearson.projectone.global.dto.forms.FormLiteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class FormServiceImpl implements FormService {
	@Autowired
	private FormDao formDao;

	@Autowired
	private AssessTestService assessTestService;

	@Autowired
	private AssessmentService assessmentService;

	@Autowired
	private FormDeliveryTypeService formDeliveryTypeService;


	/**
	 * Get form by id.
	 *
	 * @param id
	 * @return
	 */
	@Override
	public FormDTO getForm(String id) {
		return this.convertToDTO(formDao.getOne(id));
	}


	/**
	 * Gets details form.
	 *
	 * @param id Form id
	 * @return FormDetailsDTO
	 */
	public FormDetailsDTO detailedForm(final String id) {
		return this.prepareFormDetailedDTO(formDao.getOne(id));
	}

	/**
	 * This method lists forms with the specifed input page size. By default it lists all available forms.
	 *
	 * @param pageableSearchRequestDTO
	 * @return
	 */
	@Override
	public Page<FormDTO> listFormsPageWise(final PageableSearchRequestDTO pageableSearchRequestDTO) {
		Specification<Form> specification = new JpaSearchSpecificationBuilder<Form>()
				.with(pageableSearchRequestDTO.getCriteria()).build();

		return ObjectUtils.isEmpty(specification) ?
				this.formDao.findAll(pageableSearchRequestDTO.toPageable()).map(f -> convertToDTO(f)) :
				this.formDao.findAll(specification, pageableSearchRequestDTO.toPageable()).map(f -> convertToDTO(f));
	}

	/**
	 * Checks whether Form object with provided exists or not.
	 *
	 * @param id
	 * @return
	 */
	@Override
	public boolean isExists(final String id) {
		return this.formDao.exists(id);
	}

	/**
	 * Deletes Form entity with the provided id.
	 *
	 * @param id
	 */
	public void delete(final String id) {
		Form form = this.formDao.findOne(id);
		if (!CollectionUtils.isEmpty(form.getFormDeliveryType())) {
			form.getFormDeliveryType().forEach(fdt -> {
				this.formDeliveryTypeService.delete(fdt.getId());
			});
		}
		this.formDao.delete(form);
	}

	/**
	 * This methods lists all available forms in the system.
	 *
	 * @return
	 */
	@Override
	public List<FormDTO> listAllForms() {
		return this.formDao.findAll(new Sort("acronym")).stream().map(f -> convertToDTO(f)).collect(Collectors.toList());
	}

	/**
	 * Save Form Entity.
	 *
	 * @param formHeavyDTO
	 * @return
	 */
	public BaseDTO save(FormHeavyDTO formHeavyDTO) {
		Form toBeCreatedForm = this.convertFromFormHeavyDtoToForm(formHeavyDTO);
		Form form = this.formDao.save(toBeCreatedForm);
		this.formDeliveryTypeService.saveFormDeliveryTypes(formHeavyDTO.getFormDeliveryTypeIds(), form.getId());
		return new BaseDTO(form.getId(), form.getVersion());
	}


	/**
	 * This method checks whether Form entity with the given acrinym exists or not.
	 *
	 * @param acronym
	 * @return
	 */
	public boolean isFormAcronymExist(final String acronym) {
		return CollectionUtils.isEmpty(this.formDao.findByAcronym(acronym)) ? false : true;
	}

	/**
	 * Updates Form entity.
	 *
	 * @param formHeavyDTO
	 * @return
	 */
	@Override
	public BaseDTO update(FormHeavyDTO formHeavyDTO) {
		Form existingForm = this.formDao.findOne(formHeavyDTO.getId());
		Form updatedForm = this.formDao.save(this.convertFromFormHeavyDtoToForm(formHeavyDTO, existingForm));
		String[] formDeliveryTypes = formHeavyDTO.getFormDeliveryTypeIds();
		this.formDeliveryTypeService.saveFormDeliveryTypes(formDeliveryTypes, updatedForm.getId());

		return new BaseDTO(updatedForm.getId(), updatedForm.getVersion());
	}

	/**
	 * Converts Form Entity to Form DTO.
	 *
	 * @param form
	 * @return
	 */
	private FormDTO convertToDTO(final Form form) {
		if (ObjectUtils.isEmpty(form)) {
			return null;
		}

		return new FormDTO(form.getId(), form.getVersion(), form.getAcronym(), form.getDescription(),
				form.getName(),
				form.getFormType(), extractFormDeliveryType(form));

	}

	/**
	 * This method exatracts formdeliverytypes from Form Entity object.
	 *
	 * @param form
	 * @return
	 */
	private String[] extractFormDeliveryType(final Form form) {
		return CollectionUtils.isEmpty(form.getFormDeliveryType()) ? new String[0] :
				form.getFormDeliveryType().stream()
						.map(fdt -> fdt.getDeliveryTypeId())
						.toArray(String[]::new);
	}

	/**
	 * This method converts FormHeavyDTO to Form entity object.
	 *
	 * @param formHeavyDTO
	 * @return
	 */
	private Form convertFromFormHeavyDtoToForm(final FormHeavyDTO formHeavyDTO) {
		return this.convertFromFormHeavyDtoToForm(formHeavyDTO, new Form());

	}

	/**
	 * This method converts FormHeavyDTO to Form entity object. It takes input Form object.
	 *
	 * @param formHeavyDTO
	 * @param form         Form Object
	 * @return populated Form object.
	 */
	private Form convertFromFormHeavyDtoToForm(final FormHeavyDTO formHeavyDTO, final Form form) {
		Assessment assessment = this.assessmentService.findOne(formHeavyDTO.getAssessmentId());
		Form parentForm = null;
		AssessTest assessTest = null;
		if (!ObjectUtils.isEmpty(formHeavyDTO.getParentFormId())) {
			parentForm = this.formDao.findOne(formHeavyDTO.getParentFormId());
		}

		if (!ObjectUtils.isEmpty(formHeavyDTO.getAssessTestId())) {
			assessTest = this.assessTestService.findOne(formHeavyDTO.getAssessTestId());
		}

		form.setId(formHeavyDTO.getId());
		form.setAcronym(formHeavyDTO.getAcronym());
		form.setAssessment(assessment);
		form.setProductCode(formHeavyDTO.getProductCode());
		form.setDescription(formHeavyDTO.getDescription());
		form.setStatusId(formHeavyDTO.getStatusId());
		form.setFormDefinition(formHeavyDTO.getFormDefinition());
		form.setParentForm(parentForm);
		form.setDateCollectionExportDef(formHeavyDTO.getDataCollectionExportDef());
		form.setOsaEngineId(formHeavyDTO.getOsaEngineId());
		form.setGroupAdministration(formHeavyDTO.getGroupAdministration());
		form.setFormType(formHeavyDTO.getFormType());
		form.setAssessTest(assessTest);
		return form;

	}

	/**
	 * Checks existence Assessment entity with the given id.
	 *
	 * @param id
	 * @return true if an entity with the given id exists, {@literal false} otherwise
	 */
	public boolean isAssessmentExist(final String id) {
		return this.assessmentService.isAssessmentExist(id);
	}

	/**
	 * List all forms. The {@code FormLiteDTO} carries just id and names.
	 *
	 * @return
	 */
	public List<FormLiteDTO> listAllFormNamesAndIds() {
		return this.formDao.findAll(new Sort("acronym")).stream().map(f -> new FormLiteDTO(f.getId(), f.getName()))
				.collect(Collectors.toList());
	}

	/**
	 * Adds additional required form attributes.
	 *
	 * @param form Form object
	 * @return
	 */
	private FormDetailsDTO prepareFormDetailedDTO(final Form form) {
		if (ObjectUtils.isEmpty(form)) {
			return null;
		}
		return new FormDetailsDTO(form.getId(), form.getVersion(), form.getAcronym(), form.getDescription(),
				form.getName(),
				form.getFormType(), extractFormDeliveryType(form), true);

	}
}
