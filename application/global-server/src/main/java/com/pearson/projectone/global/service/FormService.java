package com.pearson.projectone.global.service;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.global.dto.forms.FormDTO;
import com.pearson.projectone.global.dto.forms.FormDetailsDTO;
import com.pearson.projectone.global.dto.forms.FormHeavyDTO;
import com.pearson.projectone.global.dto.forms.FormLiteDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FormService {


	/**
	 * Get form by id.
	 *
	 * @param id
	 * @return
	 */
	public FormDTO getForm(String id);


	/**
	 * Gets details form.
	 *
	 * @param id Form id
	 * @return FormDetailsDTO
	 */
	public FormDetailsDTO detailedForm(final String id);

	/**
	 * This method lists forms with the specifed input page size. By default it lists all available forms.
	 *
	 * @param pageableSearchRequestDTO
	 * @return
	 */
	public Page<FormDTO> listFormsPageWise(final PageableSearchRequestDTO pageableSearchRequestDTO);

	/**
	 * This methods lists all available forms in the system.
	 *
	 * @return
	 */
	public List<FormDTO> listAllForms();


	/**
	 * List all forms. The {@code FormLiteDTO} carries just id and names.
	 *
	 * @return
	 */
	public List<FormLiteDTO> listAllFormNamesAndIds();


	/**
	 * Checks whether Form object with provided exists or not.
	 *
	 * @param id
	 * @return
	 */
	public boolean isExists(final String id);

	/**
	 * Deletes Form entity with the provided id.
	 *
	 * @param id
	 */
	public void delete(final String id);

	/**
	 * Save Form Entity.
	 *
	 * @param formHeavyDTO
	 * @return
	 */
	public BaseDTO save(FormHeavyDTO formHeavyDTO);

	/**
	 * Updates form Entity.
	 * @param formHeavyDTO
	 * @return
	 */

	/**
	 * This method checks whether Form entity with the given acrinym exists or not.
	 *
	 * @param acronym
	 * @return
	 */
	public boolean isFormAcronymExist(final String acronym);

	/**
	 * Updates Form entity.
	 *
	 * @param formHeavyDTO
	 * @return
	 */
	public BaseDTO update(FormHeavyDTO formHeavyDTO);

	/**
	 * Checks existence Assessment entity with the given id.
	 *
	 * @param id
	 * @return true if an entity with the given id exists, {@literal false} otherwise
	 */
	public boolean isAssessmentExist(final String id);
}
