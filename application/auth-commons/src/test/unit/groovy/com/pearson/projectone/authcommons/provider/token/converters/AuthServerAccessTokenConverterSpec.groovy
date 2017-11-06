package com.pearson.projectone.authcommons.provider.token.converters

import com.pearson.projectone.authcommons.dto.AppUserDetails
import com.pearson.projectone.data.dao.security.user.AppUserDao
import com.pearson.projectone.data.entity.security.user.AppUser
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.OAuth2Request
import org.springframework.security.oauth2.provider.token.AccessTokenConverter
import spock.lang.Specification


class AuthServerAccessTokenConverterSpec extends Specification {

	def "should call delegate's extractAccessToken as is"() {
		given:
		def delegateStub = Stub(AccessTokenConverter) {
			extractAccessToken(_, _) >> {
				new DefaultOAuth2AccessToken('tk00001')
			}
		}
		def converter = new AuthServerAccessTokenConverter(delegate: delegateStub)

		when:
		def res = converter.extractAccessToken(null, null)

		then:
		res.value == 'tk00001'
	}

	def "should call delegate's extractAuthentication as is"() {
		given:
		def authorities = [
				new SimpleGrantedAuthority("ROLE_ANON")
		]
		def innerAuthentication = new AnonymousAuthenticationToken('test-key', 'testuser', authorities)
		def oauth2Request = new OAuth2Request([param1: '1'], 'client-id', authorities, true, ['read'].toSet(),
				['resource1'].toSet(), 'http://someredirect.com', ['implicit'].toSet(), [prop1: 'p1'])
		def authentication = new OAuth2Authentication(oauth2Request, innerAuthentication)
		def delegateStub = Stub(AccessTokenConverter) {
			extractAuthentication(_) >> {
				authentication
			}
		}
		def converter = new AuthServerAccessTokenConverter(delegate: delegateStub)

		when:
		def res = converter.extractAuthentication(null)

		then:
		res.principal == 'testuser'
		res == authentication
	}

	def "should add extra attributes from the user into the access token's attributes"() {
		given:
		def authorities = [
				new SimpleGrantedAuthority("ROLE_ANON")
		]
		def appUser = new AppUser(
				id: "10000", username: 'user1', password: 'Password1!', firstName: 'Jane',
				lastName: 'Doe', honorific: 'Dr.', timezoneId: 'America/Chicago', enabled: true, accountExpired: false,
				accountLocked: false
		)
		def userDao = Stub(AppUserDao) {
			findByUsername(_) >> appUser
		}
		def user = new AppUserDetails(appUser, authorities, false)
		def innerAuthentication = new AnonymousAuthenticationToken('test-key', user, authorities)
		def oauth2Request = new OAuth2Request([param1: '1'], 'client-id', authorities, true, ['read'].toSet(),
				['resource1'].toSet(), 'http://someredirect.com', ['implicit'].toSet(), [prop1: 'p1'])
		def authentication = new OAuth2Authentication(oauth2Request, innerAuthentication)
		def delegateStub = Stub(AccessTokenConverter) {
			convertAccessToken(_, _) >> {
				[
						"foo": "bar"
				]
			}
		}
		def converter = new AuthServerAccessTokenConverter(delegate: delegateStub, userDao: userDao)

		when:
		def res = converter.convertAccessToken(null, authentication)

		then:
		res == [
				foo       : "bar",
				firstName : "Jane",
				lastName  : "Doe",
				timezoneId: "America/Chicago",
				id        : "10000",
				honorific : "Dr.",
				metaData  : [:]
		]
	}
}
