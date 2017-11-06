package com.pearson.projectone.customer.resource

import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO
import com.pearson.projectone.core.support.data.search.SearchCriteriaDTO
import com.pearson.projectone.customer.BaseCustomerServerIntgTest
import com.pearson.projectone.customer.dto.examinee.ExamineeDTO
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by ukakapr on 6/6/17.
 */

public class ExamineeResourceITSpec extends BaseCustomerServerIntgTest {

	@Autowired
	WebApplicationContext webApplicationContext

	MockMvc mockMvc;

	private generatedExamineeId = null;

	def setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
	}


	def "Create new examinee"() {
		when:

		def result = this.mockMvc.perform(put("/api/examinee").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("""
				{
				   "id":null,
				   "firstName":"Prasad",
				   "lastName":"Kakarlamudi",
				   "middleName":"H",
				   "gender":"MALE",
				   "dob":1496778551515,
				   "examineeId":"0001"
				}
				""".toString())).andExpect(status().isOk())
				.andReturn();

		def createdExaminee = new JsonSlurper().parseText(result.response.contentAsString)

		then:
		createdExaminee.id != null
		createdExaminee.version != null
	}


	def "Get Examinee by Id"() {


		when:
		def result = this.mockMvc.perform(put("/api/examinee").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("""
				{
				   "id":null,
				   "firstName":"Prasad",
				   "lastName":"Kakarlamudi",
				   "middleName":"H",
				   "gender":"MALE",
				   "dob":1496778551515,
				   "examineeId":"0001"
				}
				""".toString())).andExpect(status().isOk())
				.andReturn();
		def examinee = new JsonSlurper().parseText(result.response.contentAsString)
		def getResult = this.mockMvc.perform(get("/api/examinee/${examinee.id}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andReturn();
		def examineeDTO = new JsonSlurper().parseText(getResult.response.contentAsString)
		then:
		examineeDTO.id == examinee.id;
		examineeDTO.firstName == "Prasad";
		examineeDTO.lastName == "Kakarlamudi";
		examineeDTO.gender == "MALE";
		examineeDTO.dob == 1496778551515;
		examineeDTO.examineeId == "0001";
	}

	def "Get Examinee by Id (BAD ID)"() {

		when:

		def examineeId = "BAD_ID0000001"
		def getResult = this.mockMvc.perform(get("/api/examinee/${examineeId}"))
				.andExpect(status().isNotFound())
				.andReturn();

		then:
		getResult.response.status == 404

	}

	def "List Examinees"() {

		given:
		ExamineeDTO examineeDTO = new ExamineeDTO(null, "Ruth", "Kakarlamudi", "H", "FEMALE", new Date(), "0001")


		def createdExamineeResponse = this.mockMvc.perform(put("/api/examinee").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new JsonBuilder(examineeDTO).toString())).andExpect(status().isOk()).andReturn();

		def createdExaminee = new JsonSlurper().parseText(createdExamineeResponse.response.contentAsString);

		when:
		List<SearchCriteriaDTO> criteria = Arrays.asList(
				new SearchCriteriaDTO("firstName", "_*_", "Ruth"),
				new SearchCriteriaDTO("gender", "=", "FEMALE")
		);

		PageableSearchRequestDTO pageableSearchRequestDTO = new PageableSearchRequestDTO(new PageRequest(0, 10), criteria);
		println new JsonBuilder(pageableSearchRequestDTO).toPrettyString();
		def result = this.mockMvc.perform(post("/api/examinee/list").contentType(MediaType.APPLICATION_JSON)
				.content(new JsonBuilder(pageableSearchRequestDTO).toString().replaceAll("EQUALITY", "=").replaceAll("CONTAINS", "_*_"))
		)
				.andExpect(status().isOk())
				.andExpect(content()
				.contentType("application/json;charset=UTF-8"))
				.andReturn();
		println result.response.contentAsString

		def examineePageJson = new JsonSlurper().parseText(result.response.contentAsString)
		println examineePageJson.content[0]
		then:
		examineePageJson.numberOfElements == 1
		examineePageJson.totalElements == 1
		examineePageJson.content[0].firstName == "Ruth"
		examineePageJson.content[0].gender == "FEMALE"
		createdExaminee.id == examineePageJson.content[0].id
	}

	def "Update Examinee"() {
		when:

		def result = this.mockMvc.perform(put("/api/examinee").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("""
					{"id":null,"firstName":"Prasad","lastName":"Kakarlamudi","middleName":"H","gender":"MALE",
					"dob":1496778551515,"examineeId":"0001"}
				""".toString())).andExpect(status().isOk())
				.andReturn();

		def examinee = new JsonSlurper().parseText(result.response.contentAsString)
		String examineeJson = """
					{"id":"${examinee.id}","firstName":"Ruth","lastName":"Kakarlamudi","middleName":"Henry",
				"gender":"MALE","dob":1496778553030,"examineeId":"0001"}
				""".toString()
		def updatedExamiee = this.mockMvc.perform(post("/api/examinee/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(examineeJson))
				.andExpect(status().isOk())
				.andReturn();


		def retrievedExaminee = this.mockMvc.perform(get("/api/examinee/${examinee.id}")).andExpect(status().isOk())
				.andReturn();
		def examineeDTO = new JsonSlurper().parseText(retrievedExaminee.response.contentAsString);


		then:
		examineeDTO.id == examinee.id;
		examineeDTO.firstName == "Ruth";
		examineeDTO.middleName == "Henry";
		examineeDTO.dob == 1496778553030;
		examineeDTO.examineeId == "0001";
	}

	def "Update Examinee (DTO with BAD ID)"() {
		when:


		String examineeJson = """
					{"id":"BAD_ID000001","firstName":"Ruth","lastName":"Kakarlamudi","middleName":"Henry",
				"gender":"MALE","dob":1496778553030,"examineeId":"0001"}
				""".toString()
		def updatedExamiee = this.mockMvc.perform(post("/api/examinee/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(examineeJson))
				.andExpect(status()
				.isNotFound())
				.andReturn();

		then:

		updatedExamiee.response.status == 404
	}

	def "Delete Examinee by Id"() {
		def result = this.mockMvc.perform(put("/api/examinee").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("""
					{"id":null,"firstName":"Prasad","lastName":"Kakarlamudi","middleName":"H","gender":"MALE",
					"dob":1496778551515,"examineeId":"0001"}
				""".toString())).andExpect(status().isOk())
				.andReturn();

		def examinee = new JsonSlurper().parseText(result.response.contentAsString);
		def examineeId = examinee.id;
		def deleteResponse = this.mockMvc.perform(delete("/api/examinee/${examineeId}"))
				.andExpect(status().isOk())
				.andReturn();
		this.mockMvc.perform(get("/api/examinee/${examineeId}")).andExpect(status().isNoContent());

	}

	def "Delete Examinee by Id (Bad Request"() {

		when:
		def examineeId = "BAD_ID0100001"
		def deleteResponse = this.mockMvc.perform(delete("/api/examinee/${examineeId}"))
				.andExpect(status()
				.isNotFound())
				.andReturn();

		then:
		deleteResponse.response.status == 404
	}
}