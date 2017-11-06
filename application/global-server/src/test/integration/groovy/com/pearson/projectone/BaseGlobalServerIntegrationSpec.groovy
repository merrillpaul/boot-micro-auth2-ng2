package com.pearson.projectone

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import spock.lang.Specification
import spock.lang.Stepwise

@ContextConfiguration(classes = [GlobalServerIntegrationTestApplication,
		GlobalServerIntegrationTestApplication.TestJpaConfiguration])
@SpringBootTest
@Stepwise
@ActiveProfiles(["integration"])
@TestPropertySource(properties = [
		"config.auth_server=false"
])
@WebAppConfiguration(value = "")
abstract class BaseGlobalServerIntegrationSpec extends Specification {
	@Autowired
	@Qualifier('globalTransactionManager')
	PlatformTransactionManager transactionManager

	def TransactionStatus transactionStatus

	def setup () {
		transactionStatus = transactionManager.getTransaction()
	}

	def cleanup () {
		transactionManager.rollback(transactionStatus)
	}
}

