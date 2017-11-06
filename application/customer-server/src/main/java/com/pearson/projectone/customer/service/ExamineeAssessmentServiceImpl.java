package com.pearson.projectone.customer.service;

import com.pearson.projectone.customer.dto.examinee.BundleVariableDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentDTO;
import com.pearson.projectone.customer.dto.examinee.RaterDTO;
import com.pearson.projectone.data.dao.customer.ExamineeAssessmentDao;
import com.pearson.projectone.data.entity.customer.BundleVariable;
import com.pearson.projectone.data.entity.customer.examinee.assessment.ExamineeAssessment;
import com.pearson.projectone.data.entity.customer.examinee.assessment.ExamineeAssessmentStatus;
import com.pearson.projectone.data.entity.customer.examinee.assessment.Rater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.sql.SQLException;
import java.util.Optional;


@Service
@Transactional(rollbackFor = SQLException.class)
public class ExamineeAssessmentServiceImpl implements ExamineeAssessmentService {


	@Autowired
	ExamineeAssessmentDao examineeAssessmentDao;


	/**
	 * Finds examineeAssessment with the goven id
	 *
	 * @param id ExamineeAssessment id. it should be not null
	 * @return
	 */
	@Override
	public Optional<ExamineeAssessmentDTO> getExamineeAsssessmentById(String id) {
		Optional<ExamineeAssessment> examineeAssessmentOptional = Optional.ofNullable(this.examineeAssessmentDao.findOne(id));
		return Optional.ofNullable((examineeAssessmentOptional.isPresent()) ? convertToDTOFromEntity(examineeAssessmentOptional.get()) : null);

	}

	@Override
	public boolean exists(String id) {
		return this.examineeAssessmentDao.exists(id);
	}


//	/**
//	 * Saves ExamineeAssessments to database.
//	 *
//	 * @param examineeAssessmentContainerDTO
//	 * @return ExamineeAssessmentContainerResponseDTO
//	 */
//	public ExamineeAssessmentContainerResponseDTO save(ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO) {
//		final String examineeId = examineeAssessmentContainerDTO.getExamineeId();
//		final Map<String, List<String>> examineeAssessmentIds = new HashMap<>();
//		ExamineeAssessmentHolderDTO[] examineeAssessmentHolderDTOs = examineeAssessmentContainerDTO
//				.getExamineeAssessmentHolders();
//
//		if (!ObjectUtils.isEmpty(examineeAssessmentHolderDTOs)) {
//			for (ExamineeAssessmentHolderDTO examineeAssessmentHolderDTO : examineeAssessmentHolderDTOs) {
//				ExamineeAssessmentDTO[] examineeAssessmentDTOs = examineeAssessmentHolderDTO.getExamineeAssessments();
//				if (!ObjectUtils.isEmpty(examineeAssessmentDTOs)) {
//					for (ExamineeAssessmentDTO examineeAssessmentDTO : examineeAssessmentDTOs) {
//						ExamineeAssessmentDTO savedExamineeAssessment = this.save(prepareExamineeAssessment(
//								examineeAssessmentDTO, examineeId));
//						addExamineeIdToMap(examineeAssessmentIds, savedExamineeAssessment);
//					}
//				}
//			}
//		}
//		return new ExamineeAssessmentContainerResponseDTO(null, convertToGroupIdArray(examineeAssessmentIds));
//	}


	/**
	 * Persists ExamineeAssessment.
	 *
	 * @param examineeAssessment
	 * @return ExamineeAssessmentContainerResponseDTO
	 */
	public ExamineeAssessmentDTO save(ExamineeAssessment examineeAssessment) {
		return this.convertToDTOFromEntity(this.examineeAssessmentDao.save(examineeAssessment));
	}

	/**
	 * Persists ExamineeAssessment
	 *
	 * @param examineeAssessmentDTO
	 * @param examineeId            examinee id.
	 * @return
	 */
	public ExamineeAssessmentDTO save(ExamineeAssessmentDTO examineeAssessmentDTO, String examineeId) {
		return this.convertToDTOFromEntity(this.examineeAssessmentDao.save(
				this.prepareExamineeAssessment(examineeAssessmentDTO, examineeId)));
	}

