package com.pearson.projectone.core.support.data.interceptor.trackchanges

import com.pearson.projectone.core.support.data.DocumentEntity
import com.pearson.projectone.core.support.data.RelationalEntity
import org.springframework.util.ReflectionUtils
import spock.lang.Specification

class TrackChangesEntityInterceptorSpec extends Specification {
	@TrackChanges
	class EntityWithTrackables extends RelationalEntity {
		@TrackChanges
		def name

		@TrackChanges
		def description

	}


	class NonTrackingEntity extends DocumentEntity {
		def name

		def dept
	}

	@TrackChanges
	class EntityMarkedAsTrackableWithNoRealTrackables extends RelationalEntity {
		def name
	}

	def "should call clear the track changes beforeFlushForUpdate"() {
		given:
		def beforeUpdateCalled = false
		def entity = Spy(EntityWithTrackables) {
			onBeforeUpdate() >> {
				beforeUpdateCalled = true
			}
		}
		def trackerInfo = new TrackerInfo()
		trackerInfo.oldValues = [id: 'id0001', name: 'Name1']
		trackerInfo.changedValues = [id: 'id1111', name: 'Name2']
		setTrackerInfo(entity, trackerInfo)

		when:
		new TrackChangesEntityInterceptor().beforeUpdate(entity)

		then:
		beforeUpdateCalled
		trackerInfo.changedValues == [:]
		trackerInfo.oldValues == [:]
	}

	def "should call trackable's onBeforeInsert"() {
		given:
		def onBeforeCalled = false
		def entity = Spy(EntityWithTrackables) {
			onBeforeInsert() >> {
				onBeforeCalled = true
			}
		}

		when:
		new TrackChangesEntityInterceptor().beforeInsert(entity)

		then:
		onBeforeCalled
	}

	def "should call trackable's onBeforeDelete"() {
		given:
		def onBeforeCalled = false
		def entity = Spy(EntityWithTrackables) {
			onBeforeDelete() >> {
				onBeforeCalled = true
			}
		}

		when:
		new TrackChangesEntityInterceptor().beforeDelete(entity)

		then:
		onBeforeCalled
	}

	def "should populate trackable's old values"() {
		given:
		def trackable1 = new EntityWithTrackables(name: "trackable-name", id: 'A1000', description: 'trackable-desc')
		def trackerInfo1 = new TrackerInfo()
		setTrackerInfo(trackable1, trackerInfo1)
		def trackable2 = new NonTrackingEntity(name: "non-trackable-name", dept: 'non-A1000')
		def trackerInfo2 = new TrackerInfo()
		setTrackerInfo(trackable2, trackerInfo2)
		def trackable3 = new EntityMarkedAsTrackableWithNoRealTrackables(name: "non-trackable-name1", id: 'non-A1001')
		def trackerInfo3 = new TrackerInfo()
		setTrackerInfo(trackable3, trackerInfo3)
		def listener = new TrackChangesEntityInterceptor()

		when:
		listener.afterLoad(trackable1)
		listener.afterLoad(trackable2)
		listener.afterLoad(trackable3)

		then:
		trackerInfo1.oldValues == [
				name       : 'trackable-name',
				description: 'trackable-desc'
		]
		trackerInfo2.oldValues == [:]
		trackerInfo3.oldValues == [:]
		trackerInfo1.changedValues == [:]
		trackerInfo2.changedValues == [:]
		trackerInfo3.changedValues == [:]
	}

	def "should populate trackable's new values"() {
		given:
		def trackable1 = new EntityWithTrackables(name: "trackable-name", id: 'A1000', description: 'trackable-desc')
		def trackerInfo1 = new TrackerInfo(oldValues: [
				name       : 'trackable-name',
				description: 'trackable-desc'
		])
		setTrackerInfo(trackable1, trackerInfo1)
		def trackable2 = new NonTrackingEntity(name: "non-trackable-name", dept: 'non-A1000')
		def trackerInfo2 = new TrackerInfo(oldValues: [
				name: 'not-actually-populated',
				dept: 'no-dept-populated'
		])
		setTrackerInfo(trackable2, trackerInfo2)
		def trackable3 = new EntityMarkedAsTrackableWithNoRealTrackables(name: "non-trackable-name1", id: 'non-A1001')
		def trackerInfo3 = new TrackerInfo(oldValues: [
				name: 'non-populated-name',
				id  : 'non-populated-id'
		])
		setTrackerInfo(trackable3, trackerInfo3)
		def listener = new TrackChangesEntityInterceptor()

		when:
		trackable1.description = 'changed-desc'
		trackable1.id = 'changed-id'
		trackable1.name = 'changed-name'
		trackable2.name = 'changed-name'
		trackable2.dept = 'changed-dept'
		trackable3.name = 'changed-name'
		trackable3.id == 'changed-id'
		def entities = [
				trackable1, trackable2, trackable3
		].each {
			listener.beforeFlushForUpdate(it)
		}

		then:
		trackerInfo1.oldValues == [
				name       : 'trackable-name',
				description: 'trackable-desc'
		]
		trackerInfo1.changedValues == [
				name       : 'changed-name',
				description: 'changed-desc'
		]
		trackerInfo2.changedValues == [:]
		trackerInfo3.changedValues == [:]
		trackable1.hasChanged()
		!trackable2.hasChanged()
		!trackable3.hasChanged()
		trackable1.isDirty('name')
		trackable1.isDirty('description')
		!trackable1.isDirty('id')
		trackable1.listDirtyPropertyNames().sort() == ['description', 'name']
		trackable2.listDirtyPropertyNames().size() == 0
		trackable3.listDirtyPropertyNames().size() == 0
		trackable1.getOriginalValue('name') == 'trackable-name'
	}

	private setTrackerInfo(entity, TrackerInfo trackerInfo) {
		def field = ReflectionUtils.getDeclaredFields(TrackableEntity)
				.find { it.name == 'trackerInfo' }
		field.setAccessible(true)
		field.set(entity, trackerInfo)
	}
}
