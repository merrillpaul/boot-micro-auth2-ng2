package com.pearson.projectone.data.entity.global.library.assessment;

import com.pearson.projectone.core.support.data.RelationalEntity;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/**
 * Created by uphilji on 4/13/17.
 */
@Entity
public class Form extends RelationalEntity {

	public Form() {
		//default Constructor
	}

	public Form(String acronym, String name, Assessment assessment, String productCode, String description, String statusId,
	            String formDefinition, Form parentForm, String dataCollectionExportDef, Long osaEngineId,
	            boolean groupAdministration, String formType, AssessTest assessTest) {
		this.acronym = acronym;
		this.name = name;
		this.assessment = assessment;
		this.productCode = productCode;
		this.description = description;
		this.statusId = statusId;
		this.formDefinition = formDefinition;
		this.parentForm = parentForm;
		this.dateCollectionExportDef = dataCollectionExportDef;
		this.osaEngineId = osaEngineId;
		this.groupAdministration = groupAdministration;
		this.formType = formType;
		this.assessTest = assessTest;
	}

	@Column
	private String acronym;

	@Column
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private Assessment assessment;

	@Column
	private String productCode;

	@Column(length = 500)
	private String description;

	@Column
	private String statusId;

	@Lob
	@Column
	private String formDefinition;

	@OneToOne(fetch = FetchType.LAZY)
	private Form parentForm;

	@Lob
	@Column
	private String dateCollectionExportDef;

	@Column
	private Long osaEngineId;

	@Column(length = 1)
	@Type(type = "yes_no")
	private boolean groupAdministration;

	@Column
	private String formType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "FormAssessTest")
	private AssessTest assessTest;


	@Column
	@OneToMany(mappedBy = "formId", fetch = FetchType.EAGER)
	private Set<FormDeliveryType> formDeliveryType;


	//
	//   Getters and Setters...
	//


	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getFormDefinition() {
		return formDefinition;
	}

	public void setFormDefinition(String formDefinition) {
		this.formDefinition = formDefinition;
	}

	public Form getParentForm() {
		return parentForm;
	}

	public void setParentForm(Form parentForm) {
		this.parentForm = parentForm;
	}

	public String getDateCollectionExportDef() {
		return dateCollectionExportDef;
	}

	public void setDateCollectionExportDef(String dateCollectionExportDef) {
		this.dateCollectionExportDef = dateCollectionExportDef;
	}

	public long getOsaEngineId() {
		return osaEngineId;
	}

	public void setOsaEngineId(long osaEngineId) {
		this.osaEngineId = osaEngineId;
	}

	public boolean getGroupAdministration() {
		return groupAdministration;
	}

	public void setGroupAdministration(boolean groupAdministration) {
		this.groupAdministration = groupAdministration;
	}

	public boolean isGroupAdministration() {
		return groupAdministration;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public AssessTest getAssessTest() {
		return assessTest;
	}

	public void setAssessTest(AssessTest assessTest) {
		this.assessTest = assessTest;
	}

	public void setOsaEngineId(Long osaEngineId) {
		this.osaEngineId = osaEngineId;
	}

	public Set<FormDeliveryType> getFormDeliveryType() {
		return formDeliveryType;
	}

	public void setFormDeliveryType(Set<FormDeliveryType> formDeliveryType) {
		this.formDeliveryType = formDeliveryType;
	}
}
