package com.pearson.projectone.customer.service;

import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeDTO;
import com.pearson.projectone.data.entity.customer.examinee.Examinee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Created by ukakapr on 3/23/17.
 */
public interface ExamineeService {


	/**
	 * Find examinee by Id.
	 *
	 * @param id
	 * @return
	 */
	public Optional<ExamineeDTO> findExamineeById(final String id);

	/**
	 * This method creates a new examinee.
	 *
	 * @param examineeDTO
	 * @return
	 */
	public Examinee create(ExamineeDTO examineeDTO);

	/**
	 * This method lists all examinees.
	 *
	 * @return
	 */
	public Optional<List<ExamineeDTO>> list();

	/**
	 * This method lists pageable Examinees. It takes PageableSearchRequestDTO as input.
	 * The Example input object in JSON format is given here.
	 * {
	 * "pageRequest":{
	 * "orders":[
	 * <p>
	 * ],
	 * "page":0,
	 * "size":15
	 * },
	 * "criteria":[
	 * {
	 * "key":"firstName",
	 * "operation":"_*_",
	 * "value":"Prasad"
	 * }
	 * ]
	 * }
	 *
	 * @param pageableSearchRequestDTO
	 * @return
	 */
	public Page<ExamineeDTO> list(PageableSearchRequestDTO pageableSearchRequestDTO);

	/**
	 * This methods returns examinee filtered by examinee name.
	 *
	 * @return
	 */
	public List<Examinee> filterByName(String name);

	/**
	 * Deletes the examinee entity with the given id.
	 *
	 * @param id
	 */
	public void delete(String id);

	/**
	 * Updates existing examinee with new values.
	 *
	 * @param examineeDTO
	 * @return
	 */

	public Examinee update(ExamineeDTO examineeDTO);


	/**
	 * Checks Examinee entity exists ot not,
	 *
	 * @param id
	 * @return
	 */
	public boolean exists(String id);

	/**
	 * Examinee entity to ExamineeDTO converter.
	 *
	 * @param examinee
	 * @return
	 */
	public ExamineeDTO convertToDTO(Examinee examinee);
}
