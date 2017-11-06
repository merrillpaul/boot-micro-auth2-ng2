package com.pearson.projectone.authcommons.service.token.impl

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.OAuth2Request
import org.springframework.security.oauth2.provider.token.RemoteTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import spock.lang.Specification

class LocalAwareRemoteTokenServiceImplSpec extends Specification {
	def authentication
	def authorities = [
			new SimpleGrantedAuthority("ROLE_ANON")
	]

	def setup() {
		def innerAuthentication = new AnonymousAuthenticationToken('test-key', 'testuser', authorities)
		def oauth2Request = new OAuth2Request([param1: '1'], 'client-id', authorities, true, ['read'].toSet(),
				['resource1'].toSet(), 'http://someredirect.com', ['implicit'].toSet(), [prop1: 'p1'])
		authentication = new OAuth2Authentication(oauth2Request, innerAuthentication)
	}

	def "should call remote token when local token store is not provided"() {
		given:
		def deletage = Stub(RemoteTokenServices) {
			loadAuthentication(_) >> {
				authentication
			}
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage)

		when:
		def result = service.loadAuthentication('tk-0001')

		then:
		result == authentication
	}

	def "should call remote token when local access token could not be found"() {
		given:
		def deletage = Stub(RemoteTokenServices) {
			loadAuthentication(_) >> {
				authentication
			}
		}
		def tokenStore = Stub(TokenStore) {
			readAccessToken(_) >> null
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage, tokenStore: tokenStore)

		when:
		def result = service.loadAuthentication('tk-0001')

		then:
		result == authentication
	}

	def "should remove access token from local store when token is expired and call remote service"() {
		given:
		def removeCalled = false
		def passedTokenId
		def accessToken = Spy(DefaultOAuth2AccessToken, constructorArgs: ['my-token']) {
			isExpired() >> true
		}
		def deletage = Stub(RemoteTokenServices) {
			loadAuthentication(_) >> {
				authentication
			}
		}
		def tokenStore = Stub(TokenStore) {
			readAccessToken(_) >> accessToken

			removeAccessToken(_) >> { OAuth2AccessToken _accessToken ->
				removeCalled = true
				passedTokenId = _accessToken.getValue()
			}
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage, tokenStore: tokenStore)

		when:
		def result = service.loadAuthentication('any')

		then:
		result == authentication
		removeCalled
		passedTokenId == 'my-token'
	}

	def "should load access token from local store"() {
		given:
		def removeCalled = false
		def passedTokenId
		def localAuthentication
		def accessToken = Spy(DefaultOAuth2AccessToken, constructorArgs: ['my-token']) {
			isExpired() >> false
		}
		def deletage = Stub(RemoteTokenServices) {
			loadAuthentication(_) >> {
				authentication
			}
		}
		def tokenStore = Stub(TokenStore) {
			readAccessToken(_) >> accessToken

			readAuthentication(_) >> { OAuth2AccessToken _accessToken ->
				def innerAuthentication = new AnonymousAuthenticationToken('test-key1', 'testuser_1', authorities)
				def oauth2Request = new OAuth2Request([param1: '1'], 'client-id1', authorities, true, ['read'].toSet(),
						['resource1'].toSet(), 'http://someredirect.com', ['implicit'].toSet(), [prop1: 'p1'])
				localAuthentication = new OAuth2Authentication(oauth2Request, innerAuthentication)
				localAuthentication
			}
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage, tokenStore: tokenStore)

		when:
		def result = service.loadAuthentication('any')

		then:
		result != authentication
		result == localAuthentication
	}

	def "should load remote authentication if access token from local store is not found"() {
		given:
		def removeCalled = false
		def passedTokenId
		def localAuthentication
		def accessToken = Spy(DefaultOAuth2AccessToken, constructorArgs: ['my-token']) {
			isExpired() >> false
		}
		def deletage = Stub(RemoteTokenServices) {
			loadAuthentication(_) >> {
				authentication
			}
		}
		def tokenStore = Stub(TokenStore) {
			readAccessToken(_) >> accessToken

			readAuthentication(_) >> { OAuth2AccessToken _accessToken ->
				null
			}
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage, tokenStore: tokenStore)

		when:
		def result = service.loadAuthentication('any')

		then:
		result == authentication
	}

	def "should call delegate's rest template"() {
		given:
		def delegateCalled = false
		def deletage = Stub(RemoteTokenServices) {
			setRestTemplate(_) >> {
				delegateCalled = true
			}
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage)

		when:
		service.setRestTemplate(null) // doesn't matter what the arg is

		then:
		delegateCalled
	}

	def "should call delegate's checkend point url"() {
		given:
		def delegateCalled = false
		def deletage = Stub(RemoteTokenServices) {
			setCheckTokenEndpointUrl(_) >> {
				delegateCalled = true
			}
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage)

		when:
		service.setCheckTokenEndpointUrl(null) // doesn't matter what the arg is

		then:
		delegateCalled
	}

	def "should call delegate's client id"() {
		given:
		def delegateCalled = false
		def deletage = Stub(RemoteTokenServices) {
			setClientId(_) >> {
				delegateCalled = true
			}
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage)

		when:
		service.setClientId(null) // doesn't matter what the arg is

		then:
		delegateCalled
	}

	def "should call delegate's client secret"() {
		given:
		def delegateCalled = false
		def deletage = Stub(RemoteTokenServices) {
			setClientSecret(_) >> {
				delegateCalled = true
			}
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage)

		when:
		service.setClientSecret(null) // doesn't matter what the arg is

		then:
		delegateCalled
	}

	def "should call delegate's setAccessTokenConverter"() {
		given:
		def delegateCalled = false
		def deletage = Stub(RemoteTokenServices) {
			setAccessTokenConverter(_) >> {
				delegateCalled = true
			}
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage)

		when:
		service.setAccessTokenConverter(null) // doesn't matter what the arg is

		then:
		delegateCalled
	}

	def "should call delegate's setTokenName"() {
		given:
		def delegateCalled = false
		def deletage = Stub(RemoteTokenServices) {
			setTokenName(_) >> {
				delegateCalled = true
			}
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage)

		when:
		service.setTokenName(null) // doesn't matter what the arg is

		then:
		delegateCalled
	}

	def "should call delegate's read access token"() {
		given:
		def delegateCalled = false
		def accessToken = Spy(DefaultOAuth2AccessToken, constructorArgs: ['my-token1']) {
			isExpired() >> false
		}
		def deletage = Stub(RemoteTokenServices) {
			readAccessToken(_) >> {
				delegateCalled = true
				accessToken
			}
		}
		def service = new LocalAwareRemoteTokenServiceImpl(delegate: deletage)

		when:
		def res = service.readAccessToken(null) // doesn't matter what the arg is

		then:
		delegateCalled
		res.value == 'my-token1'
	}
}
