package com.pearson.projectone.authcommons.provider.client

import com.pearson.projectone.IntegrationTestApplication
import com.pearson.projectone.data.dao.security.oauth2.client.Oauth2ClientDao
import com.pearson.projectone.data.entity.security.oauth2.client.OauthClientDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Example
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.NoSuchClientException
import org.springframework.security.oauth2.provider.client.BaseClientDetails
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import spock.lang.Specification
import spock.lang.Stepwise

@ContextConfiguration(classes = [IntegrationTestApplication,
		IntegrationTestApplication.TestJpaConfiguration])
@SpringBootTest
@Stepwise
@ActiveProfiles(["integration"])
@TestPropertySource(properties = [
		"config.auth_server=false"
])
class JpaClientDetailsServiceSpec extends Specification {

	@Autowired
	Oauth2ClientDao clientDao

	@Autowired
	PasswordEncoder passwordEncoder

	JpaClientDetailsService service

	@Autowired
	@Qualifier('appSecurityTransactionManager')
	PlatformTransactionManager transactionManager

	def TransactionStatus transactionStatus

	def setup() {
		service = new JpaClientDetailsService(passwordEncoder: passwordEncoder, clientDao: clientDao)
		transactionStatus = transactionManager.getTransaction()
	}

	def cleanup() {
		transactionManager.rollback(transactionStatus)
	}

	def "should throw exception for non existent client"() {
		when: 'get an invalid client'
		service.loadClientByClientId("nonExistentClientId")

		then:
		thrown(NoSuchClientException)
	}

	def "should load client with no details"() {
		given:
		def client = new OauthClientDetails(clientId: 'clientIdWithNoDetails')
		clientDao.save(client)

		when: 'load a client'
		ClientDetails clientDetails = service.loadClientByClientId("clientIdWithNoDetails")

		then:
		!clientDetails.secretRequired
		clientDetails.clientSecret == null
		!clientDetails.scoped
		clientDetails.scope.size() == 0
		clientDetails.authorizedGrantTypes.size() == 2
		clientDetails.registeredRedirectUri == null
		clientDetails.authorities.size() == 0
		clientDetails.accessTokenValiditySeconds == null
		clientDetails.refreshTokenValiditySeconds == null
	}

	def "should load client with additional details"() {
		given:
		def client = new OauthClientDetails(clientId: 'clientIdWithAddInfo')
		clientDao.save(client)
		client.additionalInformation = """
		{
			"foo": "bar"
		}
		"""
		clientDao.save(client)

		when: 'load a client'
		ClientDetails clientDetails = service.loadClientByClientId("clientIdWithAddInfo")

		then:
		clientDetails.clientId == 'clientIdWithAddInfo'
		clientDetails.additionalInformation == [
				foo: 'bar'
		]
	}

	def "should load client with details"() {
		given:
		def client = new OauthClientDetails(clientId: 'clientIdWithDetails', clientSecret: "mysecret",
				resourceIds: 'res1,res2', scope: 'scope1,scope2', authorizedGrantTypes: 'read,write',
				webServerRedirectUri: 'http://projectone.com/token', authorities: 'auth1,auth2',
				accessTokenValidity: 100, refreshTokenValidity: 200, autoApprove: 'true')
		clientDao.save(client)

		when: 'load a client'
		ClientDetails clientDetails = service.loadClientByClientId("clientIdWithDetails")

		then:
		clientDetails.clientId == 'clientIdWithDetails'
		clientDetails.secretRequired
		clientDetails.clientSecret == "mysecret"
		clientDetails.isScoped()
		clientDetails.resourceIds == ['res1', 'res2'].toSet()
		clientDetails.scope == ['scope1', 'scope2'].toSet()
		clientDetails.authorizedGrantTypes == ['read', 'write'].toSet()
		clientDetails.registeredRedirectUri == ['http://projectone.com/token'].toSet()
		clientDetails.authorities.collect { it.role } == ['auth1', 'auth2']
		clientDetails.accessTokenValiditySeconds == 100
		clientDetails.refreshTokenValiditySeconds == 200
		clientDetails.isAutoApprove('read')
		clientDetails.isAutoApprove('write')
	}

	def "should add client with no details"() {
		given:
		ClientDetails clientDetails = new BaseClientDetails(clientId: 'addedClientIdWithNoDetails')

		when:
		service.addClientDetails(clientDetails)
		and: 'get the client from db'
		def daoClient = clientDao.findByClientId('addedClientIdWithNoDetails')

		then:
		daoClient.id != null
		daoClient.clientSecret == null
	}

	def "should throw duplicate exception for adding client with existing client id"() {
		given:
		def client = new OauthClientDetails(clientId: 'duplicateClientId')
		clientDao.save(client)
		ClientDetails clientDetails = new BaseClientDetails(clientId: client.clientId)

		when:
		service.addClientDetails(clientDetails)

		then:
		thrown(ClientAlreadyExistsException)
	}

	def "should update client secret with provided password encoder"() {
		given:
		ClientDetails clientDetails = new BaseClientDetails(clientId: 'addedClientIdWithNoDetails')
		and: 'call add on service'
		service.addClientDetails(clientDetails)

		when:
		service.updateClientSecret(clientDetails.clientId, "password1!")
		and: 'get the client from db'
		def daoClient = clientDao.findByClientId('addedClientIdWithNoDetails')

		then:
		daoClient.clientSecret == 'ENCODED > password1!'
	}

	def "should throw exception when non existent client secret is updated"() {
		when:
		service.updateClientSecret("nonExistentClientId", 'somepassword')

		then:
		thrown(NoSuchClientException)
	}

	def "should update client redirect uri"() {
		given:
		ClientDetails clientDetails = new BaseClientDetails(clientId: 'addedClientIdWithNoDetails')
		and: 'call add on service'
		service.addClientDetails(clientDetails)

		when:
		clientDetails.registeredRedirectUri = [
				'http://localhost:9999',
				'http://localhost:8888'
		].toSet()
		service.updateClientDetails(clientDetails)
		and: 'get the client from db'
		def daoClient = clientDao.findByClientId('addedClientIdWithNoDetails')

		then:
		daoClient.webServerRedirectUri == 'http://localhost:8888,http://localhost:9999'
	}

	def "should throw exception when non existent client is updated"() {
		when:
		ClientDetails clientDetails = new BaseClientDetails(clientId: 'nonExistentClientId')
		service.updateClientDetails(clientDetails)

		then:
		thrown(NoSuchClientException)
	}

	def "should remove client"() {
		given:
		ClientDetails clientDetails = new BaseClientDetails(clientId: 'addedClientIdWithNoDetails')
		and: 'call add on service'
		service.addClientDetails(clientDetails)

		when:
		service.removeClientDetails(clientDetails.clientId)
		and: 'get the count from db for client id'
		def count = clientDao.count(Example.of(new OauthClientDetails(clientId: clientDetails.clientId)))

		then:
		count == 0
	}

	def "should throw exception when non existent client is removed"() {
		when:
		service.removeClientDetails('nonExistentClientId')

		then:
		thrown(NoSuchClientException)
	}

	def "should return all clients"() {
		given:
		['clientId1', 'clientId2'].each {
			service.addClientDetails(new BaseClientDetails(clientId: it))
		}

		when:
		def clients = service.listClientDetails()

		then:
		clients.collect { it.clientId } == [
				'clientId1', 'clientId2'
		]
	}

}
