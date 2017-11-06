package com.pearson.projectone.data.entity.customer.examinee.assessment;


public enum ExamineeAssessmentStatus {

	READY_FOR_ADMINSTRATION("ASSESSMENT_STATUS.Ready.for.Administration"),
	IN_PROGRESS("ASSESSMENT_STATUS.In.Progress"),
	NEEDS_EDITING("ASSESSMENT_STATUS.Needs.Editing"),
	REPORT_GENERATED("ASSESSMENT_STATUS.Report.Generated"),
	EXPIRED("ASSESSMENT_STATUS.Expired"),
	NEEDS_VERIFICATION("ASSESSMENT_STATUS.Needs.Verification"),
	READY_FOR_REPORTING("ASSESSMENT_STATUS.Ready.for.Reporting"),
	RETIRED("ASSESSMENT_STATUS.Retired"),
	WAITING_FOR_PRS_RESULT("ASSESSMENT_STATUS.Waiting.For.PRS.Results"),
	PREPARATION("ASSESSMENT_STATUS.Preparation"),
	PENDING("ASSESSMENT_STATUS.Pending"),
	GIVE("ASSESSMENT_STATUS.Give"),
	COMPLETE("ASSESSMENT_STATUS.Complete"),
	ARCHIVED("ASSESSMENT_STATUS.Archived");

	private String name;

	private ExamineeAssessmentStatus(final String name) {
		this.name = name;
	}

	public boolean equalsName(String otherName) {
		return this.name.equals(otherName);
	}

	public String toString() {
		return this.name;
	}
}
