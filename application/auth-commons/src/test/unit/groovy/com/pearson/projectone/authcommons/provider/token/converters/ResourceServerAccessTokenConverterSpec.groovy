package com.pearson.projectone.authcommons.provider.token.converters

import com.pearson.projectone.authcommons.dto.AppUserDetails
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.OAuth2Request
import org.springframework.security.oauth2.provider.token.AccessTokenConverter
import spock.lang.Specification

import static com.pearson.projectone.authcommons.provider.AuthKeys.*

class ResourceServerAccessTokenConverterSpec extends Specification {

	def "should set the resource server principal as an AppUserDetails instance"() {
		given:
		def delegateStub = Stub(AccessTokenConverter) {
			extractAuthentication(_) >> {
				def authorities = [
						new SimpleGrantedAuthority("ROLE_ANON")
				]
				def innerAuthentication = new AnonymousAuthenticationToken('test-key', 'testuser', authorities)
				def oauth2Request = new OAuth2Request([param1: '1'], 'client-id', authorities, true, ['read'].toSet(),
						['resource1'].toSet(), 'http://someredirect.com', ['implicit'].toSet(), [prop1: 'p1'])
				new OAuth2Authentication(oauth2Request, innerAuthentication)
			}
		}
		def converter = new ResourceServerAccessTokenConverter(delegate: delegateStub)

		when:
		OAuth2Authentication authentication = converter.extractAuthentication([
				(id.name())        : 'userid0001',
				(firstName.name()) : 'Jane',
				(lastName.name())  : 'Doe',
				(honorific.name()) : 'Dr.',
				(timezoneId.name()): 'America/Chicago',
				(metaData.name())  : [
						'businessUnit'  : 'US',
						'businessEntity': [
								id: 'beid'
						]
				]
		])

		then:
		authentication.principal instanceof AppUserDetails
		authentication.authorities.collect { it.role } == ['ROLE_ANON']
		authentication.principal.id == 'userid0001'
		authentication.principal.firstName == 'Jane'
		authentication.principal.lastName == 'Doe'
		authentication.principal.password == 'N/A'
		authentication.principal.honorific == 'Dr.'
		authentication.principal.username == 'testuser'
		authentication.principal.timezoneId == 'America/Chicago'
		authentication.principal.authorities.collect { it.role } == ['ROLE_ANON']
		authentication.principal.extraData == [
				'businessUnit'  : 'US',
				'businessEntity': [
						id: 'beid'
				]
		]
	}

	def "should call delegate's convertAccessToken as is"() {
		given:
		def delegateStub = Stub(AccessTokenConverter) {
			convertAccessToken(_, _) >> {
				[
						id  : 'id00',
						name: 'Jane',
						bu  : [
								id  : 'bu100',
								name: 'US'
						]
				]
			}
		}
		def converter = new ResourceServerAccessTokenConverter(delegate: delegateStub)

		when:
		def res = converter.convertAccessToken(null, null)

		then:
		res == [
				id  : 'id00',
				name: 'Jane',
				bu  : [
						id  : 'bu100',
						name: 'US'
				]
		]
	}

	def "should call delegate's extractAccessToken as is"() {
		given:
		def delegateStub = Stub(AccessTokenConverter) {
			extractAccessToken(_, _) >> {
				new DefaultOAuth2AccessToken('tk00001')
			}
		}
		def converter = new ResourceServerAccessTokenConverter(delegate: delegateStub)

		when:
		def res = converter.extractAccessToken(null, null)

		then:
		res.value == 'tk00001'
	}
}
