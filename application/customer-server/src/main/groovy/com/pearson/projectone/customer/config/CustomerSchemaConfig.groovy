package com.pearson.projectone.customer.config

import com.pearson.projectone.core.support.data.mongodb.repository.support.DefaultMongoRepositoryFactoryBean
import com.pearson.projectone.data.config.AbstractCustomerDaoConfig
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = "com.pearson.projectone.data.dao.customer",
		repositoryFactoryBeanClass = DefaultMongoRepositoryFactoryBean,
		mongoTemplateRef = "customerMongoTemplate")
class CustomerSchemaConfig extends AbstractCustomerDaoConfig {
}
