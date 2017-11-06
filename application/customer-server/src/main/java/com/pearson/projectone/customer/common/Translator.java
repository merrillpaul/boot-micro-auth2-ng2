package com.pearson.projectone.customer.common;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.DocumentEntity;
import com.pearson.projectone.customer.dto.examinee.ExaminerDTO;
import com.pearson.projectone.data.entity.customer.examiner.Examiner;

/**
 * This is common translator class across customer server.
 */
public final class Translator {

	private Translator() {
		//Can not be instantiated.
	}

	/**
	 * Converts ExaminerDTO to Examiner entity.
	 *
	 * @param examinerDTO
	 * @return
	 */
	public static Examiner convertExaminerDTOToEntity(final ExaminerDTO examinerDTO) {
		return convertExaminerDTOToEntity(examinerDTO, new Examiner());
	}

	/**
	 * Converts ExaminerDTO to Examiner entity.
	 *
	 * @param examinerDTO
	 * @return
	 */
	public static Examiner convertExaminerDTOToEntity(final ExaminerDTO examinerDTO, final Examiner examiner) {
		examiner.construct(
				examinerDTO.getTitle(),
				examinerDTO.getFirstName(),
				examinerDTO.getLastName(),
				examinerDTO.getMiddleName(),
				examinerDTO.getSuffix(),
				examinerDTO.getExaminerId()
		);
		return examiner;

	}

	/**
	 * Converts Examiner to DTO
	 *
	 * @param examiner
	 * @return
	 */
	public static ExaminerDTO convertExaminerEntityToDTO(final Examiner examiner) {
		return new ExaminerDTO(
				examiner.getId(),
				examiner.getVersion(),
				examiner.getTitle(),
				examiner.getFirstName(),
				examiner.getLastName(),
				examiner.getMiddleName(),
				examiner.getSuffix(),
				examiner.getExaminerId()
		);
	}


	/**
	 * Created BaseDTO from DocumentEntity.
	 *
	 * @param t Any class that extends DocumentEntity
	 * @return
	 */
	public static <T extends DocumentEntity> BaseDTO createBaseDTO(final T t) {
		return new BaseDTO(t.getId(), t.getVersion());
	}
}
