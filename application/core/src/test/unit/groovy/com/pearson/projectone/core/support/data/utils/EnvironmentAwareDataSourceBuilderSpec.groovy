package com.pearson.projectone.core.support.data.utils

import com.pearson.projectone.core.support.dev.db.DbHelper
import org.springframework.context.ApplicationContext
import org.springframework.core.env.Environment
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup
import spock.lang.Specification
import spock.lang.Ignore

import javax.sql.DataSource
import java.sql.Connection
import java.sql.DatabaseMetaData

class EnvironmentAwareDataSourceBuilderSpec extends Specification {

	def "should load datasource from lookup if jndiName is provided"() {
		when:
		def properties = [
				jndiName: '/jndi/dummy'
		]
		def useLookup = false
		def lookup = Stub(JndiDataSourceLookup) {
			getDataSource(_) >> { arguments ->
				useLookup = true
				Stub(DataSource)
			}
		}
		def ds = new EnvironmentAwareDataSourceBuilder(
				dataSourceLookup: lookup,
				environment: Stub(Environment) {
					getActiveProfiles() >> {
						[]
					}
				}
		).prepareDataSource(properties)

		then:
		useLookup
	}


	def "should call clean up for dev profile"() {
		when:
		def properties = [
				jndiName: '/jndi/dummy'
		]
		def beanName
		def useLookup = false
		def cleanupCalled = false
		def applicationContext = Stub(ApplicationContext) {
			getBean(_) >> { arguments ->
				beanName = arguments[0]
				Stub(DbHelper) {
					cleanup(_) >> {
						cleanupCalled = true
					}
				}
			}
		}
		def metaData = Stub(DatabaseMetaData) {
			getDatabaseProductName() >> {
				'someDb'
			}
		}
		def connection = Stub(Connection) {
			getMetaData() >> metaData
		}


		def lookup = Stub(JndiDataSourceLookup) {
			getDataSource(_) >> { arguments ->
				Stub(DataSource) {
					getConnection() >> {
						connection
					}
				}
			}
		}

		def ds = new EnvironmentAwareDataSourceBuilder(
				applicationContext: applicationContext,
				dataSourceLookup: lookup,
				environment: Stub(Environment) {
					getActiveProfiles() >> {
						['dev']
					}
				}
		).prepareDataSource(properties)

		then:
		beanName == 'somedbHelper'
		cleanupCalled
	}

	@Ignore
	def "should call clean up for integration profile"() {
		when:
		def properties = [
				jndiName: '/jndi/dummy'
		]
		def beanName
		def useLookup = false
		def cleanupCalled = false
		def applicationContext = Stub(ApplicationContext) {
			getBean(_) >> { arguments ->
				beanName = arguments[0]
				Stub(DbHelper) {
					cleanup(_) >> {
						cleanupCalled = true
					}
				}
			}
		}
		def metaData = Stub(DatabaseMetaData) {
			getDatabaseProductName() >> {
				'someDb'
			}
		}
		def connection = Stub(Connection) {
			getMetaData() >> metaData
		}


		def lookup = Stub(JndiDataSourceLookup) {
			getDataSource(_) >> { arguments ->
				Stub(DataSource) {
					getConnection() >> {
						connection
					}
				}
			}
		}

		def ds = new EnvironmentAwareDataSourceBuilder(
				applicationContext: applicationContext,
				dataSourceLookup: lookup,
				environment: Stub(Environment) {
					getActiveProfiles() >> {
						['integration']
					}
				}
		).prepareDataSource(properties)

		then:
		beanName == 'somedbHelper'
		cleanupCalled
	}

	def "should NOT call clean up for dev profile if preserve data is true"() {
		when:
		def properties = [
				jndiName       : '/jndi/dummy',
				'preserve-data': true
		]
		def cleanupCalled = false
		def lookup = Stub(JndiDataSourceLookup) {
			getDataSource(_) >> { arguments ->
				Stub(DataSource)
			}
		}

		def ds = new EnvironmentAwareDataSourceBuilder(
				dataSourceLookup: lookup,
				environment: Stub(Environment) {
					getActiveProfiles() >> {
						['dev']
					}
				}
		).prepareDataSource(properties)

		then:
		!cleanupCalled
	}

	def "should NOT call clean up for non dev profile"() {
		when:
		def properties = [
				jndiName: '/jndi/dummy'
		]
		def cleanupCalled = false
		def lookup = Stub(JndiDataSourceLookup) {
			getDataSource(_) >> { arguments ->
				Stub(DataSource)
			}
		}

		def ds = new EnvironmentAwareDataSourceBuilder(
				dataSourceLookup: lookup,
				environment: Stub(Environment) {
					getActiveProfiles() >> {
						['prod']
					}
				}
		).prepareDataSource(properties)

		then:
		!cleanupCalled
	}
}
