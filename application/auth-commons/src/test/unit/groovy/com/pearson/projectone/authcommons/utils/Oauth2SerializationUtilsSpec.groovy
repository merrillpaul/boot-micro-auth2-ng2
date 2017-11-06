package com.pearson.projectone.authcommons.utils

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.OAuth2Request
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.ZoneId

class Oauth2SerializationUtilsSpec extends Specification {

	def "should ser-deser oauth2 access token"() {
		given:
		def refreshToken = new DefaultOAuth2RefreshToken(value: 'my-refresh-token')
		def scope = ['read'].toSet()
		def additionalInformation = [name: 'my-name', id: 'my-id']

		def accessToken = new DefaultOAuth2AccessToken(value: 'my-access-token',
				expiration: Date.from(LocalDateTime.of(2017, 10, 25, 10, 30, 0).atZone(ZoneId.systemDefault()).toInstant()),
				tokenType: 'Bearer',
				refreshToken: refreshToken, scope: scope, additionalInformation: additionalInformation)

		when:
		String encodedString = Oauth2SerializationUtils.serializeAccessToken(accessToken)
		def deserializedAccessToken = Oauth2SerializationUtils.deserializeAccessToken(encodedString)

		then:
		deserializedAccessToken == accessToken
		deserializedAccessToken.refreshToken == refreshToken
	}

	def "ser-deser should throw exception for incorrect class"() {
		given:
		def accessToken = new DefaultOAuth2AccessToken(value: 'my-access-token')

		when:
		String encodedString = Oauth2SerializationUtils.serializeAccessToken(accessToken)
		Oauth2SerializationUtils.deserializeAuthentication(encodedString)

		then:
		thrown ClassCastException
	}

	def "should ser-deser oauth2 refresh token"() {
		given:
		def refreshToken = new DefaultOAuth2RefreshToken(value: 'my-refresh-token')

		when:
		String encodedString = Oauth2SerializationUtils.serializeRefreshToken(refreshToken)
		def deserializedRefreshToken = Oauth2SerializationUtils.deserializeRefreshToken(encodedString)

		then:
		deserializedRefreshToken == refreshToken
	}

	def "should ser-deser oauth2 authentication object"() {
		given:
		def authorities = [
				new SimpleGrantedAuthority("ROLE_ANON")
		]
		def innerAuthentication = new AnonymousAuthenticationToken('test-key', 'testuser', authorities)
		def oauth2Request = new OAuth2Request([param1: '1'], 'client-id', authorities, true, ['read'].toSet(),
				['resource1'].toSet(), 'http://someredirect.com', ['implicit'].toSet(), [prop1: 'p1'])
		def authentication = new OAuth2Authentication(oauth2Request, innerAuthentication)

		when:
		String encodedString = Oauth2SerializationUtils.serializeAuthentication(authentication)
		def deserializedAuthentication = Oauth2SerializationUtils.deserializeAuthentication(encodedString)

		then:
		deserializedAuthentication.authorities == authorities
		deserializedAuthentication.userAuthentication == innerAuthentication
		deserializedAuthentication == authentication
	}
}
