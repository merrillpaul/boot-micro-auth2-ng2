package com.pearson.projectone.global.dto.content;


import java.io.File;

public class GiveAllZipDTO {
	private File file;
	private String hash;

	public GiveAllZipDTO(File file, String hash) {
		this.file = file;
		this.hash = hash;
	}

	public File getFile() {
		return file;
	}

	public String getHash() {
		return hash;
	}
}
