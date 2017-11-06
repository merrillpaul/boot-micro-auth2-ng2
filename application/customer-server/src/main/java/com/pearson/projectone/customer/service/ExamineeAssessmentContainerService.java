package com.pearson.projectone.customer.service;


import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentContainerDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentContainerDetailedDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentContainerResponseDTO;
import org.springframework.data.domain.Page;

public interface ExamineeAssessmentContainerService {

	/**
	 * Fetches Pageable containers by filtering the search criteria.
	 *
	 * @param pageableSearchRequestDTO
	 * @return
	 */
	public Page<ExamineeAssessmentContainerDTO> getContainers(final PageableSearchRequestDTO pageableSearchRequestDTO);

	/**
	 * Fetches Pageable conatainer by filtering serach criteria.
	 *
	 * @param pageableSearchRequestDTO
	 * @return
	 */
	public Page<ExamineeAssessmentContainerDetailedDTO> findMostRecentContainers(
			final PageableSearchRequestDTO pageableSearchRequestDTO);

	/**
	 * Creates ExamineeAssessmentContainer.
	 *
	 * @param examineeAssessmentContainerDTO
	 * @return ExamineeAssessmentContainerResponseDTO
	 */
	public ExamineeAssessmentContainerResponseDTO create(
			final ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO);

	/**
	 * Delete ExamineeAssessmentContainer entity with the give id
	 *
	 * @param id non null id
	 */
	public void delete(String id);


	/**
	 * Delete ExamineeAssessment in ExamineeAssessmentContainer entity with the give id
	 *
	 * @param id non null id
	 */
	public void delete(String id, String assessmentId);

	/**
	 * Checks existence of entity with the given id.
	 *
	 * @param id
	 * @return
	 */
	public boolean exists(String id);

	/**
	 * Checks existence of ExamineeAssessment in ExamineeAssessmentContainer entity.
	 *
	 * @param id
	 * @param assessmentId
	 * @return
	 */
	public boolean exists(String id, String assessmentId);

	/**
	 * Finds ExamineeAssessmentContainer entity with the given id.
	 *
	 * @param id
	 * @return
	 */
	public ExamineeAssessmentContainerDTO find(String id);

	/**
	 * Checks whether ExamineeAssessment container is valid or not.
	 *
	 * @param examineeAssessmentContainerDTO
	 * @return
	 */
	public boolean isValidExamineeAssessmentContainer(
			final ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO);

	/**
	 * Updates ExamineeAssessmentContainer.
	 *
	 * @param examineeAssessmentContainerDTO
	 * @return ExamineeAssessmentContainerResponseDTO
	 */
	public ExamineeAssessmentContainerResponseDTO update(ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO);
}
