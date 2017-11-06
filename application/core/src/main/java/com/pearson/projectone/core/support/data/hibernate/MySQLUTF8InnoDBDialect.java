package com.pearson.projectone.core.support.data.hibernate;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * This is so that DDLs for tables created have charset as UTF8 to accomodate for i18n
 */
public class MySQLUTF8InnoDBDialect extends MySQL5InnoDBDialect {
	@Override
	public String getTableTypeString() {
		return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
	}
}
