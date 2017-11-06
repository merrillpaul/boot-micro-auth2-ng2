package com.pearson.projectone.customer.service;

import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentDTO;
import com.pearson.projectone.data.entity.customer.examinee.assessment.ExamineeAssessment;

import java.util.Optional;

public interface ExamineeAssessmentService {

	/**
	 * Retreived an Examinee Assessment by id.
	 *
	 * @param id
	 */
	public Optional<ExamineeAssessmentDTO> getExamineeAsssessmentById(String id);

	/**
	 * Check entity existence with the given id.
	 *
	 * @param id
	 * @return
	 */
	public boolean exists(String id);


//	/**
//	 * Saves ExamineeAssessments to database.
//	 *
//	 * @param examineeAssessmentContainerDTO
//	 * @return ExamineeAssessmentContainerResponseDTO
//	 */
//	public ExamineeAssessmentContainerResponseDTO save(ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO);

	/**
	 * Persists ExamineeAssessment.
	 *
	 * @param examineeAssessment
	 * @return ExamineeAssessmentContainerResponseDTO
	 */
	public ExamineeAssessmentDTO save(ExamineeAssessment examineeAssessment);

	/**
	 * Persists ExamineeAssessment
	 *
	 * @param examineeAssessmentDTO
	 * @param examineeId            examinee id.
	 * @return
	 */
	public ExamineeAssessmentDTO save(ExamineeAssessmentDTO examineeAssessmentDTO, String examineeId);

	/**
	 * Delete examinee ExamineeAssessment
	 *
	 * @param id non null id
	 */
	public void delete(final String id);

	/**
	 * Updates ExamineeAssessment.
	 *
	 * @param examineeAssessmentDTO
	 * @return
	 */
	public ExamineeAssessmentDTO update(ExamineeAssessmentDTO examineeAssessmentDTO, final String examineeId);

}
