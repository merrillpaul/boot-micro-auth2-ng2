package com.pearson.projectone.customer

import com.pearson.projectone.authcommons.provider.token.converters.ResourceServerAccessTokenConverter
import com.pearson.projectone.authcommons.service.user.CurrentUserService
import com.pearson.projectone.authcommons.service.user.impl.ResourceCurrentUserService
import com.pearson.projectone.core.support.data.mongodb.repository.support.DefaultMongoRepositoryFactoryBean
import com.pearson.projectone.data.config.AbstractCustomerDaoConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.security.oauth2.provider.token.AccessTokenConverter

@SpringBootApplication
class CustomerServerIntegrationSpecApplication {

	@Configuration
	@EnableMongoRepositories(basePackages = "com.pearson.projectone.data.dao.customer",
			repositoryFactoryBeanClass = DefaultMongoRepositoryFactoryBean,
			mongoTemplateRef = "customerMongoTemplate")
	class TestCustomerSchemaConfig extends AbstractCustomerDaoConfig {
	}

	@Bean
	public CurrentUserService currentUserService() {
		new ResourceCurrentUserService()
	}

	@Bean
	public AccessTokenConverter accessTokenConverter() {
		new ResourceServerAccessTokenConverter()
	}
}
