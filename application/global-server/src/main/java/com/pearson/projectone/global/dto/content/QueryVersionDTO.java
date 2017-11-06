package com.pearson.projectone.global.dto.content;

import java.util.List;

public class QueryVersionDTO {
	private String status;

	private List<String> missing;

	private String hash;

	private Long size;

	private String url;

	private String path;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getMissing() {
		return missing;
	}

	public void setMissing(List<String> missing) {
		this.missing = missing;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
