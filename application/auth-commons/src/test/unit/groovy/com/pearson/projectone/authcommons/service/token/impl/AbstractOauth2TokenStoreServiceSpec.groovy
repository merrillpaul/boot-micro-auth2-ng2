package com.pearson.projectone.authcommons.service.token.impl

import com.pearson.projectone.authcommons.tokenpublisher.TokenPublisher
import com.pearson.projectone.authcommons.utils.Oauth2SerializationUtils
import com.pearson.projectone.data.entity.security.oauth2.token.AccessToken
import com.pearson.projectone.data.entity.security.oauth2.token.RefreshToken
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.OAuth2Request
import spock.lang.Specification

class AbstractOauth2TokenStoreServiceSpec extends Specification {

	def publisher
	def publisherCalled = false
	def authorities = [
			new SimpleGrantedAuthority("ROLE_ANON")
	]

	def setup() {
		publisherCalled = false
	}


	def "should call token publisher save token if in auth server"() {
		given:
		def principalObj
		def accessTokenObj
		publisher = Stub(TokenPublisher) {
			saveAccessToken(_, _) >> { accessToken, principal ->
				principalObj = principal
				accessTokenObj = accessToken
				publisherCalled = true
			}
		}
		def accessToken = new AccessToken(tokenId: 'TK00001')
		def tokenStoreService = Spy(AbstractOauth2TokenStoreService) {
			persistAccessToken(_) >> {
				accessToken
			}
		}
		tokenStoreService.authServerMode = true
		tokenStoreService.tokenPublisher = publisher

		when:
		tokenStoreService.saveToken(accessToken, createAuthentication())

		then:
		principalObj == "testuser"
		accessTokenObj == accessToken
		publisherCalled
	}

	def "should NOT call token publisher save if not in auth server mode"() {
		given:
		publisher = Stub(TokenPublisher) {
			saveAccessToken(_, _) >> { accessToken, principal ->
				publisherCalled = true
			}
		}
		def accessToken = new AccessToken(tokenId: 'TK00001')
		def tokenStoreService = Spy(AbstractOauth2TokenStoreService) {
			persistAccessToken(_) >> {
				accessToken
			}
		}
		tokenStoreService.authServerMode = false
		tokenStoreService.tokenPublisher = publisher

		when:
		tokenStoreService.saveToken(accessToken, createAuthentication())

		then:
		!publisherCalled
	}

	def "should deserialize the authentication and pass it to publisher for removing access token"() {
		given:
		def principalObj
		def accessTokenObj
		publisher = Stub(TokenPublisher) {
			deleteAccessToken(_, _) >> { accessToken, principal ->
				principalObj = principal
				accessTokenObj = accessToken
				publisherCalled = true
			}
		}
		def authentication = createAuthentication()
		def accessToken = new AccessToken(tokenId: 'TK00001',
				authentication: Oauth2SerializationUtils.serializeAuthentication(authentication))
		def tokenStoreService = Spy(AbstractOauth2TokenStoreService) {
			getAccessToken(_) >> {
				accessToken
			}
			removeAccessToken(_) >> {}
		}
		tokenStoreService.authServerMode = true
		tokenStoreService.tokenPublisher = publisher

		when:
		tokenStoreService.deleteAccessToken(accessToken.tokenId)

		then:
		principalObj == "testuser"
		publisherCalled
	}

	def "should call token publisher save refresh token if in auth server"() {
		given:
		def principalObj
		def refreshTokenObj
		publisher = Stub(TokenPublisher) {
			saveRefreshToken(_, _) >> { refreshToken, principal ->
				principalObj = principal
				refreshTokenObj = refreshToken
				publisherCalled = true
			}
		}
		def refreshToken = new RefreshToken(tokenId: 'RFK0001')
		def tokenStoreService = Spy(AbstractOauth2TokenStoreService) {
			persistRefreshToken(_) >> {
				refreshToken
			}
		}
		tokenStoreService.authServerMode = true
		tokenStoreService.tokenPublisher = publisher

		when:
		tokenStoreService.saveToken(refreshToken, createAuthentication())

		then:
		principalObj == "testuser"
		refreshTokenObj == refreshToken
		publisherCalled
	}

	def "should deserialize the authentication and pass it to publisher for removing refresh token"() {
		given:
		def principalObj
		def refreshTokenObj
		publisher = Stub(TokenPublisher) {
			deleteRefreshToken(_, _) >> { refreshToken, principal ->
				principalObj = principal
				refreshTokenObj = refreshToken
				publisherCalled = true
			}
		}
		def authentication = createAuthentication()
		def refreshToken = new RefreshToken(tokenId: 'TK00001',
				authentication: Oauth2SerializationUtils.serializeAuthentication(authentication))
		def tokenStoreService = Spy(AbstractOauth2TokenStoreService) {
			getRefreshToken(_) >> {
				refreshToken
			}
			removeRefreshToken(_) >> {}
		}
		tokenStoreService.authServerMode = true
		tokenStoreService.tokenPublisher = publisher

		when:
		tokenStoreService.deleteRefreshToken(refreshToken.tokenId)

		then:
		principalObj == "testuser"
		publisherCalled
	}

	def "should deserialize the authentication and pass it to publisher for removing access token with refresh token"() {
		given:
		def principalObj
		def refreshTokenObj
		publisher = Stub(TokenPublisher) {
			deleteAccessTokenByRefreshToken(_, _) >> { refreshToken, principal ->
				principalObj = principal
				refreshTokenObj = refreshToken
				publisherCalled = true
			}
		}
		def authentication = createAuthentication()
		def refreshToken = new RefreshToken(tokenId: 'TK00001',
				authentication: Oauth2SerializationUtils.serializeAuthentication(authentication))
		def tokenStoreService = Spy(AbstractOauth2TokenStoreService) {
			getRefreshToken(_) >> {
				refreshToken
			}
			removeAccessTokenWithRefreshTokenKey(_) >> {}
		}
		tokenStoreService.authServerMode = true
		tokenStoreService.tokenPublisher = publisher

		when:
		tokenStoreService.deleteAccessTokenWithRefreshToken(refreshToken.tokenId)

		then:
		principalObj == "testuser"
		publisherCalled
	}

	private OAuth2Authentication createAuthentication() {
		def innerAuthentication = new AnonymousAuthenticationToken('test-key', 'testuser', authorities)
		def oauth2Request = new OAuth2Request([param1: '1'], 'client-id', authorities, true, ['read'].toSet(),
				['resource1'].toSet(), 'http://someredirect.com', ['implicit'].toSet(), [prop1: 'p1'])
		new OAuth2Authentication(oauth2Request, innerAuthentication)
	}
}
