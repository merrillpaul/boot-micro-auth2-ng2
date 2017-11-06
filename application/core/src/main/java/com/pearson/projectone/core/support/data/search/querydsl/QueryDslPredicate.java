package com.pearson.projectone.core.support.data.search.querydsl;


import com.pearson.projectone.core.support.data.AbstractEntity;
import com.pearson.projectone.core.support.data.search.SearchCriteriaDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigInteger;

public class QueryDslPredicate<T extends AbstractEntity> {

	private final SearchCriteriaDTO criteria;
	private final Class<? extends T> type;

	public QueryDslPredicate(SearchCriteriaDTO criteria, Class<? extends T> type) {
		this.criteria = criteria;
		this.type = type;
	}

	public BooleanExpression toPredicate() {
		PathBuilder<T> entityPath = new PathBuilder<T>(this.type, "entity");
		Object value = criteria.getValue();
		if (NumberUtils.isNumber(value.toString())) {
			Number number = NumberUtils.createNumber(value.toString());

			switch (criteria.getOperation()) {

				case EQUALITY:
					if (number instanceof Integer) {
						return entityPath.getNumber(criteria.getKey(), Integer.class).eq(number.intValue());
					} else if (number instanceof BigInteger || number instanceof Long) {
						return entityPath.getNumber(criteria.getKey(), Long.class).eq(number.longValue());
					} else if (number instanceof Double || number instanceof Float) {
						return entityPath.getNumber(criteria.getKey(), Double.class).eq(number.doubleValue());
					} else if (number instanceof Short) {
						return entityPath.getNumber(criteria.getKey(), Short.class).eq(number.shortValue());
					}

				case NEGATION:
					if (number instanceof Integer) {
						return entityPath.getNumber(criteria.getKey(), Integer.class).ne(number.intValue());
					} else if (number instanceof BigInteger || number instanceof Long) {
						return entityPath.getNumber(criteria.getKey(), Long.class).ne(number.longValue());
					} else if (number instanceof Double || number instanceof Float) {
						return entityPath.getNumber(criteria.getKey(), Double.class).ne(number.doubleValue());
					} else if (number instanceof Short) {
						return entityPath.getNumber(criteria.getKey(), Short.class).ne(number.shortValue());
					}
				case GREATER_THAN:
					if (number instanceof Integer) {
						return entityPath.getNumber(criteria.getKey(), Integer.class).goe(number.intValue());
					} else if (number instanceof BigInteger || number instanceof Long) {
						return entityPath.getNumber(criteria.getKey(), Long.class).goe(number.longValue());
					} else if (number instanceof Double || number instanceof Float) {
						return entityPath.getNumber(criteria.getKey(), Double.class).goe(number.doubleValue());
					} else if (number instanceof Short) {
						return entityPath.getNumber(criteria.getKey(), Short.class).goe(number.shortValue());
					}
				case LESS_THAN:
					if (number instanceof Integer) {
						return entityPath.getNumber(criteria.getKey(), Integer.class).loe(number.intValue());
					} else if (number instanceof BigInteger || number instanceof Long) {
						return entityPath.getNumber(criteria.getKey(), Long.class).loe(number.longValue());
					} else if (number instanceof Double || number instanceof Float) {
						return entityPath.getNumber(criteria.getKey(), Double.class).loe(number.doubleValue());
					} else if (number instanceof Short) {
						return entityPath.getNumber(criteria.getKey(), Short.class).loe(number.shortValue());
					}
			}
		} else if (BooleanUtils.toBooleanObject(value.toString()) != null) {
			Boolean boolValue = BooleanUtils.toBooleanObject(value.toString());
			switch (criteria.getOperation()) {
				case EQUALITY:
					return entityPath.getBoolean(criteria.getKey()).eq(boolValue);
				case NEGATION:
					return entityPath.getBoolean(criteria.getKey()).ne(boolValue);
			}
		} else {
			StringPath path = entityPath.getString(criteria.getKey());
			switch (criteria.getOperation()) {
				case EQUALITY:
					return path.equalsIgnoreCase(criteria.getValue().toString());
				case NEGATION:
					return path.ne(criteria.getValue().toString());
				case GREATER_THAN:
					return path.goe(criteria.getValue().toString());
				case LESS_THAN:
					return path.loe(criteria.getValue().toString());
				case LIKE:
					return path.likeIgnoreCase(criteria.getValue().toString());
				case STARTS_WITH:
					return path.startsWithIgnoreCase(criteria.getValue().toString());
				case ENDS_WITH:
					return path.endsWithIgnoreCase(criteria.getValue().toString());
				case CONTAINS:
					return path.containsIgnoreCase(criteria.getValue().toString());
			}
		}
		return null;
	}
}
