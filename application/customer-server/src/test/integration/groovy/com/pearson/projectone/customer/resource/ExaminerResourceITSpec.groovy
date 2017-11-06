package com.pearson.projectone.customer.resource

import com.pearson.projectone.customer.BaseCustomerServerIntgTest
import com.pearson.projectone.data.dao.customer.qg.ExaminerDao
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ExaminerResourceITSpec extends BaseCustomerServerIntgTest {

	@Autowired
	ExaminerDao examinerDao;

	@Autowired
	WebApplicationContext webApplicationContext;

	MockMvc mockMvc;

	def setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	def cleanup() {
		examinerDao.deleteAll();
	}


	def "create examiner"() {

		given:
		String examinerJson = this.createExaminer();

		when:
		def createRequest = this.mockMvc.perform(put("/api/examiner").contentType(MediaType.APPLICATION_JSON)
				.content(examinerJson)).andExpect(status().isOk()).andReturn()
		def createRequestJson = new JsonSlurper().parseText(createRequest.response.contentAsString)

		then:
		createRequestJson.id != null

	}


	def "update examiner"() {
		given:
		String examinerJson = this.createExaminer();
		def createRequest = this.mockMvc.perform(put("/api/examiner").contentType(MediaType.APPLICATION_JSON)
				.content(examinerJson)).andExpect(status().isOk()).andReturn()
		def createRequestJson = new JsonSlurper().parseText(createRequest.response.contentAsString)
		createRequestJson.middleName = "Henry";

		when:
		def updateResult = this.mockMvc.perform(post("/api/examiner").contentType(MediaType.APPLICATION_JSON)
				.content(new JsonBuilder(createRequestJson).toString())).andExpect(status().isOk()).andReturn()
		def updateResultJson = new JsonSlurper().parseText(updateResult.response.contentAsString)

		then:
		updateResultJson.id == createRequestJson.id

	}

	def "get examiner"() {
		given:
		String examinerJson = this.createExaminer();
		def createRequest = this.mockMvc.perform(put("/api/examiner").contentType(MediaType.APPLICATION_JSON)
				.content(examinerJson)).andExpect(status().isOk()).andReturn()
		def createRequestJson = new JsonSlurper().parseText(createRequest.response.contentAsString)
		def id = createRequestJson.id

		when:
		def result = this.mockMvc.perform(get("/api/examiner/${id}")).andExpect(status().isOk()).andReturn()
		def resultJosn = new JsonSlurper().parseText(result.response.contentAsString)

		then:
		resultJosn.id != null
		resultJosn.title == "Mr."
		resultJosn.firstName == "Prasad"
		resultJosn.lastName == "Kakarlamudi"
		resultJosn.middleName == ""
		resultJosn.suffix == "Sr."
		resultJosn.examinerId == "ex00067"

	}

	def "delete examiner"() {
		given:
		String examinerJson = this.createExaminer();
		def createRequest = this.mockMvc.perform(put("/api/examiner").contentType(MediaType.APPLICATION_JSON)
				.content(examinerJson)).andExpect(status().isOk()).andReturn()
		def createRequestJson = new JsonSlurper().parseText(createRequest.response.contentAsString)
		def id = createRequestJson.id

		when:
		def deleteRequest = this.mockMvc.perform(delete("/api/examiner/${id}")).andExpect(status().isOk())
		def getRequest = this.mockMvc.perform(get("/api/examiner/${id}"))


		then:
		getRequest.andExpect(status().isNotFound())
	}


	def "list examiners"() {
		given:
		String examinerJson = this.createExaminer();
		def createRequest = this.mockMvc.perform(put("/api/examiner").contentType(MediaType.APPLICATION_JSON)
				.content(examinerJson)).andExpect(status().isOk()).andReturn()
		def createRequestJson = new JsonSlurper().parseText(createRequest.response.contentAsString)


		when:
		def listRequest = this.mockMvc.perform(post("/api/examiner/list").contentType(MediaType.APPLICATION_JSON).content(
				"""
					{
					   "pageRequest":{
						   "orders":[

						   ],
						   "page":0,
						   "size":15
						},
						"criteria":[]
					}
				"""
		)).andExpect(status().isOk()).andReturn()

		def resultJson = new JsonSlurper().parseText(listRequest.response.contentAsString)


		then:
		resultJson.totalPages > 0
		resultJson.content[0].id == createRequestJson.id
		resultJson.content[0].firstName == "Prasad"
		resultJson.content[0].title == "Mr."
		resultJson.content[0].firstName == "Prasad"
		resultJson.content[0].lastName == "Kakarlamudi"
		resultJson.content[0].middleName == ""
		resultJson.content[0].suffix == "Sr."
		resultJson.content[0].examinerId == "ex00067"
	}


	private String createExaminer() {
		return """
			{
			   "title":"Mr.",
			   "firstName":"Prasad",
			   "lastName":"Kakarlamudi",
			   "middleName":"",
			   "suffix":"Sr.",
			   "examinerId":"ex00067"
			}
			""";
	}


}
