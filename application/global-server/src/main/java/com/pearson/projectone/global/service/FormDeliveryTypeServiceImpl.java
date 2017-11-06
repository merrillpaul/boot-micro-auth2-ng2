package com.pearson.projectone.global.service;

import com.pearson.projectone.data.dao.global.library.assessment.FormDeliveryTypeDao;
import com.pearson.projectone.data.entity.global.library.assessment.FormDeliveryType;
import com.pearson.projectone.global.dto.forms.FormDeliveryTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Service implementation class for FormDeliveryType
 */
@Service
public class FormDeliveryTypeServiceImpl implements FormDeliveryTypeService {

	@Autowired
	private FormDeliveryTypeDao formDeliveryTypeDao;


	/**
	 * delete FormDeliveryType entity.
	 *
	 * @param id FormDeliveryType id.
	 */
	public void delete(String id) {
		this.formDeliveryTypeDao.delete(id);
	}

	/**
	 * Saves FormDeliveryType.
	 *
	 * @param formId
	 * @param deliveryTypeId
	 * @return persisted entity.
	 */
	public FormDeliveryType save(final String formId, final String deliveryTypeId) {
		List<FormDeliveryType> formDeliveryTypeList =
				this.formDeliveryTypeDao.findByFormIdAndDeliveryTypeId(formId,
						deliveryTypeId);
		if (CollectionUtils.isEmpty(formDeliveryTypeList)) {
			return this.formDeliveryTypeDao.save(new FormDeliveryType(formId, deliveryTypeId));
		}
		return null;
	}

	/**
	 * Saves FormDeliveryType
	 *
	 * @param formDeliveryTypes
	 * @param formId
	 */
	public void saveFormDeliveryTypes(final String[] formDeliveryTypes, final String formId) {
		if (!ObjectUtils.isEmpty(formDeliveryTypes) && formDeliveryTypes.length > 0) {
			Arrays.stream(formDeliveryTypes).forEach(fdt -> this.save(formId, fdt));
		}
	}

	/**
	 * Saves FormDeliveryType
	 *
	 * @param formDeliveryTypes
	 * @param formId
	 */
	public void updateFormDeliveryTypes(final FormDeliveryTypeDTO[] formDeliveryTypes, final String formId) {
		/**
		 * WIP: Update specific logic should be added.
		 */
	}

	/**
	 * This method converts {@code Saves FormDeliveryTypeDTO} to {@code Saves FormDeliveryType}
	 *
	 * @param formDeliveryTypeDTO
	 * @return FormDeliveryType
	 */
	private FormDeliveryType convertDTOToEntity(final FormDeliveryTypeDTO formDeliveryTypeDTO) {
		return new FormDeliveryType(formDeliveryTypeDTO.getId(), formDeliveryTypeDTO.getValue());
	}


}
