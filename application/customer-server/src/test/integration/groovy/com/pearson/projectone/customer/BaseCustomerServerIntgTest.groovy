package com.pearson.projectone.customer

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Stepwise

@ContextConfiguration(classes = [CustomerServerIntegrationSpecApplication,
		CustomerServerIntegrationSpecApplication.TestCustomerSchemaConfig])
@SpringBootTest
@Stepwise
@ActiveProfiles(["integration"])
abstract class BaseCustomerServerIntgTest extends Specification {
}
