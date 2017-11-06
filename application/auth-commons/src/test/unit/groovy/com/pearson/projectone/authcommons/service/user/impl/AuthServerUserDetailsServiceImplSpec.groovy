package com.pearson.projectone.authcommons.service.user.impl

import com.pearson.projectone.authcommons.dto.AppUserDetails
import com.pearson.projectone.data.dao.security.user.AppUserDao
import com.pearson.projectone.data.entity.security.user.AppRole
import com.pearson.projectone.data.entity.security.user.AppUser
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

class AuthServerUserDetailsServiceImplSpec extends Specification {
	def "should have correct annotations"() {
		expect:
		AuthServerUserDetailsServiceImpl.getAnnotation(Transactional) != null
	}

	def "should call loadUser when called for loadUserByDomainObject"() {
		given:
		def authorities = ['ROLE_ADMIN', 'ROLE_CLINICIAN'].collect {
			new SimpleGrantedAuthority(it)
		}
		def appUser = new AppUser(
				id: "10000", username: 'user1', password: 'Password1!', firstName: 'Jane',
				lastName: 'Doe', honorific: 'Dr.', timezoneId: 'America/Chicago', enabled: true, accountExpired: false,
				accountLocked: false
		)
		def userDetails = new AppUserDetails(appUser, authorities, false)
		def service = Spy(AuthServerUserDetailsServiceImpl) {
			loadUserByUsername(_) >> {
				userDetails
			}
		}

		when:
		def userbyDomain = service.loadUserByDomainObject(appUser)

		then:
		userbyDomain == userDetails

	}

	def "should throw username not found exception if user not found"() {
		given:
		def appUserDao = Stub(AppUserDao) {
			findByUsername(_) >> null
		}
		def service = new AuthServerUserDetailsServiceImpl(appUserDao: appUserDao);

		when:
		service.loadUserByUsername("someusername")

		then:
		thrown(UsernameNotFoundException)
	}

	def "should get user information with valid details"() {
		given:
		def appUser = new AppUser(
				id: "10000", password: 'Password1!', firstName: 'Jane',
				lastName: 'Doe', honorific: 'Dr.', timezoneId: 'America/Chicago', enabled: true, accountExpired: false,
				accountLocked: false,
				roles: [new AppRole(authority: 'ROLE_ADMIN'), new AppRole(authority: 'ROLE_CLINICIAN')]
		)
		def appUserDao = Stub(AppUserDao) {
			findByUsername(_) >> { arguments ->
				def username = arguments[0]
				appUser.username = username
				appUser
			}
		}
		def service = new AuthServerUserDetailsServiceImpl(appUserDao: appUserDao)

		when:
		def userDetails = service.loadUserByUsername("jane")

		then:
		userDetails.username == 'jane'
		userDetails.firstName == appUser.firstName
		userDetails.authorities[0] == new SimpleGrantedAuthority('ROLE_ADMIN')
		userDetails.authorities[1] == new SimpleGrantedAuthority('ROLE_CLINICIAN')

	}

}
