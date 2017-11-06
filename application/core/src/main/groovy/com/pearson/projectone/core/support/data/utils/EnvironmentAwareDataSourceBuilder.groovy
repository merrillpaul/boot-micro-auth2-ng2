package com.pearson.projectone.core.support.data.utils

import com.pearson.projectone.core.support.dev.db.DbHelper
import com.pearson.projectone.core.utils.DataBinderUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.context.ApplicationContext
import org.springframework.core.env.Environment
import org.springframework.jdbc.datasource.lookup.DataSourceLookup
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup
import org.springframework.stereotype.Component

import javax.sql.DataSource
import java.sql.Connection

/**
 * A helper to provide a datasource which knows about the environment.
 * If the app is executed on local dev ( intelliJ ), this cleans loads the datasource based
 * on the properties provided and cleans it. There are instances when multiple applications , auth server
 * and global server accessed the same server . In that case we always run auth_server in recreate mode
 * and all other servers to preserve the data.
 * A typical example of the dev datasource properties in yml format:
 * <code>
 *     	userDatasource:
 * 			driver-class-name: com.mysql.jdbc.Driver
 * 			url: jdbc:mysql://localhost:3306/oauth_example
 * 			username: scott
 * 			password: tiger
 * 			preserve-data: true[false]
 * </code>
 *
 * In higher environments, this just passes through to resolve a datasource based on the configuration, which
 * typically would be JNDI. A typical example of the spring boot properties in yml:
 * <code>
 * 		commonDatasource:
 * 			jndiName: java:comp/env/jdbc/common
 * </code>
 */
@Component
class EnvironmentAwareDataSourceBuilder {

	@Autowired
	Environment environment

	@Autowired
	ApplicationContext applicationContext

	DataSourceLookup dataSourceLookup = new JndiDataSourceLookup()

	def DataSource prepareDataSource(def properties) {
		DataSource dataSource = null
		if (properties['jndiName']) {
			dataSource = dataSourceLookup.getDataSource(properties['jndiName'])
		} else {
			dataSource = DataSourceBuilder.create().build()
			DataBinderUtils.populateObjectFromProperties(dataSource, properties)
		}

		def preserveData = properties['preserve-data']
		if (!preserveData && environment.activeProfiles.contains('dev')) {
			Connection connection
			try {
				connection = dataSource.connection
				DbHelper helper = applicationContext
						.getBean("${connection.metaData.databaseProductName.toLowerCase()}Helper")
				helper.cleanup(connection)
			} catch (e) {
				e.printStackTrace()
			} finally {
				if (connection) {
					try {
						connection.close()
					} catch (e) {
						e.printStackTrace()
					}
				}
			}
		}

		dataSource
	}


}
