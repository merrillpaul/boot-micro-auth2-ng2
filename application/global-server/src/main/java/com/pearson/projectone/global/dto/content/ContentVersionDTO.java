package com.pearson.projectone.global.dto.content;


import com.pearson.projectone.core.support.data.BaseDTO;

public class ContentVersionDTO extends BaseDTO {
	private String pathRelativeToZipDir;
	private String branch;
	private String config;
	private String hash;
	private String originalFileName;
	private String interfaceManifest;
	private Long fileSize;
	private String contentTypeId;
	private String contentTypeName;

	public String getPathRelativeToZipDir() {
		return pathRelativeToZipDir;
	}

	public void setPathRelativeToZipDir(String pathRelativeToZipDir) {
		this.pathRelativeToZipDir = pathRelativeToZipDir;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getInterfaceManifest() {
		return interfaceManifest;
	}

	public void setInterfaceManifest(String interfaceManifest) {
		this.interfaceManifest = interfaceManifest;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(String contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getContentTypeName() {
		return contentTypeName;
	}

	public void setContentTypeName(String contentTypeName) {
		this.contentTypeName = contentTypeName;
	}
}
