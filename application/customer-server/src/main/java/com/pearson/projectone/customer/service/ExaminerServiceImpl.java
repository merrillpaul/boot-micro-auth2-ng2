package com.pearson.projectone.customer.service;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.data.search.querydsl.QueryDslPredicateBuilder;
import com.pearson.projectone.customer.dto.examinee.ExaminerDTO;
import com.pearson.projectone.data.dao.customer.qg.ExaminerDao;
import com.pearson.projectone.data.entity.customer.examiner.Examiner;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static com.pearson.projectone.customer.common.Translator.convertExaminerDTOToEntity;
import static com.pearson.projectone.customer.common.Translator.convertExaminerEntityToDTO;
import static com.pearson.projectone.customer.common.Translator.createBaseDTO;

@Service
public class ExaminerServiceImpl implements ExaminerService {

	@Autowired
	private ExaminerDao examinerDao;


	@Override
	public BaseDTO createOrUpdate(ExaminerDTO examinerDTO) {
		Examiner examiner = null;
		if (!ObjectUtils.isEmpty(examinerDTO.getId())) {
			examiner = this.examinerDao.findOne(examinerDTO.getId());
		} else {
			examiner = new Examiner();
		}

		return createBaseDTO(this.examinerDao.save(convertExaminerDTOToEntity(examinerDTO, examiner)));
	}


	@Override
	public ExaminerDTO find(String id) {
		return convertExaminerEntityToDTO(this.examinerDao.findOne(id));
	}

	@Override
	public void delete(String id) {
		if (this.examinerDao.exists(id)) {
			this.examinerDao.delete(id);
		}
	}

	@Override
	public Page<Examiner> listExaminers(PageableSearchRequestDTO pageableSearchRequestDTO) {
		Predicate predicate = new QueryDslPredicateBuilder().with(pageableSearchRequestDTO.getCriteria())
				.build(Examiner.class);
		return this.examinerDao.findAll(predicate, pageableSearchRequestDTO.toPageable());
	}

	@Override
	public boolean isExist(final String id) {
		return this.examinerDao.exists(id);
	}
}
