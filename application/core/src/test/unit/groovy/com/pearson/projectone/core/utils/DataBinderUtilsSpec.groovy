package com.pearson.projectone.core.utils

import spock.lang.Specification

class DataBinderUtilsSpec extends Specification {
	class SampleDTO {
		def name
		def apartment
		def age
		def enabled = true

		SampleDTO() {

		}
	}

	def "should bind properties"() {
		given:
		def source = [name: 'Emp', id: 1000, dept: 'DPDg']
		def target = [gender: 'M', dept: 'ENGG']

		when:
		DataBinderUtils.populateObjectFromProperties(target, source)

		then:
		target == [
				name  : 'Emp',
				id    : 1000,
				dept  : 'DPDg',
				gender: 'M'
		]
	}

	def "should bind properties to object"() {
		given:
		def source = [name: 'Emp', id: 1000, apartment: 'DPDg', age: 66]
		def target = new SampleDTO()

		when:
		DataBinderUtils.populateObjectFromProperties(target, source)

		then:
		target.name == 'Emp'
		target.age == 66
		target.enabled
		target.apartment == 'DPDg'
	}
}
