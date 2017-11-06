package com.pearson.projectone.data.config

import com.pearson.projectone.core.support.data.utils.EnvironmentAwareMongoDbBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.MongoTemplate

/**
 * This abstract customer dao config bundles the customer location data repositories
 * together.
 * Just having this class in the classpath does not bootstrap this. This becomes active
 * only when an app, for eg in the auth-server or any app that needs this concern
 * includes a spring boot configuration class extending this class. This means, to bootstrap
 * we need to have a class like this:
 * <code>
 * package some.package.config
 *
 * import com.pearson.projectone.data.config.AbstractCustomerDaoConfig
 * import org.springframework.context.annotation.Configuration
 *
 * @Configuration
 * @EnableMongoRepositories ( basePackages = " com.pearson.projectone.data.dao.customer " , mongoTemplateRef = " customerMongoTemplate " )
 * class CustomerServerDaoConfig extends AbstractCustomerDaoConfig {
 *
 *}* </code>
 *
 * The application yml/properties expects a datasource configuration with customerDatasource prefix :
 * <code>
 *     	customerDatasource:
 * 			  mongo-uri: mongodb://customerdb_user:password1!@localhost:27017/customerdb
 * </code>
 */
abstract class AbstractCustomerDaoConfig {
	@Autowired
	EnvironmentAwareMongoDbBuilder environmentAwareMongoDbBuilder

	@Autowired
	ApplicationContext applicationContext

	@Bean(name = "customerMongoDSConfig")
	@ConfigurationProperties(prefix = "customerDatasource")
	public def prepareCustomerDataSourceConfigs() {
		[:]
	}

	@Bean(name = "customerMongoFactory")
	public MongoDbFactory customerMongoFactory() {
		def configs = prepareCustomerDataSourceConfigs()
		environmentAwareMongoDbBuilder.prepareMongoDataSource(configs)
	}

	@Bean(name = "customerMongoTemplate")
	@Primary
	public MongoTemplate customerMongoTemplate() {
		environmentAwareMongoDbBuilder.prepareMongoTemplate(customerMongoFactory())
	}
}
