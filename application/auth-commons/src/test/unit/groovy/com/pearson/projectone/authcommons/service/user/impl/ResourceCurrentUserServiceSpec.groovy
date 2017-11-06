package com.pearson.projectone.authcommons.service.user.impl

import com.pearson.projectone.authcommons.dto.AppUserDetails
import com.pearson.projectone.data.entity.security.user.AppUser
import org.junit.Rule
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Specification

@PrepareForTest([SecurityContextHolder])
class ResourceCurrentUserServiceSpec extends Specification {

	// to setup static interceptions
	@Rule
	PowerMockRule powerMockRule = new PowerMockRule()

	def "should return an authentication from spring security context"() {
		given:
		def authentication = new TestingAuthenticationToken('someusername', null)
		def securityContext = Stub(SecurityContext) {
			getAuthentication() >> authentication
		}
		PowerMockito.mockStatic(SecurityContextHolder)
		PowerMockito.when(SecurityContextHolder.getContext()).thenReturn(securityContext)
		def service = new ResourceCurrentUserService()

		when:
		def contextAuthentication = service.authentication

		then:
		contextAuthentication.principal == 'someusername'
	}

	def "should return current user"() {
		given:
		def appUser = new AppUser(
				id: "10000", username: 'user1', password: 'Password1!', firstName: 'Jane',
				lastName: 'Doe', honorific: 'Dr.', timezoneId: 'America/Chicago', enabled: true, accountExpired: false,
				accountLocked: false
		)
		def userDetails = new AppUserDetails(appUser, null, false)
		def authentication = new TestingAuthenticationToken(userDetails, null)
		def service = Spy(ResourceCurrentUserService) {
			getAuthentication() >> authentication
		}

		when:
		def currentUser = service.currentUser

		then:
		currentUser == userDetails
	}

	def "should throw exception if principal in authentication is not expected type"() {
		given:
		def appUser = new AppUser(
				id: "10000", username: 'user1', password: 'Password1!', firstName: 'Jane',
				lastName: 'Doe', honorific: 'Dr.', timezoneId: 'America/Chicago', enabled: true, accountExpired: false,
				accountLocked: false
		)
		def authentication = new TestingAuthenticationToken(appUser, null)
		def securityContext = Stub(SecurityContext) {
			getAuthentication() >> authentication
		}
		PowerMockito.mockStatic(SecurityContextHolder)
		PowerMockito.when(SecurityContextHolder.getContext()).thenReturn(securityContext)
		def service = new ResourceCurrentUserService()

		when:
		service.getCurrentUser()

		then:
		thrown(AuthenticationException)

	}
}
