package com.pearson.projectone.data.dao.global.library.assessment;

import com.pearson.projectone.data.entity.global.library.assessment.FormDeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FormDeliveryTypeDao extends JpaRepository<FormDeliveryType, String>, JpaSpecificationExecutor<FormDeliveryType> {
	/**
	 * Find FormDeliveryType list with given {@code formId} and {@code deliveryTypeId}
	 * @param formId non null formId
	 * @param deliveryTypeId non null deliveryTypeId
	 * @return List of FormDeliveryType entities if found, empty list otherwise.
	 */
	public List<FormDeliveryType> findByFormIdAndDeliveryTypeId(final String formId, final String deliveryTypeId);

	/**
	 * Find FormDeliveryType list with given {@code formId}
	 * @param formId non null formId
	 * @return List of FormDeliveryType entities if found, empty list otherwise.
	 */
	public List<FormDeliveryType> findByFormId(final String formId);
}
