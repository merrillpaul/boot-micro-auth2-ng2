package com.pearson.projectone.data.config

import com.pearson.projectone.core.support.data.utils.EnvironmentAwareDataSourceBuilder
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager

import javax.sql.DataSource

/**
 * This abstract security dao config bundles the user, role and oauth2 client repositories
 * together as they are of one schema and relational.
 * Just having this class in the classpath does not bootstrap this. This becomes active
 * only when an app, for eg in the auth-server or any app that needs this concern
 * includes a spring boot configuration class extending this class. This means, to bootstrap
 * we need to have a class like this:
 * <code>
 * package some.package.config
 *
 * import com.pearson.projectone.data.config.AbstractSecurityDaoConfig
 * import org.springframework.context.annotation.Configuration
 *
 * @Configuration
 * @EnableJpaRepositories (
 * basePackages = ['com.pearson.projectone.data.dao.security.user',
 * 				'com.pearson.projectone.data.dao.security.oauth2.client'],
 * 		entityManagerFactoryRef = 'appSecurityEntityManagerFactory',
 * 		transactionManagerRef = 'appSecurityTransactionManager'
 * )
 * class AuthServerDaoConfig extends AbstractSecurityDaoConfig {}* </code>
 *
 * The application yml/properties expects a datasource configuration with appSecurityDatasource prefix :
 * <code>
 * appSecurityDatasource:
 * 		jndiName: /some/jndi
 * </code>
 */
abstract class AbstractSecurityDaoConfig {

	private static final Log logger = LogFactory.getLog(AbstractSecurityDaoConfig)

	@Autowired
	EnvironmentAwareDataSourceBuilder environmentAwareDataSourceBuilder

	@Bean(name = "appSecurityDSConfig")
	@ConfigurationProperties(prefix = "appSecurityDatasource")
	public def prepareAppSecurityDataSourceConfigs() {
		[:]
	}

	@Bean(name = "appSecurityDataSource")
	@Primary
	public DataSource appSecurityDataSource() {
		logger.debug(">>>>>>>> Preparing App Security Datasource")
		environmentAwareDataSourceBuilder.prepareDataSource(prepareAppSecurityDataSourceConfigs())
	}

	@Bean(name = "appSecurityEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean appSecurityEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		builder.dataSource(appSecurityDataSource())
				.packages('com.pearson.projectone.data.entity.security.user',
				'com.pearson.projectone.data.entity.security.oauth2.client')
				.persistenceUnit("appSecurity")
				.build()
	}

	@Bean(name = "appSecurityTransactionManager")
	public PlatformTransactionManager appSecurityTransactionManager() {
		def transactionManager = new JpaTransactionManager()
		transactionManager.setEntityManagerFactory(appSecurityEntityManagerFactory().getObject())
		transactionManager
	}
}
