package com.pearson.projectone

import com.pearson.projectone.core.support.data.mongodb.NoopMongoDBFactory
import com.pearson.projectone.data.config.AbstractGlobalDaoConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.mongodb.MongoDbFactory

@SpringBootApplication
class GlobalServerIntegrationTestApplication {

	@Configuration
	@Profile("integration")
	@EnableJpaRepositories(
			basePackages = ['com.pearson.projectone.data.dao.global'],
			entityManagerFactoryRef = 'globalEntityManagerFactory',
			transactionManagerRef = 'globalTransactionManager'
	)
	public class TestJpaConfiguration extends AbstractGlobalDaoConfig {

	}
}
