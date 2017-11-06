package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Entity;

/**
 * Created by uphilji on 4/18/17.
 */
@Entity
public class FormDeliveryType extends RelationalEntity {

	public FormDeliveryType() {
		//default constructor
	}

	public FormDeliveryType(String formId, String deliveryTypeId) {
		super();
		this.formId = formId;
		this.deliveryTypeId = deliveryTypeId;
	}

	private String deliveryTypeId;

	private String formId;


	public String getDeliveryTypeId() {
		return deliveryTypeId;
	}

	public void setDeliveryTypeId(String deliveryTypeId) {
		this.deliveryTypeId = deliveryTypeId;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
}
