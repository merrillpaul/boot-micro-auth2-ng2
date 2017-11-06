package com.pearson.projectone.data.entity.customer.assess;

import com.pearson.projectone.core.support.data.DocumentEntity;
import com.pearson.projectone.core.support.data.mongodb.Json;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "QiAssessmentSubtest")
public class QiAssessmentSubtest extends DocumentEntity {

	private String qiAssessmentId;

	private String assessSubtestId;

	private String subtestInstanceId;

	private Integer orderId;

	@Json
	private String subtestJson;

	private BigDecimal completionTime;

	private boolean wasStarted;

	private Date dateWeSetStartTime;

	private BigDecimal usage;

	public String getQiAssessmentId() {
		return qiAssessmentId;
	}

	public void setQiAssessmentId(String qiAssessmentId) {
		this.qiAssessmentId = qiAssessmentId;
	}

	public String getAssessSubtestId() {
		return assessSubtestId;
	}

	public void setAssessSubtestId(String assessSubtestId) {
		this.assessSubtestId = assessSubtestId;
	}

	public String getSubtestInstanceId() {
		return subtestInstanceId;
	}

	public void setSubtestInstanceId(String subtestInstanceId) {
		this.subtestInstanceId = subtestInstanceId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getSubtestJson() {
		return subtestJson;
	}

	public void setSubtestJson(String subtestJson) {
		this.subtestJson = subtestJson;
	}

	public BigDecimal getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(BigDecimal completionTime) {
		this.completionTime = completionTime;
	}

	public boolean isWasStarted() {
		return wasStarted;
	}

	public void setWasStarted(boolean wasStarted) {
		this.wasStarted = wasStarted;
	}

	public Date getDateWeSetStartTime() {
		return dateWeSetStartTime;
	}

	public void setDateWeSetStartTime(Date dateWeSetStartTime) {
		this.dateWeSetStartTime = dateWeSetStartTime;
	}

	public BigDecimal getUsage() {
		return usage;
	}

	public void setUsage(BigDecimal usage) {
		this.usage = usage;
	}
}
