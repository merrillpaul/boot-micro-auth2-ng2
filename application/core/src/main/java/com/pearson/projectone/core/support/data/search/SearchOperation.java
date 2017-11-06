package com.pearson.projectone.core.support.data.search;

import java.io.Serializable;

/**
 * Inspired from http://www.baeldung.com/rest-api-query-search-language-more-operations
 */
public enum SearchOperation implements Serializable {
	EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, LIKE, STARTS_WITH, ENDS_WITH, CONTAINS;

	public static final String[] SIMPLE_OPERATION_SET = {"=", "!", ">", "<", "~", "*_", "_*", "_*_"};

	public static SearchOperation getSimpleOperation(String input) {
		switch (input) {
			case "=":
				return EQUALITY;
			case "!":
				return NEGATION;
			case ">":
				return GREATER_THAN;
			case "<":
				return LESS_THAN;
			case "~":
				return LIKE;
			case "*_":
				return STARTS_WITH;
			case "_*":
				return ENDS_WITH;
			case "_*_":
				return CONTAINS;
			default:
				return null;
		}
	}
}
