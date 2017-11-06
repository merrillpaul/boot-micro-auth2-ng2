package com.pearson.projectone.data.entity.security.user

import com.pearson.projectone.AuthServerIntegrationTestApplication
import com.pearson.projectone.authcommons.dto.AppUserDetails
import com.pearson.projectone.data.dao.security.user.AppUserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import spock.lang.Specification
import spock.lang.Stepwise

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import java.sql.Timestamp
import java.time.LocalDateTime

@ContextConfiguration(classes = [AuthServerIntegrationTestApplication,
		AuthServerIntegrationTestApplication.TestJpaConfiguration])
@SpringBootTest
@Stepwise
@ActiveProfiles(["integration"])
@TestPropertySource(properties = [
		"config.auth_server=true"
])
class PasswordHistoryITSpec extends Specification {

	@Autowired
	AppUserDao userDao

	@PersistenceContext(unitName = "appSecurity")
	EntityManager entityManager


	@Autowired
	@Qualifier('appSecurityTransactionManager')
	PlatformTransactionManager transactionManager

	def TransactionStatus transactionStatus

	def setup() {
		transactionStatus = transactionManager.getTransaction()
	}

	def cleanup() {
		transactionManager.rollback(transactionStatus)
	}

	def authorities = ['ROLE_ADMIN', 'ROLE_CLINICIAN'].collect {
		new SimpleGrantedAuthority(it)
	}

	def "should capture password history for new user"() {
		given:
		def userId = userDao.save(new AppUser(username: 'joe', password: 'Password1', firstName: "Joe",
				lastName: 'Doe', timezoneId: TimeZone.default.ID, honorific: 'Mr',
				accountExpired: false, accountLocked: false, lastCreated: Timestamp.valueOf(LocalDateTime.now()),
				enabled: true)).id
		when:
		def retrievedUser = userDao.findOne(userId)

		then:
		retrievedUser.passwordHistories.size() == 1
		retrievedUser.passwordHistories[0].password == "ENCODED > Password1"
	}

	def "should capture password histories for every new password added to a user"() {
		given:
		def user = new AppUser(username: 'joe', password: 'Password1', firstName: "Joe",
				lastName: 'Doe', timezoneId: TimeZone.default.ID, honorific: 'Mr',
				accountExpired: false, accountLocked: false, lastCreated: Timestamp.valueOf(LocalDateTime.now()),
				enabled: true)
		userDao.save(user)
		flush()
		def userId = user.id
		user = userDao.findOne(userId)
		user.password = 'Password2'
		userDao.save(user)
		flush()
		user = userDao.findOne(userId)
		user.password = 'Password3'
		userDao.save(user)
		flush()

		when:
		def retrievedUser = userDao.findOne(userId)

		then:
		retrievedUser.passwordHistories.size() == 3
		retrievedUser.passwordHistories.collect { it.password }.sort() == [
				"ENCODED > Password1",
				"ENCODED > Password2",
				"ENCODED > Password3"
		]
	}

	def "should capture create and updated info for a new and updated entity"() {
		given:
		def loggedUser1 = userDao.save(new AppUser(username: 'log1', password: 'Password1', firstName: "Log1",
				lastName: 'Doe', timezoneId: TimeZone.default.ID, honorific: 'Mr',
				accountExpired: false, accountLocked: false, lastCreated: Timestamp.valueOf(LocalDateTime.now()),
				enabled: true))
		def loggedUser2 = userDao.save(new AppUser(username: 'log2', password: 'Password1', firstName: "Log2",
				lastName: 'Doe', timezoneId: TimeZone.default.ID, honorific: 'Mr',
				accountExpired: false, accountLocked: false, lastCreated: Timestamp.valueOf(LocalDateTime.now()),
				enabled: true))
		flush()
		and: 'login with first user'
		def loggedPrincipal1 = new AppUserDetails(loggedUser1, authorities, false)
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
				loggedPrincipal1, 'na', loggedPrincipal1.getAuthorities()))
		def user = new AppUser(username: 'joe', password: 'Password1', firstName: "Joe",
				lastName: 'Doe', timezoneId: TimeZone.default.ID, honorific: 'Mr',
				accountExpired: false, accountLocked: false, lastCreated: Timestamp.valueOf(LocalDateTime.now()),
				enabled: true)
		userDao.save(user)
		flush()
		def firstCreatedBy = user.createdBy
		def firstUpdatedBy = user.updatedBy

		and: 'clear authentication'
		SecurityContextHolder.clearContext()

		and: 'login with second user'
		def loggedPrincipal2 = new AppUserDetails(loggedUser2, authorities, false)
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
				loggedPrincipal2, 'na', loggedPrincipal2.getAuthorities()))

		def userId = user.id
		user = userDao.findOne(userId)
		user.password = 'Password2'
		userDao.save(user)
		flush()

		and: 'clear authentication'
		SecurityContextHolder.clearContext()

		when:
		user = userDao.findOne(userId)
		def secondCreatedBy = user.createdBy
		def secondUpdatedBy = user.updatedBy

		then:
		loggedUser1.id != loggedUser2.id
		firstCreatedBy == loggedUser1.id
		firstUpdatedBy == loggedUser1.id
		secondCreatedBy == loggedUser1.id
		secondUpdatedBy == loggedUser2.id
	}

	// mimics flush in run time
	private flush() {
		entityManager.flush()
		entityManager.clear()
	}
}
