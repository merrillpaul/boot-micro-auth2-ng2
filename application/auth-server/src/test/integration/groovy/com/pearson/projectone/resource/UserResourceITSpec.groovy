package com.pearson.projectone.resource

import com.pearson.projectone.AuthServerIntegrationTestApplication
import com.pearson.projectone.authcommons.dto.AppUserDetails
import com.pearson.projectone.data.dao.security.user.AppRoleDao
import com.pearson.projectone.data.dao.security.user.AppUserDao
import com.pearson.projectone.data.entity.security.user.AppRole
import com.pearson.projectone.data.entity.security.user.AppUser
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.util.NestedServletException
import spock.lang.Specification
import spock.lang.Stepwise

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import java.sql.Timestamp
import java.time.LocalDateTime

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ContextConfiguration(classes = [AuthServerIntegrationTestApplication,
		AuthServerIntegrationTestApplication.TestJpaConfiguration])
@SpringBootTest
@Stepwise
@ActiveProfiles(["integration"])
@TestPropertySource(properties = [
		"config.auth_server=true"
])
@WebAppConfiguration(value = "")
class UserResourceITSpec extends Specification {

	@Autowired
	WebApplicationContext wac

	@Autowired
	AppUserDao userDao

	@Autowired
	AppRoleDao roleDao

	MockMvc mockMvc

	@PersistenceContext(unitName = "appSecurity")
	EntityManager entityManager

	@Autowired
	@Qualifier('appSecurityTransactionManager')
	PlatformTransactionManager transactionManager

	def TransactionStatus transactionStatus
	def currentUser
	def authorities = ['ROLE_SYSTEM_ADMIN'].collect {
		new SimpleGrantedAuthority(it)
	}

