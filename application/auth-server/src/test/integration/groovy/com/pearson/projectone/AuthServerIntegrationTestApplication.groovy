package com.pearson.projectone

import com.pearson.projectone.authcommons.provider.client.JpaClientDetailsService
import com.pearson.projectone.authcommons.provider.token.converters.AuthServerAccessTokenConverter
import com.pearson.projectone.authcommons.service.user.AuthServerUserDetailService
import com.pearson.projectone.authcommons.service.user.CurrentUserService
import com.pearson.projectone.authcommons.service.user.impl.AuthServerCurrentUserService
import com.pearson.projectone.authcommons.service.user.impl.AuthServerUserDetailsServiceImpl
import com.pearson.projectone.authcommons.tokenpublisher.TokenPublisher
import com.pearson.projectone.authcommons.tokenpublisher.impl.NoopTokenPublisher
import com.pearson.projectone.data.config.AbstractSecurityDaoConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.token.AccessTokenConverter

@SpringBootApplication
class AuthServerIntegrationTestApplication {

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
	public AccessTokenConverter accessTokenConverter() {
		new AuthServerAccessTokenConverter()
	}

	@Bean
	public ClientDetailsService clientDetailsService() {
		new JpaClientDetailsService()
	}

	@Bean
	public TokenPublisher tokenPublisher() {
		// TODO finalize and decide the mode of token distribution
		new NoopTokenPublisher()
	}

	@Bean
	public AuthServerUserDetailService userDetailsService() {
		new AuthServerUserDetailsServiceImpl()
	}

	@Bean
	public CurrentUserService currentUserService() {
		new AuthServerCurrentUserService()
	}

}