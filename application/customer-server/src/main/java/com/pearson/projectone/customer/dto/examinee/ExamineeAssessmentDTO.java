package com.pearson.projectone.customer.dto.examinee;

import com.pearson.projectone.core.support.data.BaseDTO;

import java.util.Date;
import java.util.List;

public class ExamineeAssessmentDTO extends BaseDTO {

	public ExamineeAssessmentDTO() {
		//default constructor
	}

	public ExamineeAssessmentDTO(String id, String formId, Date adminstrationDate, String examinerId,
	                             String deliveryTypeId, String status, RaterDTO raterDTO, List<String> subtestIds,
	                             BundleVariableDTO bundleVariables) {
		this.id = id;
		this.formId = formId;
		this.adminstrationDate = adminstrationDate;
		this.examinerId = examinerId;
		this.deliveryTypeId = deliveryTypeId;
		this.status = status;
		this.rater = raterDTO;
		this.subtestIds = subtestIds;
		this.bundleVariables = bundleVariables;
	}

	private String id;
	private String formId;
	private Date adminstrationDate;
	private String examinerId;
	private String deliveryTypeId;

	private String status;
	private RaterDTO rater;
	private List<String> subtestIds;
	private BundleVariableDTO bundleVariables;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public Date getAdminstrationDate() {
		return adminstrationDate;
	}

	public void setAdminstrationDate(Date adminstrationDate) {
		this.adminstrationDate = adminstrationDate;
	}

	public String getExaminerId() {
		return examinerId;
	}

	public void setExaminerId(String examinerId) {
		this.examinerId = examinerId;
	}

	public String getDeliveryTypeId() {
		return deliveryTypeId;
	}

	public void setDeliveryTypeId(String deliveryTypeId) {
		this.deliveryTypeId = deliveryTypeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RaterDTO getRater() {
		return rater;
	}

	public void setRater(RaterDTO rater) {
		this.rater = rater;
	}

	public List<String> getSubtestIds() {
		return subtestIds;
	}

	public void setSubtestIds(List<String> subtestIds) {
		this.subtestIds = subtestIds;
	}

	public BundleVariableDTO getBundleVariables() {
		return bundleVariables;
	}

	public void setBundleVariables(BundleVariableDTO bundleVariables) {
		this.bundleVariables = bundleVariables;
	}
}
