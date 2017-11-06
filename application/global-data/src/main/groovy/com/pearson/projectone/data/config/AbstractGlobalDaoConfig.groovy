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
 * This abstract global dao config bundles the global repositories such as account, assessments, tests etc
 * together as they are of one schema and relational.
 * Just having this class in the classpath does not bootstrap this. This becomes active
 * only when an app, for eg in the auth-server or any app that needs this concern
 * includes a spring boot configuration class extending this class. This means, to bootstrap
 * we need to have a class like this:
 * <code>
 * package some.package.config
 *
 * import com.pearson.projectone.data.config.AbstractGlobalDaoConfig
 * import org.springframework.context.annotation.Configuration
 *
 * @Configuration
 * @EnableJpaRepositories (
 * basePackages = ['com.pearson.projectone.data.dao.global'],
 * 		entityManagerFactoryRef = 'globalEntityManagerFactory',
 * 		transactionManagerRef = 'globalTransactionManager'
 * )
 * class GlobalServerDaoConfig extends AbstractGlobalDaoConfig {}* </code>
 *
 * The application yml/properties expects a datasource configuration with globalDatasource prefix :
 * <code>
 * globalDatasource:
 * 		jndiName: /some/jndi
 * </code>
 */
abstract class AbstractGlobalDaoConfig {

	private static final Log logger = LogFactory.getLog(AbstractGlobalDaoConfig)

	@Autowired
	EnvironmentAwareDataSourceBuilder environmentAwareDataSourceBuilder

	@Bean(name = "globalDSConfig")
	@ConfigurationProperties(prefix = "globalDatasource")
	public def prepareGlobalDataSourceConfigs() {
		[:]
	}

	@Bean(name = "globalDataSource")
	@Primary
	public DataSource globalDataSource() {
		logger.debug(">>>>>>>> Preparing Global schema Datasource")
		environmentAwareDataSourceBuilder.prepareDataSource(prepareGlobalDataSourceConfigs())
	}

	@Bean(name = "globalEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean globalEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		builder.dataSource(globalDataSource())
				.packages('com.pearson.projectone.data.entity.global')
				.persistenceUnit("global")
				.build()
	}

	@Bean(name = "globalTransactionManager")
	public PlatformTransactionManager globalTransactionManager() {
		def transactionManager = new JpaTransactionManager()
		transactionManager.setEntityManagerFactory(globalEntityManagerFactory().getObject())
		transactionManager
	}
}
