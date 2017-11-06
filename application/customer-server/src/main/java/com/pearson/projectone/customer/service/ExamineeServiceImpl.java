package com.pearson.projectone.customer.service;

import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.data.search.querydsl.QueryDslPredicateBuilder;
import com.pearson.projectone.customer.dto.examinee.ExamineeDTO;
import com.pearson.projectone.data.constants.Gender;
import com.pearson.projectone.data.dao.customer.ExamineeDao;
import com.pearson.projectone.data.entity.customer.examinee.Examinee;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This ExamineeServiceImpl class implements service methods in ExamineeService
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ExamineeServiceImpl implements ExamineeService {

	@Autowired
	private ExamineeDao examineeDao;


	@Override
	public Optional<ExamineeDTO> findExamineeById(final String id) {
		return Optional.ofNullable(this.convertToDTO(this.examineeDao.findOne(id)));
	}

	@Override
	public Examinee create(ExamineeDTO examineeDTO) {
		return this.examineeDao.save(convertToEntity(examineeDTO));
	}

	// This is WIP
	@Override
	public Optional<List<ExamineeDTO>> list() {

		List<ExamineeDTO> examineeList = new ArrayList<>();
		this.examineeDao.findAll(new PageRequest(0, 100)).forEach(examinee -> examineeList.add(convertToDTO(examinee)));
		return Optional.ofNullable(examineeList.isEmpty() ? null : examineeList);
	}

	@Override
	public Page<ExamineeDTO> list(PageableSearchRequestDTO pageableSearchRequestDTO) {

		Predicate predicate = new QueryDslPredicateBuilder().with(pageableSearchRequestDTO.getCriteria())
				.build(Examinee.class);
		return this.examineeDao.findAll(predicate, pageableSearchRequestDTO.toPageable()).map(e -> this.convertToDTO(e));
	}


	@Override
	public List<Examinee> filterByName(String name) {
		return this.examineeDao.findByFirstNameContainingOrLastNameContainingOrExamineeIdContainingAllIgnoreCase(name, name, name);
	}

	@Override
	public void delete(String id) {
		this.examineeDao.delete(id);
	}

	@Override
	public Examinee update(ExamineeDTO examineeDTO) {

		Examinee examinee = examineeDao.findOne(examineeDTO.getId());
		return examineeDao.save(convertToEntity(examineeDTO, examinee));
	}

	@Override
	public boolean exists(String id) {
		return this.examineeDao.exists(id);
	}


	/**
	 * Examinee entity to ExamineeDTO converter.
	 *
	 * @param examinee
	 * @return
	 */
	public ExamineeDTO convertToDTO(Examinee examinee) {
		if (null == examinee) {
			return null;
		}
		ExamineeDTO examineeDTO = new ExamineeDTO(examinee.getId(), examinee.getFirstName(), examinee.getLastName(),
				examinee.getMiddleName(), examinee.getGender().toString(), examinee.getDob(), examinee.getExamineeId());
		return examineeDTO;
	}

	private Examinee convertToEntity(ExamineeDTO examineeDTO) {
		Examinee examinee = new Examinee();
		return convertToEntity(examineeDTO, examinee);
	}

	/**
	 * Converts ExamineeDTO to Examinee.
	 *
	 * @param examineeDTO
	 * @param examinee
	 * @return
	 */
	private Examinee convertToEntity(ExamineeDTO examineeDTO, Examinee examinee) {
		examinee.setExamineeId(examineeDTO.getExamineeId());
		examinee.setFirstName(examineeDTO.getFirstName());
		examinee.setLastName(examineeDTO.getLastName());
		examinee.setMiddleName(examineeDTO.getMiddleName());
		examinee.setGender(Gender.valueOf(examineeDTO.getGender()));
		examinee.setDob(examineeDTO.getDob());
		return examinee;
	}


}
