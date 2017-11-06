package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;
import com.pearson.projectone.data.entity.global.library.account.BusinessUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class BusinessUnitAssessment extends RelationalEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	private BusinessUnit businessUnit;

	@ManyToOne(fetch = FetchType.LAZY)
	private Assessment assessment;

	@Column
	private String websiteLink;

	@Column
	private String isbn;

	@Lob
	@Column
	private String comments;

	@Column
	private String status;

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public String getWebsiteLink() {
		return websiteLink;
	}

	public void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
