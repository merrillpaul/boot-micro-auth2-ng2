package com.pearson.projectone.core.support.data.utils

import com.mongodb.MongoClientURI
import com.pearson.projectone.core.support.data.mongodb.JpaAvoidingMappingContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.stereotype.Component

/**
 * A helper to provide a MongoDB factory nstance which knows about the app environment.
 * If the app is executed on local dev ( intelliJ ), this cleans the local mongo db based on the properties
 * provided.
 *
 * A typical example of the dynamo db properties in yml format:
 * <code>
 *     	tokenDatasource:
 * 			mongo-uri: mongodb://tokenstore_user:Password1!@localhost:27017/tokenstoredb
 * </code>
 *
 */
@Component
class EnvironmentAwareMongoDbBuilder {
	@Autowired
	Environment environment

	private static final MONGO_URI = 'mongo-uri'

	def MongoDbFactory prepareMongoDataSource(def config) {
		assert config[MONGO_URI] != null
		def factory
		factory = new SimpleMongoDbFactory(new MongoClientURI(config[MONGO_URI]))
		if (environment.activeProfiles.contains('dev') || environment.activeProfiles.contains('integration')) {
			// we cleanup the db
			factory.db.getCollectionNames().each {
				println "Dropping Mongo Collection ${it} in dev mode"
				factory.db.getCollection(it).drop()
			}
		}
		factory
	}

	def MongoTemplate prepareMongoTemplate(MongoDbFactory factory) {
		def dbRefResolver = new DefaultDbRefResolver(factory)
		def converter = new MappingMongoConverter(dbRefResolver, new JpaAvoidingMappingContext())
		converter.afterPropertiesSet()
		new MongoTemplate(factory, converter)
	}
}
