package com.pearson.projectone.authserver.config

import com.pearson.projectone.authcommons.Constants
import org.junit.Rule
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.token.AccessTokenConverter
import org.springframework.security.oauth2.provider.token.TokenStore
import spock.lang.Specification

import static org.mockito.Matchers.*
import static org.mockito.Mockito.when
import static org.powermock.api.mockito.PowerMockito.mock

@PrepareForTest([AuthorizationServerSecurityConfigurer, AuthorizationServerEndpointsConfigurer,
		ClientDetailsServiceConfigurer])
class Oauth2ConfigSpec extends Specification {

	@Rule
	PowerMockRule mockRule = new PowerMockRule()

	def "should configure authorization security server"() {
		given:
		def passedRealm
		def passedTokenAccess
		def passedCheckAssess
		def oauthServerConfigurer
		oauthServerConfigurer = mock(AuthorizationServerSecurityConfigurer)
		when(oauthServerConfigurer.realm(anyString())).thenAnswer(closurable { args ->
			passedRealm = args[0].toString()
			oauthServerConfigurer
		})
		when(oauthServerConfigurer.tokenKeyAccess(anyString())).thenAnswer(closurable { args ->
			passedTokenAccess = args[0].toString()
			oauthServerConfigurer
		})
		when(oauthServerConfigurer.checkTokenAccess(anyString())).thenAnswer(closurable { args ->
			passedCheckAssess = args[0].toString()
			oauthServerConfigurer
		})

		def config = new Oauth2Config()

		when:
		config.configure(oauthServerConfigurer)

		then:
		passedRealm == Constants.REALM.code
		passedTokenAccess == 'permitAll()'
		passedCheckAssess == 'isAuthenticated()'
	}


	def "should configure authorization endpoint"() {
		given:
		def passedTokenStore
		def passedAuthenticationManager
		def passedTokenConverter
		def passedRefreshTokens
		def endpoints
		endpoints = mock(AuthorizationServerEndpointsConfigurer)
		when(endpoints.tokenStore(any())).thenAnswer(closurable { args ->
			passedTokenStore = args[0]
			endpoints
		})
		when(endpoints.authenticationManager(any())).thenAnswer(closurable { args ->
			passedAuthenticationManager = args[0]
			endpoints
		})
		when(endpoints.accessTokenConverter(any())).thenAnswer(closurable { args ->
			passedTokenConverter = args[0]
			endpoints
		})
		when(endpoints.reuseRefreshTokens(anyBoolean())).thenAnswer(closurable { args ->
			passedRefreshTokens = args[0]
			endpoints
		})

		def tokenStore = Stub(TokenStore)
		def tokenConverter = Stub(AccessTokenConverter)
		def authenticationManager = new AuthenticationManager() {
			@Override
			Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return null
			}
		}
		def refreshTokens = true
		def config = new Oauth2Config(refreshTokens: refreshTokens, tokenStore: tokenStore,
				authenticationManager: authenticationManager, accessTokenConverter: tokenConverter)

		when:
		config.configure(endpoints)

		then:
		passedTokenStore == tokenStore
		passedAuthenticationManager == authenticationManager
		passedTokenConverter == tokenConverter
		passedRefreshTokens
	}

	def "should configure oauth2 client details service"() {
		given:
		def passedClientService
		def clientConfigurer
		clientConfigurer = mock(ClientDetailsServiceConfigurer)
		when(clientConfigurer.withClientDetails(any())).thenAnswer(closurable { args ->
			passedClientService = args[0]
			null
		})
		def clientService = Stub(ClientDetailsService)
		def config = new Oauth2Config(clientDetailsService: clientService)

		when:
		config.configure(clientConfigurer)

		then:
		passedClientService == clientService
	}


	static def Answer closurable(Closure c) {
		new Answer() {
			@Override
			Object answer(InvocationOnMock invocation) throws Throwable {
				def result = c(invocation.arguments)
				result
			}
		}
	}
}
