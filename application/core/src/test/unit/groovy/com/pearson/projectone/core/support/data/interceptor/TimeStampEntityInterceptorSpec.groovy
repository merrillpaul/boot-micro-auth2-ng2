package com.pearson.projectone.core.support.data.interceptor

import com.pearson.projectone.core.support.data.RelationalEntity
import spock.lang.Specification

import java.sql.Timestamp
import java.time.LocalDateTime

class TimeStampEntityInterceptorSpec extends Specification {

	private static class SampleEntity extends RelationalEntity {
		String description
	}

	def "should set current time into last created and updated fields while creating an entity"() {
		given:
		def entity = new SampleEntity(description: "Some desc")

		when:
		new TimeStampEntityInterceptor().beforeInsert(entity)

		then:
		entity.lastCreated != null
		entity.lastUpdated != null
	}

	def "should set current time only into last updated field while updating an entity"() {
		given:
		def createdDate = LocalDateTime.of(2017, 12, 10, 10, 30, 0)
		def entity = new SampleEntity(description: "Some desc")
		entity.lastCreated = Timestamp.valueOf(createdDate)

		when:
		new TimeStampEntityInterceptor().beforeUpdate(entity)

		then:
		entity.lastCreated == Timestamp.valueOf(createdDate)
		entity.lastUpdated != Timestamp.valueOf(createdDate)
	}
}
