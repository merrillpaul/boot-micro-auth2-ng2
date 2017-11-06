package com.pearson.projectone.authcommons.provider.token.store

import com.pearson.projectone.authcommons.service.token.Oauth2TokenStoreService
import com.pearson.projectone.authcommons.utils.Oauth2SerializationUtils
import com.pearson.projectone.data.entity.security.oauth2.token.AccessToken
import com.pearson.projectone.data.entity.security.oauth2.token.RefreshToken
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.OAuth2Request
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.ZoneId

class DefaultTokenStoreSpec extends Specification {

	def accessToken
	def refreshToken
	def authentication
	def daoAccessToken
	def daoRefreshToken
	def authorities = [
			new SimpleGrantedAuthority("ROLE_ANON")
	]

	def setup() {
		refreshToken = new DefaultOAuth2RefreshToken(value: 'my-refresh-token')
		def scope = ['read'].toSet()
		def additionalInformation = [name: 'my-name', id: 'my-id']

		accessToken = new DefaultOAuth2AccessToken(value: 'my-access-token',
				expiration: Date.from(LocalDateTime.of(2017, 10, 25, 10, 30, 0).atZone(ZoneId.systemDefault()).toInstant()),
				tokenType: 'Bearer',
				refreshToken: refreshToken, scope: scope, additionalInformation: additionalInformation)

		def innerAuthentication = new AnonymousAuthenticationToken('test-key', 'testuser', authorities)
		def oauth2Request = new OAuth2Request([param1: '1'], 'client-id', authorities, true, ['read'].toSet(),
				['resource1'].toSet(), 'http://someredirect.com', ['implicit'].toSet(), [prop1: 'p1'])
		authentication = new OAuth2Authentication(oauth2Request, innerAuthentication)

		daoAccessToken = new AccessToken(tokenId: 'TK00001')
		daoRefreshToken = new RefreshToken(tokenId: 'RFK0001')
	}

	def "should read authentication for access token obj"() {
		given:
		def service = Spy(DefaultTokenStore) {
			readAuthentication('tk001') >> authentication
		}

		when:
		OAuth2Authentication auth2Authentication = service.readAuthentication("tk001")

		then:
		auth2Authentication == authentication
	}

