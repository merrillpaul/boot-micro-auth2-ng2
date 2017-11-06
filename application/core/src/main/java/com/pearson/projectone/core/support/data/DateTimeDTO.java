package com.pearson.projectone.core.support.data;


public class DateTimeDTO extends DayDTO {
	private int hourOfDay;
	private int minuteOfHour;
	private int secondOfMinute;

	public int getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(int hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public int getMinuteOfHour() {
		return minuteOfHour;
	}

	public void setMinuteOfHour(int minuteOfHour) {
		this.minuteOfHour = minuteOfHour;
	}

	public int getSecondOfMinute() {
		return secondOfMinute;
	}

	public void setSecondOfMinute(int secondOfMinute) {
		this.secondOfMinute = secondOfMinute;
	}
}
