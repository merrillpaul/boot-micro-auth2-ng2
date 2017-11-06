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
 * This abstract token dao config bundles the oauth2 access and refresh token repositories
 * together as they are part of the dynamo db token schema
 * Just having this class in the classpath does not bootstrap this. This becomes active
 * only when an app, for eg in the auth-server or any app that needs this concern
 * includes a spring boot configuration class extending this class. This means, to bootstrap
 * we need to have a class like this:
 * <code>
 * package some.package.config
 *
 * import com.pearson.projectone.data.config.AbstractTokenStoreConfig
 * import org.springframework.context.annotation.Configuration
 *
 * @Configuration
 * @EnableMongoRepositories ( basePackages = "com.pearson.projectone.data.dao.security.oauth2.token", mongoTemplateRef = "tokenStoreMongoTemplate")
 * class AuthServerTokenDaoConfig extends AbstractTokenStoreConfig {}* </code>
 *
 * The application yml/properties expects a datasource configuration with tokenDatasource prefix :
 * <code>
 *     	tokenDatasource:
 * 			mongo-uri: mongodb://tokenstoredb_user:password1!@localhost:27017/tokenstoredb
 * </code>
 */
abstract class AbstractTokenStoreConfig {

	@Autowired
	EnvironmentAwareMongoDbBuilder environmentAwareMongoDbBuilder

	@Autowired
	ApplicationContext applicationContext

	@Bean(name = "tokenStoreMongoDSConfig")
	@ConfigurationProperties(prefix = "tokenDatasource")
	public def prepareTokenDataSourceConfigs() {
		[:]
	}

	@Bean(name = "tokenStoreMongoFactory")
	public MongoDbFactory tokenMongoFactory() {
		def configs = prepareTokenDataSourceConfigs()
		environmentAwareMongoDbBuilder.prepareMongoDataSource(configs)
	}

	@Bean(name = "tokenStoreMongoTemplate")
	@Primary
	public MongoTemplate tokenStoreMongoTemplate() {
		environmentAwareMongoDbBuilder.prepareMongoTemplate(tokenMongoFactory())
	}
}
