package com.pearson.projectone.authcommons.dto

import com.pearson.projectone.data.entity.security.user.AppUser
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.springframework.security.core.authority.SimpleGrantedAuthority
import spock.lang.Specification

class AppUserDetailsSpec extends Specification {

	def "clone should remove password and return plain roles"() {
		when: "we have properties and authorities"
		def property = new Properties()
		property.setProperty('name', 'US')
		def authorities = ['ROLE_ADMIN', 'ROLE_CLINICIAN'].collect {
			new SimpleGrantedAuthority(it)
		}
		def extraData = [
				'businessEntity': property
		]

		and: "a user exists"
		def appUser = new AppUser(
				id: "10000", username: 'user1', password: 'Password1!', firstName: 'Jane',
				lastName: 'Doe', honorific: 'Dr.', timezoneId: 'America/Chicago', enabled: true, accountExpired: false,
				accountLocked: false
		)
		def user = new AppUserDetails(appUser, authorities, false)
		user.setExtraData('businessEntity', 'NEISD')
		def clonedUser = AppUserDetails.clone(user)
		def expected = """
		{
			"password": "",
			"accountNonExpired": false,
			"honorific": "Dr.",
			"accountNonLocked": false,
			"roles": [
				"ROLE_ADMIN",
				"ROLE_CLINICIAN"
			],
			"authorities": [

			],
			"id": "10000",
			"firstName": "Jane",
			"lastName": "Doe",
			"credentialsNonExpired": true,
			"passwordExpired": null,
			"enabled": true,
			"username": "user1",
			"timezoneId": "America/Chicago"
		}
		"""

		then:
		new JsonSlurper().parseText(expected) == new JsonSlurper().parseText(JsonOutput.toJson(clonedUser))

	}
}
