package com.pearson.projectone.authcommons.config

import com.pearson.projectone.authcommons.service.token.impl.LocalAwareRemoteTokenServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.security.oauth2.provider.token.AccessTokenConverter
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices

/**
 * Provides base configurations for all resource servers. In our case, the global and customer server apps.
 * These apps need a sub class with the correct annotation to get the resource servers to be wired up.
 * For eg
 * <code>
 * @Configuration
 * @EnableResourceServer
 * @EnableGlobalMethodSecurity ( p r e P o s t E n a b l e d = true )
 * 		class CustomerResourceServerConfig extends AbstractResourceServerConfig {}* </code>
 */
abstract class AbstractResourceServerConfig extends DefaultGlobalMethodSecurityConfiguration {
	@Value('${config.oauth2.check_token_url}')
	String checkTokenUrl

	@Value('${config.oauth2.client_id}')
	String checkTokenClient

	@Value('${config.oauth2.client_secret}')
	String checkTokenClientSecret

	@Autowired
	AccessTokenConverter accessTokenConverter

	@Bean
	@Primary
	public ResourceServerTokenServices tokenServices() {
		def tokenServices = new LocalAwareRemoteTokenServiceImpl()
		tokenServices.checkTokenEndpointUrl = checkTokenUrl
		tokenServices.clientId = checkTokenClient
		tokenServices.clientSecret = checkTokenClientSecret
		tokenServices.accessTokenConverter = accessTokenConverter
		tokenServices
	}
}
