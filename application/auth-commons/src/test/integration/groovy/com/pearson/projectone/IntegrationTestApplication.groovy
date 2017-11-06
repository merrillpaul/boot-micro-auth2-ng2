package com.pearson.projectone

import com.pearson.projectone.authcommons.service.token.Oauth2TokenStoreService
import com.pearson.projectone.authcommons.service.token.impl.DefaultTokenStoreServiceImpl
import com.pearson.projectone.authcommons.service.user.AuthServerUserDetailService
import com.pearson.projectone.authcommons.service.user.CurrentUserService
import com.pearson.projectone.authcommons.service.user.impl.AuthServerCurrentUserService
import com.pearson.projectone.authcommons.service.user.impl.AuthServerUserDetailsServiceImpl
import com.pearson.projectone.authcommons.tokenpublisher.TokenPublisher
import com.pearson.projectone.authcommons.tokenpublisher.impl.NoopTokenPublisher
import com.pearson.projectone.data.config.AbstractSecurityDaoConfig
import com.pearson.projectone.data.config.AbstractTokenStoreConfig
import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class IntegrationTestApplication {

	@Configuration
	@Profile("integration")
	@EnableMongoRepositories(basePackages = 'com.pearson.projectone.data.dao.security.oauth2.token',
			mongoTemplateRef = 'tokenStoreMongoTemplate')
	public class TestMongoConfiguration extends AbstractTokenStoreConfig {

		@Bean
		TokenPublisher tokenPublisher() {
			new NoopTokenPublisher()
		}
	}


	@Configuration
	@Profile("integration")
	@EnableJpaRepositories(
			basePackages = ['com.pearson.projectone.data.dao.security.user',
					'com.pearson.projectone.data.dao.security.oauth2.client'],
			entityManagerFactoryRef = 'appSecurityEntityManagerFactory',
			transactionManagerRef = 'appSecurityTransactionManager'
	)
	public class TestJpaConfiguration extends AbstractSecurityDaoConfig {

	}

	public class TestPasswordEncoder implements PasswordEncoder {
		@Override
		String encode(CharSequence rawPassword) {
			return "ENCODED > ${rawPassword}"
		}

		@Override
		boolean matches(CharSequence rawPassword, String encodedPassword) {
			return true
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		new TestPasswordEncoder()
	}

	@Bean
	@Profile("token")
	Oauth2TokenStoreService oauth2DynamoTokenStoreService() {
		new DefaultTokenStoreServiceImpl()
	}

	@Bean
	public AuthServerUserDetailService userDetailsService() {
		new AuthServerUserDetailsServiceImpl()
	}

	@Bean
	public CurrentUserService currentUserService() {
		new AuthServerCurrentUserService()
	}

	@Bean
	public ModelMapper modelMapper() {
		new ModelMapper()
	}

}