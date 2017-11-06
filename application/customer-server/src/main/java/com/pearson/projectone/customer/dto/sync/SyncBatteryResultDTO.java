package com.pearson.projectone.customer.dto.sync;

import java.util.Map;

public class SyncBatteryResultDTO {
	private String status;
	private Map<String, String> batteryStatus;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, String> getBatteryStatus() {
		return batteryStatus;
	}

	public void setBatteryStatus(Map<String, String> batteryStatus) {
		this.batteryStatus = batteryStatus;
	}
}
