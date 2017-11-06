package com.pearson.projectone.customer.service;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.customer.dto.examinee.ExaminerDTO;
import com.pearson.projectone.data.entity.customer.examiner.Examiner;
import org.springframework.data.domain.Page;

public interface ExaminerService {

	/**
	 * Create or update Examiner.
	 *
	 * @param examinerDTO
	 * @return BaseDTO
	 */
	public BaseDTO createOrUpdate(final ExaminerDTO examinerDTO);

	/**
	 * Find Examiner with the given id.
	 *
	 * @param id non null id.
	 * @return
	 */
	public ExaminerDTO find(final String id);

	/**
	 * Delete Examiner with the given id
	 *
	 * @param id non null id.
	 */
	public void delete(final String id);

	/**
	 * find Examiners with the given search and page criteria.
	 *
	 * @param pageableSearchRequestDTO
	 * @return
	 */
	public Page<Examiner> listExaminers(final PageableSearchRequestDTO pageableSearchRequestDTO);

	/**
	 * Checks whether entity with the given id exists or not.
	 *
	 * @param id
	 * @return
	 */
	public boolean isExist(final String id);
}
