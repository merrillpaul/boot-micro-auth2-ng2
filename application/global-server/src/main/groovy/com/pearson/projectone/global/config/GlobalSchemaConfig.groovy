package com.pearson.projectone.global.config

import com.pearson.projectone.data.config.AbstractGlobalDaoConfig
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(
		basePackages = ['com.pearson.projectone.data.dao.global'],
		entityManagerFactoryRef = 'globalEntityManagerFactory',
		transactionManagerRef = 'globalTransactionManager'
)
class GlobalSchemaConfig extends AbstractGlobalDaoConfig {
}