	def "should call remove access token if deser failed for authentication when authentication is read"() {
		given:
		def passedTokenKey
		daoAccessToken.authentication = 'invalid ser'
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			getAccessToken(_) >> daoAccessToken
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService]) {
			removeAccessToken(_) >> { arguments ->
				passedTokenKey = arguments[0]
			}
		}

		when:
		service.readAuthentication("tk-9999")

		then:
		passedTokenKey == 'tk-9999'
	}

	def "should read authentication from token key"() {
		given:
		daoAccessToken.authentication = Oauth2SerializationUtils.serializeAuthentication(authentication)
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			getAccessToken(_) >> daoAccessToken
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService])

		when:
		def auth = service.readAuthentication("tk-9999")

		then:
		auth == authentication
	}

	def "should store access token"() {
		given:
		def savedAccessToken
		def savedAuthentication
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			saveToken(_, _) >> { _accessToken, _authentication ->
				savedAccessToken = _accessToken
				savedAuthentication = _authentication
				savedAccessToken
			}
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService]) {
			readAccessToken(_) >> null

			removeAccessToken(_) >> {

			}
		}

		when:
		service.storeAccessToken(accessToken, authentication)

		then:
		savedAccessToken.userName == 'testuser'
		savedAccessToken.clientId == 'client-id'
		savedAccessToken.tokenId != null
		savedAccessToken.refreshToken != null
		Oauth2SerializationUtils.deserializeAccessToken(savedAccessToken.token) == accessToken
		savedAuthentication == authentication
		Oauth2SerializationUtils.deserializeAuthentication(savedAccessToken.authentication) == savedAuthentication
	}

	def "should call remove access token if deser failed for access token"() {
		given:
		def passedTokenKey
		daoAccessToken.token = 'invalid ser'
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			getAccessToken(_) >> daoAccessToken
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService]) {
			removeAccessToken(_) >> { arguments ->
				passedTokenKey = arguments[0]
			}
		}

		when:
		service.readAccessToken("tk-9999")

		then:
		passedTokenKey == 'tk-9999'
	}

	def "should read access token from token key"() {
		given:
		daoAccessToken.token = Oauth2SerializationUtils.serializeAccessToken(accessToken)
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			getAccessToken(_) >> daoAccessToken
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService])

		when:
		def readAccessToken = service.readAccessToken("tk-9999")

		then:
		readAccessToken == accessToken
	}

	def "should call delete service"() {
		given:
		def passedTokenKey
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			deleteAccessToken(_) >> { arguments ->
				passedTokenKey = arguments[0]
			}
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService])

		when:
		service.removeAccessToken('tk-0101')

		then:
		passedTokenKey != null
	}

	def "should delegate the call to delete service when a token object is passed"() {
		given:
		def passedTokenKey
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			deleteAccessToken(_) >> { arguments ->
				passedTokenKey = arguments[0]
			}
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService])

		when:
		service.removeAccessToken(accessToken)

		then:
		passedTokenKey != null
	}

	def "should store refresh token"() {
		given:
		def savedRefreshToken
		def savedAuthentication
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			saveToken(_, _) >> { RefreshToken _refreshToken, OAuth2Authentication _authentication ->
				savedRefreshToken = _refreshToken
				savedAuthentication = _authentication
				savedRefreshToken
			}
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService])

		when:
		service.storeRefreshToken(refreshToken, authentication)

		then:
		Oauth2SerializationUtils.deserializeRefreshToken(savedRefreshToken.token) == refreshToken
		savedAuthentication == authentication
		Oauth2SerializationUtils.deserializeAuthentication(savedRefreshToken.authentication) == savedAuthentication
	}

	def "should call remove refresh token if deser failed for refresh token"() {
		given:
		def passedTokenKey
		daoRefreshToken.token = 'invalid ser'
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			getRefreshToken(_) >> daoRefreshToken
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService]) {
			removeRefreshToken(_) >> { arguments ->
				passedTokenKey = arguments[0]
			}
		}

		when:
		service.readRefreshToken("tk-9999")

		then:
		passedTokenKey == 'tk-9999'
	}

	def "should read refresh token from token key"() {
		given:
		daoRefreshToken.token = Oauth2SerializationUtils.serializeRefreshToken(refreshToken)
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			getRefreshToken(_) >> daoRefreshToken
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService])

		when:
		def readRefreshToken = service.readRefreshToken("tk-9999")

		then:
		refreshToken == readRefreshToken
	}

	def "should call remove refresh token if deser failed for attempting to read authentication from refresh token"() {
		given:
		def passedTokenKey
		daoRefreshToken.authentication = 'invalid ser'
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			getRefreshToken(_) >> daoRefreshToken
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService]) {
			removeRefreshToken(_) >> { arguments ->
				passedTokenKey = arguments[0]
			}
		}

		when:
		service.readAuthenticationForRefreshToken(refreshToken)

		then:
		passedTokenKey == 'my-refresh-token'
	}

	def "should call delete service when refresh token is removed"() {
		given:
		def passedTokenKey
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			deleteRefreshToken(_) >> { arguments ->
				passedTokenKey = arguments[0]
			}
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService])

		when:
		service.removeRefreshToken('tk-0101')

		then:
		passedTokenKey != null
	}

	def "should call the delegate to delete service when refresh token is removed"() {
		given:
		def passedTokenKey
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			deleteRefreshToken(_) >> { arguments ->
				passedTokenKey = arguments[0]
			}
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService])

		when:
		service.removeRefreshToken(refreshToken)

		then:
		passedTokenKey != null
	}

	def "should call delete service when access token is removed using refresh token"() {
		given:
		def passedTokenKey
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			deleteAccessTokenWithRefreshToken(_) >> { arguments ->
				passedTokenKey = arguments[0]
			}
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService])

		when:
		service.removeAccessTokenUsingRefreshToken(refreshToken)

		then:
		passedTokenKey != null
	}

	def "should find tokens by client and username"() {
		given:
		def accessTokens = [
				new AccessToken(tokenId: 'tk01', token: Oauth2SerializationUtils.serializeAccessToken(
						new DefaultOAuth2AccessToken(value: 'my-access-token1')
				)),
				new AccessToken(tokenId: 'tk02', token: Oauth2SerializationUtils.serializeAccessToken(
						new DefaultOAuth2AccessToken(value: 'my-access-token2')
				))
		]
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			getAccessTokens(_, _) >> {
				accessTokens
			}
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService])

		when:
		def result = service.findTokensByClientIdAndUserName(null, null)

		then:
		result.collect { it.value } == [
				'my-access-token1',
				'my-access-token2'
		]
	}

	def "should find tokens by client"() {
		given:
		def accessTokens = [
				new AccessToken(tokenId: 'tk01', token: Oauth2SerializationUtils.serializeAccessToken(
						new DefaultOAuth2AccessToken(value: 'my-access-token1')
				)),
				new AccessToken(tokenId: 'tk02', token: Oauth2SerializationUtils.serializeAccessToken(
						new DefaultOAuth2AccessToken(value: 'my-access-token2')
				))
		]
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			getAccessTokens(_) >> {
				accessTokens
			}
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService])

		when:
		def result = service.findTokensByClientId(null)

		then:
		result.collect { it.value } == [
				'my-access-token1',
				'my-access-token2'
		]
	}

	def "should get access token with oauth2 authentication"() {
		given:
		daoAccessToken.token = Oauth2SerializationUtils.serializeAccessToken(accessToken)
		daoAccessToken.authentication = Oauth2SerializationUtils.serializeAuthentication(authentication)
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			getAccessTokenForAuthenticationId(_) >> daoAccessToken

			getAccessToken(_) >> daoAccessToken
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService]) {
			readAuthentication('tk001') >> authentication
		}

		when:
		def oauth2AcessToken = service.getAccessToken(authentication)

		then:
		oauth2AcessToken == accessToken
	}

	def "should remove old access token and add the new authentication if the key is different"() {
		given:
		def removeTokenKey
		def updatedAccessToken
		def updatedAuthentication
		daoAccessToken.token = Oauth2SerializationUtils.serializeAccessToken(accessToken)
		daoAccessToken.authentication = Oauth2SerializationUtils.serializeAuthentication(authentication)
		def oauthTokenStoreService = Stub(Oauth2TokenStoreService) {
			getAccessTokenForAuthenticationId(_) >> daoAccessToken

			getAccessToken(_) >> daoAccessToken
		}
		def service = Spy(DefaultTokenStore, constructorArgs: [oauthTokenStoreService]) {
			readAuthentication(_) >> {
				def innerAuthentication = new AnonymousAuthenticationToken('another-key', 'testuser', authorities)
				def oauth2Request = new OAuth2Request([param1: '1'], 'new-client-id', authorities, true, ['read'].toSet(),
						['resource1'].toSet(), 'http://someredirect.com', ['implicit'].toSet(), [prop1: 'p1'])
				new OAuth2Authentication(oauth2Request, innerAuthentication)
			}

			removeAccessToken(_) >> {
				arguments
				removeTokenKey = arguments[0]
			}

			storeAccessToken(_, _) >> { _accessToken, _authentication ->
				updatedAccessToken = _accessToken
				updatedAuthentication = _authentication
			}
		}

		when:
		def oauth2AcessToken = service.getAccessToken(authentication)

		then:
		oauth2AcessToken == accessToken
		removeTokenKey == 'my-access-token'
		updatedAccessToken == accessToken
		updatedAuthentication == authentication
	}
}
