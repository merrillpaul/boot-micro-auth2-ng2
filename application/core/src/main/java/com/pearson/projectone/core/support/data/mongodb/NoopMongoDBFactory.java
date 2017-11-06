package com.pearson.projectone.core.support.data.mongodb;

import com.mongodb.DB;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDbFactory;

/**
 * To prevent auto configuration of
 * {@link org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration}
 * using the {@link org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean}
 * in non-mongo based apps. This is because we have spring-data-mongo-db in the classpath
 * resolved through core for common mongo aspects. However we do not want the mongodb repositories
 * or factories to be loaded when they are needed. We tried adding exclude list in the
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration} as per
 * http://stackoverflow.com/questions/28747909/how-to-disable-spring-data-mongodb-autoconfiguration-in-spring-boot
 * which did not work. We have the same situatio where we want to control the loading of mongodb factory instead
 * of the spring-data-mongo-db loading the factory for us. This is accomplished using our
 * {@link com.pearson.projectone.core.support.data.utils.EnvironmentAwareMongoDbBuilder}
 */
public class NoopMongoDBFactory implements MongoDbFactory {
	@Override
	public DB getDb() throws DataAccessException {
		return null;
	}

	@Override
	public DB getDb(String dbName) throws DataAccessException {
		return null;
	}

	@Override
	public PersistenceExceptionTranslator getExceptionTranslator() {
		return null;
	}
}
