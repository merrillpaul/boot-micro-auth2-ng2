package com.pearson.projectone.authserver.config

import com.pearson.projectone.data.config.AbstractSecurityDaoConfig
import com.pearson.projectone.data.config.AbstractTokenStoreConfig
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

/**
 * wires up beans for auth dao's
 */
@Configuration
@EnableJpaRepositories(
		basePackages = ['com.pearson.projectone.data.dao.security.user',
				'com.pearson.projectone.data.dao.security.oauth2.client'],
		entityManagerFactoryRef = 'appSecurityEntityManagerFactory',
		transactionManagerRef = 'appSecurityTransactionManager'
)
class AuthDaoConfig extends AbstractSecurityDaoConfig {}


@Configuration
@EnableMongoRepositories(basePackages = 'com.pearson.projectone.data.dao.security.oauth2.token',
		mongoTemplateRef = 'tokenStoreMongoTemplate')
class TokenDaoConfig extends AbstractTokenStoreConfig {}
