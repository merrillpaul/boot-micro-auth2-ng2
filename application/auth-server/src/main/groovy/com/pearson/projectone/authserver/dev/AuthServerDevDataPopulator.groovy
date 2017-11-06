package com.pearson.projectone.authserver.dev

import com.pearson.projectone.core.support.populator.BasePopulator
import com.pearson.projectone.data.dao.security.user.AppRoleDao
import com.pearson.projectone.data.dao.security.user.AppUserDao
import com.pearson.projectone.data.entity.security.user.AppRole
import com.pearson.projectone.data.entity.security.user.AppUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.security.oauth2.provider.ClientRegistrationService
import org.springframework.security.oauth2.provider.client.BaseClientDetails
import org.springframework.stereotype.Component

import java.sql.Timestamp
import java.time.LocalDateTime

@Component
@Order(Integer.MIN_VALUE)
// should be the first
class AuthServerDevDataPopulator implements BasePopulator {

	private static final TOKEN_VALIDITTY = 30 * 60

	@Autowired
	ClientRegistrationService oauthClientService

	@Autowired
	AppRoleDao appRoleDao

	@Autowired
	AppUserDao userDao

	@Override
	void populate() {
		populateRoles(DEVXML.roles[0].role)
		populateUsers(DEVXML)
		populateOauth2Clients(DEVXML.oauth2[0].client)
	}

	private populateRoles(roles) {
		// adding roles
		roles.collect { it.@code }.each {
			appRoleDao.save(new AppRole(authority: it, lastCreated: Timestamp.valueOf(LocalDateTime.now())))
		}
	}

	private populateUsers(devXml) {

		def globalUsers = devXml.user

		def createUser = { it ->
			def user = userDao.save(new AppUser(id: it.@id, username: it.@userName, password: 'Password1', firstName: it.@firstName,
					lastName: it.@lastName, timezoneId: TimeZone.default.ID, honorific: it.@hon,
					accountExpired: false, accountLocked: false, lastCreated: Timestamp.valueOf(LocalDateTime.now()),
					enabled: true))
			def roles = it.@roles.split(',')
			user.roles = roles.collect {
				appRoleDao.findByAuthority("ROLE_${it.toString().toUpperCase()}")
			}
			userDao.save(user)
		}

		globalUsers.each(createUser)

		def allUsers = devXml.business_units
				.collect { it.business_unit.collect { it.account.collect { it.user } } }.flatten()
		allUsers.each(createUser)

	}

	private populateOauth2Clients(clients) {

		clients.each {

			def clientDetails = new BaseClientDetails(it.@id,
					'', it.@scopes ?: '', it.@grantTypes ?: '',
					it.@roles ?: '', //authorities or roles
					'')

			clientDetails.clientSecret = 'password1'
			clientDetails.accessTokenValiditySeconds = TOKEN_VALIDITTY
			clientDetails.refreshTokenValiditySeconds = TOKEN_VALIDITTY
			oauthClientService.addClientDetails(clientDetails)
		}
	}
}
