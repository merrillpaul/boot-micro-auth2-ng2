package com.pearson.projectone.core.support.hibernate

import org.apache.commons.lang3.StringUtils
import org.hibernate.boot.model.naming.Identifier
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment

/**
 * A strategy class to resolve table, sequence and column names and add underscores
 * to separate titles, instead of relying on default.
 * I.e The default in case of mysql hibernate dialect is to render titlecases (without underscores). To be
 * consistent, we want all db objects to have titles separated with underscores.
 * For eg, if an entity is :
 * <code>
 * @Entity
 * public class Oauth2AccessToken {
 *         private String columnnameWithInformation
 *}* </code>
 * This resolves to a table as:
 * <code>
 *     create table oauth2_access_token (
 *      columnname_with_information varchar(256)
 *     )
 * </code>
 * Also provides a
 * way to create custom abbreviations to lengthy names. I.e if we have a long entity name , but we
 * want to restrict the length by providing an abbreviation. For eg an entity
 * <code>
 * @Entity
 * public class AccountTemplate {}* </code>
 * This could be reduced to
 * acct_tpl if ABBREVIATIONS array provides the replacement for account and template
 *
 * To register this strategy, add this in the application.yml or properties as below:
 * <code>
 *     jpa:
 *     	properties:
 *     		hibernate:
 *     			physical_naming_strategy: com.pearson.projectone.core.support.hibernate.PhysicalNamingStrategyImpl
 * </code>
 */
public class PhysicalNamingStrategyImpl extends PhysicalNamingStrategyStandardImpl {

	// TODO add any abbreviations needed as we expand the project
	def abbreviations = [:]

	@Override
	Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment context) {
		def parts = splitAndReplace(name.text)
		// all sequences need 'seq' at the end
		if (!"seq".equalsIgnoreCase(parts.last())) {
			parts.add("seq");
		}
		context.identifierHelper.toIdentifier(join(parts), name.isQuoted())
	}

	@Override
	Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
		def parts = splitAndReplace(name.text)
		parts.removeAll("_")
		context.identifierHelper.toIdentifier(join(parts), name.isQuoted())
	}

	@Override
	Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {

		if (name.text.toLowerCase().startsWith("hibernate")) {
			return super.toPhysicalTableName(new Identifier(name.getText().toUpperCase(), name.isQuoted()), context)
		}
		def parts = splitAndReplace(name.text)
		context.identifierHelper.toIdentifier(join(parts), name.isQuoted())
	}

	def join(parts) {
		parts.join("_").toUpperCase()
	}

	def applyAbbreviationReplacement(String word) {
		if (abbreviations.containsKey(word)) {
			return abbreviations.get(word)
		}
		word
	}

	/**
	 * Splits a camelCase to [camel, case] and replaces any abbreviations as per company policy
	 * @param name
	 * @return
	 */
	def splitAndReplace(String name) {
		def parts = StringUtils.splitByCharacterTypeCamelCase(name).findAll {
			it != null && !it.trim().isEmpty()
		}.collect {
			applyAbbreviationReplacement(it.toLowerCase(Locale.ROOT))
		}
		parts = parts.findAll {
			it != '_'
		}
		parts
	}

}
