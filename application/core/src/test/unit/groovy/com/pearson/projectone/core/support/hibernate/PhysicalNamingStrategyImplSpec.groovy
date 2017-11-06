package com.pearson.projectone.core.support.hibernate

import org.hibernate.boot.model.naming.Identifier
import org.hibernate.engine.jdbc.env.spi.IdentifierHelper
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment
import spock.lang.Specification

class PhysicalNamingStrategyImplSpec extends Specification {

	def jdbcEnvironment
	def identifierHelper
	def identifier

	def setup() {
		identifierHelper = Stub(IdentifierHelper) {
			toIdentifier(_, _) >> { String text, boolean quoted ->
				identifier = Identifier.toIdentifier(text)
				identifier
			}
		}
		jdbcEnvironment = Stub(JdbcEnvironment) {
			getIdentifierHelper() >> identifierHelper
		}
	}

	def "should underscorify titlecase entity names for tables"() {
		when:
		def strategy = new PhysicalNamingStrategyImpl()
		def processIdentifier = strategy.toPhysicalTableName(Identifier.toIdentifier("AppUser"), jdbcEnvironment)

		then:
		processIdentifier.text == 'APP_USER'
	}

	def "should underscorify multiple titlecase entity names for tables"() {
		when:
		def strategy = new PhysicalNamingStrategyImpl()
		def processIdentifier = strategy.toPhysicalTableName(Identifier.toIdentifier("AppUserInformationTables"), jdbcEnvironment)

		then:
		processIdentifier.text == 'APP_USER_INFORMATION_TABLES'
	}

	def "should NOT modify internal hibernate tables "() {
		when:
		def strategy = new PhysicalNamingStrategyImpl()
		def processIdentifier = strategy.toPhysicalTableName(Identifier.toIdentifier("HibernateSequence"), jdbcEnvironment)

		then:
		processIdentifier.text == 'HIBERNATESEQUENCE'
	}

	def "should underscorify titlecase for column names"() {
		when:
		def strategy = new PhysicalNamingStrategyImpl()
		def processIdentifier = strategy.toPhysicalColumnName(Identifier.toIdentifier("accountEnabledToForce"), jdbcEnvironment)

		then:
		processIdentifier.text == 'ACCOUNT_ENABLED_TO_FORCE'
	}

	def "should underscorify titlecase for sequence names"() {
		when:
		def strategy = new PhysicalNamingStrategyImpl()
		def processIdentifier = strategy.toPhysicalSequenceName(Identifier.toIdentifier("MyTestSequence"), jdbcEnvironment)

		then:
		processIdentifier.text == 'MY_TEST_SEQUENCE_SEQ' // all sequences would add _seq
	}

	def "should replace abbreviations if any"() {
		when:
		def strategy = new PhysicalNamingStrategyImpl(
				abbreviations: [account: 'act', trigger: 'trg', quartz: 'qrt'])
		def tableIdentifier = strategy.toPhysicalTableName(Identifier.toIdentifier("AccountUserInformationTables"), jdbcEnvironment)
		def columnIdentifier = strategy.toPhysicalColumnName(Identifier.toIdentifier("applicationTriggerToRun"), jdbcEnvironment)
		def seqIdentifier = strategy.toPhysicalSequenceName(Identifier.toIdentifier("QuartzTriggers"), jdbcEnvironment)

		then:
		tableIdentifier.text == 'ACT_USER_INFORMATION_TABLES'
		columnIdentifier.text == 'APPLICATION_TRG_TO_RUN'
		seqIdentifier.text == 'QRT_TRIGGERS_SEQ'
	}

	def "should NOT add redundant underscores for an already underscored separated names for tables"() {
		when:
		def strategy = new PhysicalNamingStrategyImpl()
		def processIdentifier = strategy.toPhysicalTableName(Identifier.toIdentifier("business_unit_assess_tests"), jdbcEnvironment)

		then:
		processIdentifier.text == 'BUSINESS_UNIT_ASSESS_TESTS'
	}

}
