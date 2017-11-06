package com.pearson.projectone.global.dto.forms;

/**
 * This DTO as compared to FormDTO containes more fileds to map to Form Entity object.
 */
public class FormHeavyDTO {

	public FormHeavyDTO() {
		//default constructor
	}

	public FormHeavyDTO(String acronym, String assessmentId, String productCode, String description, String statusId,
	                    String formDefinition, String parentFormId, String dateCollectionExportDef, Long osaEngineId,
	                    Boolean groupAdministration, String formType, String assessTestId,
	                    String[] formDeliveryTypeIds) {
		this.acronym = acronym;
		this.assessmentId = assessmentId;
		this.productCode = productCode;
		this.description = description;
		this.statusId = statusId;
		this.formDefinition = formDefinition;
		this.parentFormId = parentFormId;
		this.dataCollectionExportDef = dateCollectionExportDef;
		this.osaEngineId = osaEngineId;
		this.groupAdministration = groupAdministration;
		this.formType = formType;
		this.assessTestId = assessTestId;
		this.formDeliveryTypeIds = formDeliveryTypeIds;
	}

	public FormHeavyDTO(String id, String acronym, String assessmentId, String productCode, String description,
	                    String statusId, String formDefinition, String parentFormId, String dateCollectionExportDef,
	                    Long osaEngineId, Boolean groupAdministration, String formType, String assessTestId,
	                    String[] formDeliveryTypeIds) {
		this(acronym, assessmentId, productCode, description, statusId, formDefinition, parentFormId,
				dateCollectionExportDef, osaEngineId, groupAdministration, formType, assessTestId, formDeliveryTypeIds);
		this.id = id;
	}

	private String id;
	private String acronym;
	private String assessmentId;
	private String productCode;
	private String description;
	private String statusId;
	private String formDefinition;
	private String parentFormId;
	private String dataCollectionExportDef;
	private Long osaEngineId;
	private Boolean groupAdministration;
	private String formType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String assessTestId;
	private String[] formDeliveryTypeIds;


	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

	public String getParentFormId() {
		return parentFormId;
	}

	public void setParentFormId(String parentFormId) {
		this.parentFormId = parentFormId;
	}

	public String getDataCollectionExportDef() {
		return dataCollectionExportDef;
	}

	public void setDataCollectionExportDef(String dataCollectionExportDef) {
		this.dataCollectionExportDef = dataCollectionExportDef;
	}

	public Long getOsaEngineId() {
		return osaEngineId;
	}

	public void setOsaEngineId(Long osaEngineId) {
		this.osaEngineId = osaEngineId;
	}

	public Boolean getGroupAdministration() {
		return groupAdministration;
	}

	public void setGroupAdministration(Boolean groupAdministration) {
		this.groupAdministration = groupAdministration;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getAssessTestId() {
		return assessTestId;
	}

	public void setAssessTestId(String assessTestId) {
		this.assessTestId = assessTestId;
	}

	public String[] getFormDeliveryTypeIds() {
		return formDeliveryTypeIds;
	}

	public void setFormDeliveryTypeIds(String[] formDeliveryTypeIds) {
		this.formDeliveryTypeIds = formDeliveryTypeIds;
	}
}
