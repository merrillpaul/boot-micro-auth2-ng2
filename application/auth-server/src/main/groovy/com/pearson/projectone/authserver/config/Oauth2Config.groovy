package com.pearson.projectone.authserver.config

import com.pearson.projectone.authcommons.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.token.AccessTokenConverter
import org.springframework.security.oauth2.provider.token.TokenStore

@Configuration
@EnableAuthorizationServer
class Oauth2Config extends AuthorizationServerConfigurerAdapter {
	@Autowired
	AuthenticationManager authenticationManager

	@Value('${config.refreshTokens}')
	boolean refreshTokens

	@Autowired
	TokenStore tokenStore

	@Autowired
	AccessTokenConverter accessTokenConverter

	@Autowired
	ClientDetailsService clientDetailsService

	@Override
	public void configure(
			AuthorizationServerSecurityConfigurer oauthServer)
			throws Exception {
		oauthServer.realm(Constants.REALM.code)
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()")
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
				.accessTokenConverter(accessTokenConverter)
				.reuseRefreshTokens(refreshTokens)
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService)
	}
}
