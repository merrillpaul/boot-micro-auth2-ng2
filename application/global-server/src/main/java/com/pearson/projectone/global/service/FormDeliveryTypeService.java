package com.pearson.projectone.global.service;

import com.pearson.projectone.data.entity.global.library.assessment.FormDeliveryType;
import com.pearson.projectone.global.dto.forms.FormDeliveryTypeDTO;

/**
 * Service interface for FormDeliveryType
 */
public interface FormDeliveryTypeService {

	/**
	 * delete FormDeliveryType entity.
	 *
	 * @param id FormDeliveryType id.
	 */
	public void delete(String id);

	/**
	 * Saves FormDeliveryType.
	 *
	 * @param formId
	 * @param deliveryTypeId
	 * @return persisted entity.
	 */
	public FormDeliveryType save(final String formId, final String deliveryTypeId);

	/**
	 * Saves FormDeliveryType
	 *
	 * @param formDeliveryTypes
	 * @param formId
	 */
	public void saveFormDeliveryTypes(final String[] formDeliveryTypes, final String formId);
}