	def setup() {
		transactionStatus = transactionManager.getTransaction()
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()

		currentUser = userDao.save(new AppUser(id: "USR0001", username: 'joe', password: 'Password1', firstName: "Joe",
				lastName: 'Doe', timezoneId: TimeZone.default.ID, honorific: 'Mr',
				accountExpired: false, accountLocked: false, lastCreated: Timestamp.valueOf(LocalDateTime.now()),
				enabled: true))

		def loggedPrincipal = new AppUserDetails(currentUser, authorities, false)
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
				loggedPrincipal, 'na', loggedPrincipal.getAuthorities()))

		["ROLE_SYSTEM_ADMIN", "ROLE_BU_ADMIN", "ROLE_CLINICIAN", "ROLE_FINANCE"].each {
			roleDao.save(new AppRole(authority: it))
		}
	}

	def cleanup() {
		transactionManager.rollback(transactionStatus)
	}

	def "should get user details from user resource"() {
		when:
		def result = this.mockMvc.perform(get("/api/user/${currentUser.id}")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn()
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		assert resultJson.id != null
		resultJson.id = null
		def expectedJson = new JsonSlurper().parseText("""
		{
		  "version": 0,
		  "id": null,
		  "username": "joe",
		  "firstName": "Joe",
		  "lastName": "Doe",
		  "honorific": "Mr",
		  "timezoneId": "America/Chicago",
		  "enabled": true,
		  "accountExpired": false,
		  "accountLocked": false,
		  "confirmed": false
		}
		""")
		expectedJson.id == null

		then:
		resultJson == expectedJson
	}

	def "should deny access to any non system admin user"() {
		given:
		def userAuthorities = ['ROLE_CLINICIAN'].collect {
			new SimpleGrantedAuthority(it)
		}
		def loggedPrincipal = new AppUserDetails(currentUser, userAuthorities, false)
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
				loggedPrincipal, 'na', loggedPrincipal.getAuthorities()))

		when:
		this.mockMvc.perform(get("/api/user/${currentUser.id}"))

		then:
		NestedServletException ex = thrown()
		ex.rootCause.class == AccessDeniedException
	}

	def "should create a user"() {
		when:
		def result = this.mockMvc.perform(put("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
					{
						"username": "mpaul",
						"password": "grizzlystar",
						"firstName": "Iron",
						"lastName": "Man",
						"honorific": "Dr.",
						"timezoneId": "Australia/Melbourne",
						"roles": [
							"ROLE_BU_ADMIN",
							"ROLE_CLINICIAN"
						]
					}
				""".toString()
		)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn()
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		def createdUser = userDao.findOne(resultJson.id)

		then:
		resultJson.version == 0
		createdUser.password == "ENCODED > grizzlystar"
		createdUser.passwordHistories.size() == 1
		createdUser.passwordHistories[0].password == createdUser.password
		createdUser.username == "mpaul"
		createdUser.roles.collect { it.authority }.sort() == ["ROLE_BU_ADMIN", "ROLE_CLINICIAN"]
	}

	def "should update a user"() {
		when:
		def userId = userDao.save(new AppUser(username: 'jane', password: 'Password1', firstName: "Joe",
				lastName: 'Doe', timezoneId: TimeZone.default.ID, honorific: 'Mr', roles: [roleDao.findByAuthority("ROLE_FINANCE")],
				accountExpired: false, accountLocked: false, lastCreated: Timestamp.valueOf(LocalDateTime.now()),
				enabled: true)).id
		flush()
		def result = this.mockMvc.perform(post("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
					{
						"id": "${userId}",
						"username": "jane",
						"password": "grizzlystar",
						"firstName": "Captain",
						"lastName": "Doe",
						"honorific": "Dr.",
						"timezoneId": "Australia/Melbourne",
						"roles": [
							"ROLE_BU_ADMIN",
							"ROLE_CLINICIAN"
						],
						"enabled": true,
						"confirmed": true,
						"version": 0
					}
				""".toString()
		)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn()
		flush()
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		def updatedUser = userDao.findOne(resultJson.id)

		then:
		resultJson.version == 0
		updatedUser.password == "ENCODED > grizzlystar"
		updatedUser.passwordHistories.size() == 2
		updatedUser.roles.collect { it.authority }.sort() == ["ROLE_BU_ADMIN", "ROLE_CLINICIAN"]
	}

	def "should search user by username with pagination aspects"() {
		given:
		createUsers()

		when:
		def result = this.mockMvc.perform(post("/api/user/list")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
					{
						"criteria": [
										{
											"key": "username",
											"operation": "_*_",
											"value": "BaR"
										}
						]
					}
				""".toString()
		)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn()
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		// to clear ids' as they will change for every run
		resultJson.content.each {
			assert it.id != null
			it.id = null
		}
		def expectedJsonResponse = new JsonSlurper().parseText("""
		{
		  "content": [
			{
			  "version": 0,
			  "id": null,
			  "username": "bar_0",
			  "firstName": "BAR",
			  "lastName": "0",
			  "honorific": "Mr",
			  "timezoneId": "America/Chicago",
			  "enabled": true,
			  "accountExpired": false,
			  "accountLocked": false,
			  "confirmed": false
			},
			{
			  "version": 0,
			  "id": null,
			  "username": "bar_1",
			  "firstName": "BAR",
			  "lastName": "1",
			  "honorific": "Mr",
			  "timezoneId": "America/Chicago",
			  "enabled": true,
			  "accountExpired": false,
			  "accountLocked": false,
			  "confirmed": false
			},
			{
			  "version": 0,
			  "id": null,
			  "username": "bar_2",
			  "firstName": "BAR",
			  "lastName": "2",
			  "honorific": "Mr",
			  "timezoneId": "America/Chicago",
			  "enabled": true,
			  "accountExpired": false,
			  "accountLocked": false,
			  "confirmed": false
			},
			{
			  "version": 0,
			  "id": null,
			  "username": "bar_3",
			  "firstName": "BAR",
			  "lastName": "3",
			  "honorific": "Mr",
			  "timezoneId": "America/Chicago",
			  "enabled": true,
			  "accountExpired": false,
			  "accountLocked": false,
			  "confirmed": false
			}
		  ],
		  "last": true,
		  "totalPages": 1,
		  "totalElements": 4,
		  "size": 20,
		  "number": 0,
		  "first": true,
		  "numberOfElements": 4,
		  "sort": null
		}
		""")

		then:
		resultJson == expectedJsonResponse
	}

	def "should search user not equal to baz and order by last name in desc with pagination aspects"() {
		given:
		createUsers()

		when:
		def result = this.mockMvc.perform(post("/api/user/list")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
					{
						"criteria": [
										{
											"key": "firstName",
											"operation": "!",
											"value": "BAZ"
										}
						],
						"pageRequest": {
							"page": 2,
							"size": 3,
							"orders": [
								{
									"property": "lastName",
									"direction": "DESC"
								}
							]
						}
					}
				""".toString()
		)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn()
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		// to clear ids' as they will change for every run
		resultJson.content.each {
			assert it.id != null
			it.id = null
		}
		def expectedJsonResponse = new JsonSlurper().parseText("""
		{
		  "content": [
			{
			  "version": 0,
			  "id": null,
			  "username": "bar_1",
			  "firstName": "BAR",
			  "lastName": "1",
			  "honorific": "Mr",
			  "timezoneId": "America/Chicago",
			  "enabled": true,
			  "accountExpired": false,
			  "accountLocked": false,
			  "confirmed": false
			},
			{
			  "version": 0,
			  "id": null,
			  "username": "foo_0",
			  "firstName": "FOO",
			  "lastName": "0",
			  "honorific": "Mr",
			  "timezoneId": "America/Chicago",
			  "enabled": true,
			  "accountExpired": false,
			  "accountLocked": false,
			  "confirmed": false
			},
			{
			  "version": 0,
			  "id": null,
			  "username": "bar_0",
			  "firstName": "BAR",
			  "lastName": "0",
			  "honorific": "Mr",
			  "timezoneId": "America/Chicago",
			  "enabled": true,
			  "accountExpired": false,
			  "accountLocked": false,
			  "confirmed": false
			}
		  ],
		  "totalElements": 9,
		  "totalPages": 3,
		  "last": true,
		  "size": 3,
		  "number": 2,
		  "sort": [
			{
			  "direction": "DESC",
			  "property": "lastName",
			  "ignoreCase": false,
			  "nullHandling": "NATIVE",
			  "ascending": false
			}
		  ],
		  "numberOfElements": 3,
		  "first": false
		}
		""")

		then:
		resultJson == expectedJsonResponse
	}

	def "should search user with multiple searches"() {
		given:
		createUsers()

		when:
		def result = this.mockMvc.perform(post("/api/user/list")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
					{
						"criteria": [
										{
											"key": "firstName",
											"operation": "_*_",
											"value": "baZ"
										},
										{
											"key": "username",
											"operation": "=",
											"value": "baz_3"
										}
						]
					}
				""".toString()
		)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn()
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)

		then:
		resultJson.content.size() == 1
		resultJson.content[0].username == "baz_3"
	}

	def "should search user which checks for starts with"() {
		given:
		createUsers()

		when:
		def result = this.mockMvc.perform(post("/api/user/list")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
					{
						"criteria": [
										{
											"key": "firstName",
											"operation": "*_",
											"value": "Fo"
										}
						]
					}
				""".toString()
		)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn()
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)

		then:
		resultJson.content.size() == 4
		resultJson.content.collect { it.firstName }.unique() == ["FOO"]
	}

	private createUsers() {
		["foo", "bar", "baz"].each { prefix ->
			(0..3).each {
				def username = "${prefix}_${it}"
				def firstName = prefix.toUpperCase()

				userDao.save(new AppUser(username: username, password: 'Password1', firstName: firstName,
						lastName: it.toString(), timezoneId: TimeZone.default.ID, honorific: 'Mr', roles: [roleDao.findByAuthority("ROLE_FINANCE")],
						accountExpired: false, accountLocked: false, lastCreated: Timestamp.valueOf(LocalDateTime.now()),
						enabled: true))
			}
		}
	}

	private flush() {
		entityManager.flush()
		entityManager.clear()
	}

}
