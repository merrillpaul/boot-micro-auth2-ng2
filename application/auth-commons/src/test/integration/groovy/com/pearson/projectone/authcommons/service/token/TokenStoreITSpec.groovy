package com.pearson.projectone.authcommons.service.token

import com.pearson.projectone.IntegrationTestApplication
import com.pearson.projectone.data.entity.security.oauth2.token.AccessToken
import com.pearson.projectone.data.entity.security.oauth2.token.RefreshToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification
import spock.lang.Stepwise

@ContextConfiguration(classes = [IntegrationTestApplication, IntegrationTestApplication.TestMongoConfiguration,
		IntegrationTestApplication.TestJpaConfiguration])
@SpringBootTest
@Stepwise
@ActiveProfiles(["integration", "token"])
@TestPropertySource(properties = [
		"config.auth_server=false"
])
class TokenStoreITSpec extends Specification {

	@Autowired
	Oauth2TokenStoreService oauth2TokenStoreService

	def "should persist an access token"() {
		given:
		def accessToken = new AccessToken(tokenId: 'TK1000', token: 'someotkenuuid',
				authenticationId: 'AUTH001', authentication: 'authser', userName: 'jane', clientId: 'reactui',
				refreshToken: 'RF0001')

		when:
		oauth2TokenStoreService.saveToken(accessToken, null)
		def savedAccessToken = oauth2TokenStoreService.getAccessToken(accessToken.tokenId)

		then:
		savedAccessToken.properties == accessToken.properties
	}

	def "should persist a refresh token"() {
		given:
		def refreshToken = new RefreshToken(tokenId: 'RFK001', token: 'someuuid', authentication: 'auth001')
		when:
		oauth2TokenStoreService.saveToken(refreshToken, null)
		def savedRefreshToken = oauth2TokenStoreService.getRefreshToken(refreshToken.tokenId)

		then:
		savedRefreshToken.properties == refreshToken.properties
	}

	def "should delete an access token"() {
		given:
		def accessToken = new AccessToken(tokenId: 'TK1000', token: 'someotkenuuid',
				authenticationId: 'AUTH001', authentication: 'authser', userName: 'jane', clientId: 'reactui',
				refreshToken: 'RF0001')
		and: 'the access token is saved'
		oauth2TokenStoreService.saveToken(accessToken, null)

		when: 'it is removed'
		oauth2TokenStoreService.deleteAccessToken(accessToken.tokenId)
		def savedAccessToken = oauth2TokenStoreService.getAccessToken(accessToken.tokenId)

		then:
		savedAccessToken == null
	}

	def "should delete a refresh token"() {
		given:
		def refreshToken = new RefreshToken(tokenId: 'RFK001', token: 'someuuid', authentication: 'auth001')

		and: 'the refresh token is saved'
		oauth2TokenStoreService.saveToken(refreshToken, null)

		when: 'it is removed'
		oauth2TokenStoreService.deleteRefreshToken(refreshToken.tokenId)
		def savedRefreshToken = oauth2TokenStoreService.getRefreshToken(refreshToken.tokenId)

		then:
		savedRefreshToken == null
	}

	def "should delete access token with a refresh token"() {
		given:
		def refreshTokenId = 'RF0001'
		def accessToken = new AccessToken(tokenId: 'TK1000', token: 'someotkenuuid',
				authenticationId: 'AUTH001', authentication: 'authser', userName: 'jane', clientId: 'reactui',
				refreshToken: refreshTokenId)
		and: 'the access token is saved'
		oauth2TokenStoreService.saveToken(accessToken, null)

		when:
		oauth2TokenStoreService.deleteAccessTokenWithRefreshToken(refreshTokenId)
		def savedAccessToken = oauth2TokenStoreService.getAccessToken(accessToken.tokenId)

		then:
		savedAccessToken == null
	}

	def "should get access tokens for client id"() {
		given:
		def accessTokens = [
				new AccessToken(tokenId: 'TK1000', token: 'someotkenuuid',
						authenticationId: 'AUTH001', authentication: 'authser', userName: 'jane', clientId: 'reactui',
						refreshToken: 'RF0001'),
				new AccessToken(tokenId: 'TK1001', token: 'someotkenuuid1',
						authenticationId: 'AUTH002', authentication: 'authser11', userName: 'joe', clientId: 'reactui',
						refreshToken: 'RF0001'),
				new AccessToken(tokenId: 'TK1002', token: 'someotkenuuid133',
						authenticationId: 'AUTH003', authentication: 'authser12', userName: 'jon', clientId: 'ng2',
						refreshToken: 'RF0001')
		].each {
			oauth2TokenStoreService.saveToken(it, null)
		}

		when:
		def reactAccessTokens = oauth2TokenStoreService.getAccessTokens('reactui')
		def ng2AccessTokens = oauth2TokenStoreService.getAccessTokens('ng2')

		then:
		reactAccessTokens.size() == 2
		ng2AccessTokens.size() == 1
		reactAccessTokens.collect { it.userName }.sort() == ['jane', 'joe']
		ng2AccessTokens.collect { it.userName } == ['jon']
	}

	def "should get access tokens for client id and username"() {
		given:
		def accessTokens = [
				new AccessToken(tokenId: 'TK1000', token: 'someotkenuuid',
						authenticationId: 'AUTH001', authentication: 'authser', userName: 'jane', clientId: 'reactui',
						refreshToken: 'RF0001'),
				new AccessToken(tokenId: 'TK1001', token: 'someotkenuuid1',
						authenticationId: 'AUTH002', authentication: 'authser11', userName: 'joe', clientId: 'reactui',
						refreshToken: 'RF0001'),
				new AccessToken(tokenId: 'TK1002', token: 'someotkenuuid133',
						authenticationId: 'AUTH003', authentication: 'authser12', userName: 'jon', clientId: 'ng2',
						refreshToken: 'RF0001')
		].each {
			oauth2TokenStoreService.saveToken(it, null)
		}

		when:
		def reactAccessTokens = oauth2TokenStoreService.getAccessTokens('reactui', 'joe')

		then:
		reactAccessTokens.size() == 1
		reactAccessTokens.collect { it.userName } == ['joe']
	}

	def "should get access token for authentication id"() {
		given:
		def authenticationId = 'AUTH001'
		def accessToken = new AccessToken(tokenId: 'TK1000', token: 'someotkenuuid',
				authenticationId: authenticationId, authentication: 'authser', userName: 'jane', clientId: 'reactui',
				refreshToken: 'RF0001')

		when:
		oauth2TokenStoreService.saveToken(accessToken, null)
		def savedAccessToken = oauth2TokenStoreService.getAccessTokenForAuthenticationId(authenticationId)

		then:
		savedAccessToken.properties == accessToken.properties
	}
}