	/**
	 * Delete examinee ExamineeAssessment
	 *
	 * @param id non null id
	 */
	@Override
	public void delete(final String id) {
		this.examineeAssessmentDao.delete(id);
	}


	/**
	 * Updates ExamineeAssessment.
	 *
	 * @param examineeAssessmentDTO
	 * @return
	 */
	@Override
	public ExamineeAssessmentDTO update(ExamineeAssessmentDTO examineeAssessmentDTO, final String examineeId) {
		ExamineeAssessment examineeAssessment = this.examineeAssessmentDao.findOne(examineeAssessmentDTO.getId());
		return this.convertToDTOFromEntity(this.examineeAssessmentDao.save(prepareExamineeAssessment(
				examineeAssessmentDTO, examineeId, examineeAssessment)));
	}

	private ExamineeAssessment prepareExamineeAssessment(final ExamineeAssessmentDTO examineeAssessmentDTO,
	                                                     final String examineeId,
	                                                     final ExamineeAssessment prepareExamineeAssessment) {
		RaterDTO raterDTO = examineeAssessmentDTO.getRater();
		prepareExamineeAssessment.construct(
				examineeId,
				examineeAssessmentDTO.getFormId(),
				null, //accountId
				examineeAssessmentDTO.getDeliveryTypeId(),
				examineeAssessmentDTO.getExaminerId(),
				ExamineeAssessmentStatus.READY_FOR_ADMINSTRATION.toString(),
				false,
				examineeAssessmentDTO.getAdminstrationDate(),
				null, //parent Id
				examineeAssessmentDTO.getSubtestIds(),
				false, // cloned
				this.convertBundleVariableDTOToEntity(examineeAssessmentDTO.getBundleVariables()),
				new Rater(raterDTO.getFirstName(), raterDTO.getLastName(), raterDTO.getEmail())
		);
		return prepareExamineeAssessment;
	}

	private ExamineeAssessment prepareExamineeAssessment(final ExamineeAssessmentDTO examineeAssessmentDTO,
	                                                     final String examineeId) {

		return this.prepareExamineeAssessment(examineeAssessmentDTO, examineeId, new ExamineeAssessment());
	}

	/**
	 * Converts BundleVariableDTO to BundleVariable entity object.
	 *
	 * @param bundleVariableDTO
	 * @return
	 */
	private BundleVariable convertBundleVariableDTOToEntity(final BundleVariableDTO bundleVariableDTO) {
		if (ObjectUtils.isEmpty(bundleVariableDTO)) {
			return null;
		}
		return new BundleVariable(bundleVariableDTO.getName(), bundleVariableDTO.getValue(),
				bundleVariableDTO.getDataType(), bundleVariableDTO.isRequired());

	}

	/**
	 * Converts ExamineeAssessment entity to ExamineeAssessmentDTO.
	 *
	 * @param examineeAssessment
	 * @return
	 */
	private ExamineeAssessmentDTO convertToDTOFromEntity(final ExamineeAssessment examineeAssessment) {
		ExamineeAssessmentDTO examineeAssessmentDTO = new ExamineeAssessmentDTO();
		examineeAssessmentDTO.setId(examineeAssessment.getId());
		examineeAssessmentDTO.setFormId(examineeAssessment.getFormId());
		examineeAssessmentDTO.setDeliveryTypeId(examineeAssessment.getDeliveryTypeId());
		examineeAssessmentDTO.setAdminstrationDate(examineeAssessment.getAdminstrationDate());
		examineeAssessmentDTO.setStatus(examineeAssessment.getStatus());
		examineeAssessmentDTO.setSubtestIds(examineeAssessment.getSubtests());
		Rater rater = examineeAssessment.getRater();
		examineeAssessmentDTO.setRater(new RaterDTO(rater.getFirstName(), rater.getLastName(), rater.getEmail()));
		return examineeAssessmentDTO;
	}

}
