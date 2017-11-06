package com.pearson.projectone.core.support.data.utils

import org.springframework.core.env.Environment
import spock.lang.Specification

class EnvironmentAwareMongoDbBuilderSpec extends Specification {

	def "should throw assertion error if mongouri not provided"() {
		given:
		def builder = new EnvironmentAwareMongoDbBuilder()

		when:
		builder.prepareMongoDataSource([:])

		then:
		thrown(AssertionError)
	}

	def "should set mongo ur into mongo db factory"() {
		given:
		def builder = new EnvironmentAwareMongoDbBuilder(
				environment: Stub(Environment) {
					getActiveProfiles() >> {
						[]
					}
				}
		)

		when:
		def factory = builder.prepareMongoDataSource(['mongo-uri': 'mongodb://mymongo:21111/mydb'])

		then:
		factory.databaseName == 'mydb'
		factory.mongo.cluster.server.serverAddress.toString() == "mymongo:21111"
	}

}
