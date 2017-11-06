package com.pearson.projectone.data.entity.customer.examinee.assessment;

import com.pearson.projectone.core.support.data.DocumentEntity;
import com.pearson.projectone.data.entity.customer.BundleVariable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "ExamineeAssessment")
public class ExamineeAssessment extends DocumentEntity {

	public ExamineeAssessment() {
		//Default constructor
	}

	public ExamineeAssessment(String examineeId, String formId, String accountId, String deliveryTypeId,
	                          String examinerId, String status, boolean practiceMode, Date adminstrationDate,
	                          String parentAssessmentId, List<String> subtests, boolean cloned,
	                          BundleVariable bundleVariables, Rater rater) {

		construct(examineeId, formId, accountId, deliveryTypeId, examinerId, status, practiceMode, adminstrationDate,
				parentAssessmentId, subtests, cloned, bundleVariables, rater);

	}

	public void construct(String examineeId, String formId, String accountId, String deliveryTypeId, String examinerId,
	                      String status, boolean practiceMode, Date adminstrationDate, String parentAssessmentId,
	                      List<String> subtests, boolean cloned, BundleVariable bundleVariables, Rater rater) {
		this.examineeId = examineeId;
		this.formId = formId;
		this.accountId = accountId;
		this.deliveryTypeId = deliveryTypeId;
		this.examinerId = examinerId;
		this.status = status;
		this.practiceMode = practiceMode;
		this.adminstrationDate = adminstrationDate;
		this.parentAssessmentId = parentAssessmentId;
		this.subtests = subtests;
		this.cloned = cloned;
		this.bundleVariables = bundleVariables;
		this.rater = rater;
	}

	private String examineeId;

	private String formId;

	private String accountId;

	private String deliveryTypeId;

	private String examinerId;

	private String status;

	private boolean practiceMode;

	private Date adminstrationDate;

	private String parentAssessmentId;

	private List<String> subtests;

	private boolean cloned;

	private Rater rater;

	private BundleVariable bundleVariables;

	public String getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


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

	public String getExaminerId() {
		return examinerId;
	}

	public void setExaminerId(String examinerId) {
		this.examinerId = examinerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isPracticeMode() {
		return practiceMode;
	}

	public void setPracticeMode(boolean practiceMode) {
		this.practiceMode = practiceMode;
	}

	public Date getAdminstrationDate() {
		return adminstrationDate;
	}

	public void setAdminstrationDate(Date adminstrationDate) {
		this.adminstrationDate = adminstrationDate;
	}

	public String getParentAssessmentId() {
		return parentAssessmentId;
	}

	public void setParentAssessmentId(String parentAssessmentId) {
		this.parentAssessmentId = parentAssessmentId;
	}

	public boolean isCloned() {
		return cloned;
	}

	public void setCloned(boolean cloned) {
		this.cloned = cloned;
	}

	public List<String> getSubtests() {
		return subtests;
	}

	public void setSubtests(List<String> subtests) {
		this.subtests = subtests;
	}

	public BundleVariable getBundleVariables() {
		return bundleVariables;
	}

	public void setBundleVariables(BundleVariable bundleVariables) {
		this.bundleVariables = bundleVariables;
	}

	public Rater getRater() {
		return rater;
	}

	public void setRater(Rater rater) {
		this.rater = rater;
	}
}
