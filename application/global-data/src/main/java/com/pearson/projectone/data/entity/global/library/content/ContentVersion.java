package com.pearson.projectone.data.entity.global.library.content;

import com.pearson.projectone.core.support.data.RelationalEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ContentVersion extends RelationalEntity {

	@Column
	private String pathRelativeToZipDir;

	@Column
	private String branch;

	@Column
	private String config;

	@Column
	private Date dateVersion;

	@Column
	private String hash;

	@Column
	private String originalFileName;

	@Column
	private String interfaceManifest;

	@Column
	private Long fileSize;

	@ManyToOne(fetch = FetchType.LAZY)
	private ContentType parent;

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

	public Date getDateVersion() {
		return dateVersion;
	}

	public void setDateVersion(Date dateVersion) {
		this.dateVersion = dateVersion;
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

	public ContentType getParent() {
		return parent;
	}

	public void setParent(ContentType parent) {
		this.parent = parent;
	}
}
