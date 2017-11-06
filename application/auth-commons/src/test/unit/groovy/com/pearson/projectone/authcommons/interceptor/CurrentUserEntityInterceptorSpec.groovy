package com.pearson.projectone.authcommons.interceptor

import com.pearson.projectone.authcommons.dto.AppUserDetails
import com.pearson.projectone.authcommons.service.user.CurrentUserService
import com.pearson.projectone.core.support.data.RelationalEntity
import com.pearson.projectone.data.entity.security.user.AppUser
import spock.lang.Specification

class CurrentUserEntityInterceptorSpec extends Specification {

	def currentUserId
	def interceptor

	private static class SampleEntity extends RelationalEntity {
		String description
	}

	def setup() {
		currentUserId = null
		interceptor = Spy(CurrentUserEntityInterceptor) {
			getCurrentUserId() >> {
				currentUserId
			}
		}
	}

	def "should set current user while creating new entity"() {
		given:
		currentUserId = 'jondoe'
		def entity = new SampleEntity(description: "Some desc")

		when:
		interceptor.beforeInsert(entity)

		then:
		entity.createdBy == 'jondoe'
		entity.updatedBy == 'jondoe'
	}

	def "should NOT set current user while creating new entity if no current user found"() {
		given:
		def entity = new SampleEntity(description: "Some desc")

		when:
		interceptor.beforeInsert(entity)

		then:
		entity.createdBy == null
		entity.updatedBy == null
	}

	def "should set current user while updating entity"() {
		given:
		currentUserId = 'janedoe'
		def entity = new SampleEntity(description: "Some desc", createdBy: 'jondoe')

		when:
		interceptor.beforeUpdate(entity)

		then:
		entity.createdBy == 'jondoe'
		entity.updatedBy == 'janedoe'
	}

	def "should NOT set current user while updating entity if no current user found"() {
		given:
		def entity = new SampleEntity(description: "Some desc", createdBy: 'jondoe')

		when:
		interceptor.beforeUpdate(entity)

		then:
		entity.createdBy == 'jondoe'
		entity.updatedBy == null
	}

	def "should return current user id from resource current user service"() {
		given:
		def service = Spy(CurrentUserService) {
			getCurrentUser() >> {
				def appUser = new AppUser(
						id: "user0001", username: 'user1', password: 'Password1!', firstName: 'Jane',
						lastName: 'Doe', honorific: 'Dr.', timezoneId: 'America/Chicago', enabled: true, accountExpired: false,
						accountLocked: false
				)
				new AppUserDetails(appUser, null, false)
			}
		}
		def interceptor = new CurrentUserEntityInterceptor(currentUserService: service)

		when:
		def returnedUserId = interceptor.getCurrentUserId()

		then:
		returnedUserId == 'user0001'
	}

}
