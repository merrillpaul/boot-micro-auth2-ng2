package com.pearson.projectone.customer.dto.sync;


import java.util.List;

public class SyncAssessmentDTO {
	private String administrationDate;
	private String id;
	private String title;
	private List<SyncBatteryDTO> battery;
	private boolean practiceMode;
	private SyncPatientDTO patient;
	private String testLocation;
	private List<String> examiners;
	private boolean hasObservations;
	private List<SyncGradeDTO> grades;

	public String getAdministrationDate() {
		return administrationDate;
	}

	public void setAdministrationDate(String administrationDate) {
		this.administrationDate = administrationDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SyncBatteryDTO> getBattery() {
		return battery;
	}

	public void setBattery(List<SyncBatteryDTO> battery) {
		this.battery = battery;
	}

	public boolean isPracticeMode() {
		return practiceMode;
	}

	public void setPracticeMode(boolean practiceMode) {
		this.practiceMode = practiceMode;
	}

	public SyncPatientDTO getPatient() {
		return patient;
	}

	public void setPatient(SyncPatientDTO patient) {
		this.patient = patient;
	}

	public String getTestLocation() {
		return testLocation;
	}

	public void setTestLocation(String testLocation) {
		this.testLocation = testLocation;
	}

	public List<String> getExaminers() {
		return examiners;
	}

	public void setExaminers(List<String> examiners) {
		this.examiners = examiners;
	}

	public boolean isHasObservations() {
		return hasObservations;
	}

	public void setHasObservations(boolean hasObservations) {
		this.hasObservations = hasObservations;
	}

	public List<SyncGradeDTO> getGrades() {
		return grades;
	}

	public void setGrades(List<SyncGradeDTO> grades) {
		this.grades = grades;
	}
}
