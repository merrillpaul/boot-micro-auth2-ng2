package com.pearson.projectone.core.utils;

import org.springframework.core.NestedCheckedException;

public class CheckedException extends NestedCheckedException {
	public CheckedException(String msg) {
		super(msg);
	}

	public CheckedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
