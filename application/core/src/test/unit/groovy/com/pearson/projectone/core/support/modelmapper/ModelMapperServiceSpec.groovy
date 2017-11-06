package com.pearson.projectone.core.support.modelmapper

import com.pearson.projectone.core.support.data.AbstractDTO
import com.pearson.projectone.core.support.data.RelationalEntity
import org.modelmapper.ModelMapper
import spock.lang.Specification

class ModelMapperServiceSpec extends Specification {


	def service = new ModelMapperService(modelMapper: new ModelMapper())

	def "should convert entity to dto and copy props"() {
		given:
		def entity = new SampleEntity(name: "ENT", age: 33, dob: new Date(), dept: 'DPDG')

		when:
		def dto = service.convertToDTO(entity, SampleDTO)

		then:
		dto.name == entity.name
		dto.age == 33
		dto.dob == entity.dob
	}

	def "should convert dto to entity and copy props"() {
		given:
		def dto = new SampleDTO(name: "ENT", age: 33, dob: new Date())

		when:
		def entity = service.convertToEntity(dto, SampleEntity)

		then:
		entity.name == entity.name
		entity.age == 33
		entity.dob == entity.dob
		entity.dept == null
	}
}


public class SampleEntity extends RelationalEntity {
	String name
	Integer age
	Date dob
	String dept

	public SampleEntity() {}
}

public class SampleDTO extends AbstractDTO {
	String name
	int age
	Date dob

	public SampleDTO() {}
}