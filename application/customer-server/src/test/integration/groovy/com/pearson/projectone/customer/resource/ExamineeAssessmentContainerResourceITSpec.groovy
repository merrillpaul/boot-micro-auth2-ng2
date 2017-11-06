package com.pearson.projectone.customer.resource

import com.pearson.projectone.customer.BaseCustomerServerIntgTest
import com.pearson.projectone.data.dao.customer.ExamineeDao
import com.pearson.projectone.data.dao.customer.qg.ExamineeAssessmentContainerDao
import com.pearson.projectone.data.entity.customer.examinee.Examinee
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ExamineeAssessmentContainerResourceITSpec extends BaseCustomerServerIntgTest {

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	ExamineeAssessmentContainerDao examineeAssessmentContainerDao;

	@Autowired
	ExamineeDao examineeDao;


	MockMvc mockMvc;

	def setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
	}

	def "create examinee assessment container"() {

		given:
		String examinerId = this.createExaminerAndReturnId();
		Examinee examinee = this.createExaminee();

		String examineeAssessmentJson = this.getExamineeAssessmentContainerJson(examinee.getId(), examinerId)

		when:
		def result = this.mockMvc.perform(put("/api/examineeAssessmentContainer").contentType(MediaType.APPLICATION_JSON)
				.content(examineeAssessmentJson)).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)

		then:
		resultJson.examineeAssessmentIds[0]["ids"][0] != null
		resultJson.examineeAssessmentIds[0]["groupId"] == "BASC-3-PRS-Child"


	}

	def "create Examinee with invlaid examinee id"() {

		given:
		String examineeAssessmentJson = this.getExamineeAssessmentContainerJson("INVALID-ID", "INVALID-ID")

		when:

		def createRequest = this.mockMvc.perform(put("/api/examineeAssessmentContainer")
				.contentType(MediaType.APPLICATION_JSON).content(examineeAssessmentJson))

		then:
		createRequest.andExpect(status().isBadRequest());

	}

	def "get examinee assessment container with given search"() {

		given:
		Examinee examinee = this.createExaminee();
		String examinerId = this.createExaminerAndReturnId();

		String examineeAssessmentJson = this.getExamineeAssessmentContainerJson(examinee.getId(), examinerId)

		when:
		def result = this.mockMvc.perform(put("/api/examineeAssessmentContainer").contentType(MediaType.APPLICATION_JSON)
				.content(examineeAssessmentJson)).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		def getResult = this.mockMvc.perform(post("/api/examineeAssessmentContainer/default").contentType(MediaType.APPLICATION_JSON_VALUE).content(
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
		))
				.andExpect(status().isOk()).andReturn()
		def getResultJson = new JsonSlurper().parseText(getResult.response.contentAsString)

		then:
		resultJson.examineeAssessmentIds[0]["ids"][0] != null
		resultJson.examineeAssessmentIds[0]["groupId"] == "BASC-3-PRS-Child"
		resultJson.id != null
		getResultJson.content["id"].contains(resultJson.id)
	}

	def "most recent get examinee assessment containers with given search"() {
		given:
		Examinee examinee = this.createExaminee();
		String examinerId = this.createExaminerAndReturnId();

		String examineeAssessmentJson = this.getExamineeAssessmentContainerJson(examinee.getId(), examinerId)

		when:
		def result = this.mockMvc.perform(put("/api/examineeAssessmentContainer")
				.contentType(MediaType.APPLICATION_JSON).content(examineeAssessmentJson))
				.andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		def getResult = this.mockMvc.perform(post("/api/examineeAssessmentContainer/recent")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(
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
		))
				.andExpect(status().isOk()).andReturn()
		def getResultJson = new JsonSlurper().parseText(getResult.response.contentAsString)

		then:
		resultJson.examineeAssessmentIds[0]["ids"][0] != null
		resultJson.examineeAssessmentIds[0]["groupId"] == "BASC-3-PRS-Child"
		resultJson.id != null
		getResultJson.content["id"].contains(resultJson.id)


	}

	def "get examinee assessment container"() {

		given:
		Examinee examinee = this.createExaminee();
		String examinerId = this.createExaminerAndReturnId();

		String examineeAssessmentJson = this.getExamineeAssessmentContainerJson(examinee.getId(), examinerId)

		when:
		def result = this.mockMvc.perform(put("/api/examineeAssessmentContainer").contentType(MediaType.APPLICATION_JSON)
				.content(examineeAssessmentJson)).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		def getResult = this.mockMvc.perform(get("/api/examineeAssessmentContainer/${resultJson.id}"))
				.andExpect(status().isOk()).andReturn()
		def getResultJson = new JsonSlurper().parseText(getResult.response.contentAsString)

		then:
		resultJson.examineeAssessmentIds[0]["ids"][0] != null
		resultJson.examineeAssessmentIds[0]["groupId"] == "BASC-3-PRS-Child"
		resultJson.id != null
		getResultJson["id"] == resultJson.id
		getResultJson["examineeId"] == examinee.getId()
	}

	def "delete Examinee Assessment Container"() {
		given:
		Examinee examinee = this.createExaminee();
		String examinerId = this.createExaminerAndReturnId();
		String examineeAssessmentJson = this.getExamineeAssessmentContainerJson(examinee.getId(), examinerId)

		when:
		def result = this.mockMvc.perform(put("/api/examineeAssessmentContainer")
				.contentType(MediaType.APPLICATION_JSON).content(examineeAssessmentJson))
				.andExpect(status().isOk()).andReturn();

		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		def deleteRequest = this.mockMvc.perform(delete("/api/examineeAssessmentContainer/${resultJson.id}"))
				.andExpect(status().isOk()).andReturn()

		def assessmentId = resultJson["examineeAssessmentIds"]["ids"][0][0]
		def deletedAssessmentGet = this.mockMvc.perform(get("/api/examineeAssessment/${assessmentId}"))
				.andExpect(status().isNotFound())

		def deletedContainerRequest = this.mockMvc
				.perform(get("/api/examineeAssessmentContainer/${resultJson.id}"))

		then:
		deletedContainerRequest.andExpect(status().isNotFound())
	}

	def "update examinee assessment container add new assessment"() {

		given:
		String examinerId = this.createExaminerAndReturnId();
		Examinee examinee = this.createExaminee();

		String examineeAssessmentJson = this.getExamineeAssessmentContainerJson(examinee.getId(), examinerId)

		when:

		def result = this.mockMvc.perform(put("/api/examineeAssessmentContainer").contentType(MediaType.APPLICATION_JSON)
				.content(examineeAssessmentJson)).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		println "Container Id:" + resultJson.id
		println new JsonBuilder(resultJson).toPrettyString()
		println "Assessment id:" + resultJson.examineeAssessmentIds[0]["ids"][0]
		def updateResult = this.mockMvc.perform(post("/api/examineeAssessmentContainer/update").contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
				{
					"id":"${resultJson.id}",
				   "examineeId":"${examinee.id}",
				   "examineeAssessmentHolders":[
					  {
						 "groupId":"BASC-3-PRS-Child",
						 "name":"BASC-3-PRS-Child",
						 "examineeAssessments":[
							{
							   "version":null,
							   "id":"${resultJson.examineeAssessmentIds[0]['ids'][0]}",
							   "formId":"BASC-3-PRS-Child",
							   "adminstrationDate":${new Date().getTime()},
							   "examinerId":"${examinerId}",
							   "deliveryTypeId":"24342653623263",
							   "status":"ASSESSMENT_STATUS.Ready.for.Administration",
							   "rater":{
								  "firstName":"John",
								  "lastName":"Mark",
								  "email":"john.mark@gmail.com"
							   },
							   "subtestIds":[
								  "5942dcfd6034fbab245e47bc1",
								  "5942dcfd6034fbab245e47bc2",
								  "5942dcfd6034fbab245e47bc3"
							   ],
							   "bundleVariables":null
							},
							{
							   "version":null,
							   "id":null,
							   "formId":"BASC-3-PRS-Child",
							   "adminstrationDate":"${new Date().getTime()}",
							   "examinerId":"${examinerId}",
							   "deliveryTypeId":"24342653623263",
							   "status":"",
							   "rater":{
								  "firstName":"Ruth",
								  "lastName":"Antonio",
								  "email":"ruth.antonio@gmail.com"
							   },
							   "subtestIds":[
								  "5942dcfd6034fbab245e47bc1",
								  "5942dcfd6034fbab245e47bc2"
							   ],
							   "bundleVariables":null
							}
														
						 ]
					  }
				   ]
				}
			"""

		)).andExpect(status().isOk()).andReturn();
		def updateResultJson = new JsonSlurper().parseText(updateResult.response.contentAsString)
		println new JsonBuilder(updateResultJson).toPrettyString()
		def assessmentRequest = this.mockMvc.perform(
				get("/api/examineeAssessment/${updateResultJson.examineeAssessmentIds[0]["ids"][1]}"))
				.andExpect(status().isOk()).andReturn()

		def assessmentJson = new JsonSlurper().parseText(assessmentRequest.response.contentAsString)
		println "Assessment"
		println new JsonBuilder(assessmentJson).toPrettyString()

		then:
		resultJson.id == updateResultJson.id
		resultJson.examineeAssessmentIds[0]["ids"].size() == 1
		updateResultJson.examineeAssessmentIds[0]["ids"].size() == 2
		updateResultJson.examineeAssessmentIds[0]["ids"].contains(resultJson.examineeAssessmentIds[0]["ids"][0])
		resultJson.examineeAssessmentIds[0]["ids"][0] != null
		resultJson.examineeAssessmentIds[0]["groupId"] == "BASC-3-PRS-Child"

		assessmentJson.deliveryTypeId == "24342653623263"
		assessmentJson.formId == "BASC-3-PRS-Child"
		assessmentJson.rater["firstName"] == "Ruth"
		assessmentJson.rater["lastName"] == "Antonio"
		assessmentJson.rater["email"] == "ruth.antonio@gmail.com"
		assessmentJson.status == "ASSESSMENT_STATUS.Ready.for.Administration"
		assessmentJson.subtestIds.contains("5942dcfd6034fbab245e47bc1")


	}

	def "update examinee assessment container add new group"() {

		given:
		String examinerId = this.createExaminerAndReturnId();
		Examinee examinee = this.createExaminee();

		String examineeAssessmentJson = this.getExamineeAssessmentContainerJson(examinee.getId(), examinerId)

		when:

		def result = this.mockMvc.perform(put("/api/examineeAssessmentContainer").contentType(MediaType.APPLICATION_JSON)
				.content(examineeAssessmentJson)).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		println "Container Id:" + resultJson.id
		println new JsonBuilder(resultJson).toPrettyString()
		println "Assessment id:" + resultJson.examineeAssessmentIds[0]["ids"][0]
		def updateResult = this.mockMvc.perform(post("/api/examineeAssessmentContainer/update").contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
				{
					"id":"${resultJson.id}",
				   "examineeId":"${examinee.id}",
				   "examineeAssessmentHolders":[
					  {
						 "groupId":"BASC-3-PRS-Child",
						 "name":"BASC-3-PRS-Child",
						 "examineeAssessments":[
							{
							   "version":null,
							   "id":"${resultJson.examineeAssessmentIds[0]['ids'][0]}",
							   "formId":"BASC-3-PRS-Child",
							   "adminstrationDate":${new Date().getTime()},
							   "examinerId":"${examinerId}",
							   "deliveryTypeId":"24342653623263",
							   "status":"ASSESSMENT_STATUS.Ready.for.Administration",
							   "rater":{
								  "firstName":"John",
								  "lastName":"Mark",
								  "email":"john.mark@gmail.com"
							   },
							   "subtestIds":[
								  "5942dcfd6034fbab245e47bc1",
								  "5942dcfd6034fbab245e47bc2",
								  "5942dcfd6034fbab245e47bc3"
							   ],
							   "bundleVariables":null
							}
														
						 ]
					  },
					  {
						   "groupId":"WISC-V",
						   "name":"WISC-V",
						   "examineeAssessments":[
							  {
								 "version":null,
								 "id":null,
								 "formId":"",
								 "adminstrationDate":"${new Date().getTime()}",
								 "examinerId":"${examinerId}",
								 "deliveryTypeId":"24342653623263",
								 "status":"",
								 "rater":{
									"firstName":"Ruth",
									"lastName":"Antonio",
									"email":"ruth.antonio@gmail.com"
								 },
								 "subtestIds":[
									"5942dcfd6034fbab245e47b8999",
									"5942dcfd6034fbab245e47bc2"
								 ],
								 "bundleVariables":null
							  }
						   ]
						}
				   ]
				}
			"""

		)).andExpect(status().isOk()).andReturn();
		def updateResultJson = new JsonSlurper().parseText(updateResult.response.contentAsString)
		println new JsonBuilder(updateResultJson).toPrettyString()
		def assessmentRequest = this.mockMvc.perform(
				get("/api/examineeAssessment/${updateResultJson.examineeAssessmentIds[1]["ids"][0]}"))
				.andExpect(status().isOk()).andReturn()

		def assessmentJson = new JsonSlurper().parseText(assessmentRequest.response.contentAsString)
		println "Assessment"
		println new JsonBuilder(assessmentJson).toPrettyString()

		then:
		resultJson.id == updateResultJson.id
		resultJson.examineeAssessmentIds[0]["ids"].size() == 1
		updateResultJson.examineeAssessmentIds[0]["ids"].size() == 1
		updateResultJson.examineeAssessmentIds[1]["ids"].size() == 1
		updateResultJson.examineeAssessmentIds[0]["ids"].contains(resultJson.examineeAssessmentIds[0]["ids"][0])
		resultJson.examineeAssessmentIds[0]["ids"][0] != null
		resultJson.examineeAssessmentIds[0]["groupId"] == "BASC-3-PRS-Child"

		updateResultJson.examineeAssessmentIds[0]["groupId"] == "BASC-3-PRS-Child"
		updateResultJson.examineeAssessmentIds[1]["groupId"] == "WISC-V"
		updateResultJson.examineeAssessmentIds[0]["ids"].size() == 1
		updateResultJson.examineeAssessmentIds[1]["ids"].size() == 1

		assessmentJson.deliveryTypeId == "24342653623263"
		assessmentJson.formId == "WISC-V"
		assessmentJson.rater["firstName"] == "Ruth"
		assessmentJson.rater["lastName"] == "Antonio"
		assessmentJson.rater["email"] == "ruth.antonio@gmail.com"
		assessmentJson.status == "ASSESSMENT_STATUS.Ready.for.Administration"
		assessmentJson.subtestIds.contains("5942dcfd6034fbab245e47b8999")


	}

	def "update examinee assessment container delete group"() {

		given:
		String examinerId = this.createExaminerAndReturnId();
		Examinee examinee = this.createExaminee();

		String examineeAssessmentJson = this.getExamineeAssessmentContainerJson(examinee.getId(), examinerId)

		when:

		def result = this.mockMvc.perform(put("/api/examineeAssessmentContainer").contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
				{
				   "examineeId":"${examinee.id}",
				   "examineeAssessmentHolders":[
					  {
						 "groupId":"BASC-3-PRS-Child",
						 "name":"BASC-3-PRS-Child",
						 "examineeAssessments":[
							{
							   "version":null,
							   "id":null,
							   "formId":"BASC-3-PRS-Child",
							   "adminstrationDate":${new Date().getTime()},
							   "examinerId":"${examinerId}",
							   "deliveryTypeId":"24342653623263",
							   "status":"ASSESSMENT_STATUS.Ready.for.Administration",
							   "rater":{
								  "firstName":"John",
								  "lastName":"Mark",
								  "email":"john.mark@gmail.com"
							   },
							   "subtestIds":[
								  "5942dcfd6034fbab245e47bc1",
								  "5942dcfd6034fbab245e47bc2",
								  "5942dcfd6034fbab245e47bc3"
							   ],
							   "bundleVariables":null
							}
														
						 ]
					  },
					  {
						   "groupId":"WISC-V",
						   "name":"WISC-V",
						   "examineeAssessments":[
							  {
								 "version":null,
								 "id":null,
								 "formId":"",
								 "adminstrationDate":"${new Date().getTime()}",
								 "examinerId":"${examinerId}",
								 "deliveryTypeId":"24342653623263",
								 "status":"",
								 "rater":{
									"firstName":"Ruth",
									"lastName":"Antonio",
									"email":"ruth.antonio@gmail.com"
								 },
								 "subtestIds":[
									"5942dcfd6034fbab245e47b8999",
									"5942dcfd6034fbab245e47bc2"
								 ],
								 "bundleVariables":null
							  }
						   ]
						}
				   ]
				}
			"""
		)).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		println "Container Id:" + resultJson.id
		println new JsonBuilder(resultJson).toPrettyString()
		println "Assessment id:" + resultJson.examineeAssessmentIds[0]["ids"][0]
		def updateResult = this.mockMvc.perform(post("/api/examineeAssessmentContainer/update").contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
				{
					"id":"${resultJson.id}",
				   "examineeId":"${examinee.id}",
				   "examineeAssessmentHolders":[
					  {
						 "groupId":"BASC-3-PRS-Child",
						 "name":"BASC-3-PRS-Child",
						 "examineeAssessments":[
							{
							   "version":null,
							   "id":"${resultJson.examineeAssessmentIds[0]['ids'][0]}",
							   "formId":"BASC-3-PRS-Child",
							   "adminstrationDate":${new Date().getTime()},
							   "examinerId":"${examinerId}",
							   "deliveryTypeId":"24342653623263",
							   "status":"ASSESSMENT_STATUS.Ready.for.Administration",
							   "rater":{
								  "firstName":"John",
								  "lastName":"Mark",
								  "email":"john.mark@gmail.com"
							   },
							   "subtestIds":[
								  "5942dcfd6034fbab245e47bc1",
								  "5942dcfd6034fbab245e47bc2",
								  "5942dcfd6034fbab245e47bc3"
							   ],
							   "bundleVariables":null
							}
														
						 ]
					  }
				   ]
				}
			"""

		)).andExpect(status().isOk()).andReturn();
		def updateResultJson = new JsonSlurper().parseText(updateResult.response.contentAsString)
		println new JsonBuilder(updateResultJson).toPrettyString()

		then:
		resultJson.id == updateResultJson.id
		resultJson.examineeAssessmentIds[0]["ids"].size() == 1
		resultJson.examineeAssessmentIds[0]["ids"][0] != null
		resultJson.examineeAssessmentIds[0]["groupId"] == "BASC-3-PRS-Child"
		updateResultJson.examineeAssessmentIds[0]["ids"].size() == 1
		updateResultJson.examineeAssessmentIds[1] == null
		updateResultJson.examineeAssessmentIds[0]["ids"].contains(resultJson.examineeAssessmentIds[0]["ids"][0])

		updateResultJson.examineeAssessmentIds.size() == 1
		updateResultJson.examineeAssessmentIds[0]["groupId"] == "BASC-3-PRS-Child"

		updateResultJson.examineeAssessmentIds[0]["ids"].size() == 1


	}

	def "update examinee assessment container delete assessment"() {

		given:
		String examinerId = this.createExaminerAndReturnId();
		Examinee examinee = this.createExaminee();

		String examineeAssessmentJson = this.getExamineeAssessmentContainerJson(examinee.getId(), examinerId)

		when:

		def result = this.mockMvc.perform(put("/api/examineeAssessmentContainer").contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
				{
				   "examineeId":"${examinee.id}",
				   "examineeAssessmentHolders":[
					  {
						 "groupId":"BASC-3-PRS-Child",
						 "name":"BASC-3-PRS-Child",
						 "examineeAssessments":[
							{
							   "version":null,
							   "id":null,
							   "formId":"BASC-3-PRS-Child",
							   "adminstrationDate":${new Date().getTime()},
							   "examinerId":"${examinerId}",
							   "deliveryTypeId":"24342653623263",
							   "status":"ASSESSMENT_STATUS.Ready.for.Administration",
							   "rater":{
								  "firstName":"John",
								  "lastName":"Mark",
								  "email":"john.mark@gmail.com"
							   },
							   "subtestIds":[
								  "5942dcfd6034fbab245e47bc1",
								  "5942dcfd6034fbab245e47bc2",
								  "5942dcfd6034fbab245e47bc3"
							   ],
							   "bundleVariables":null
							},
							{
							   "version":null,
							   "id":null,
							   "formId":"",
							   "adminstrationDate":"${new Date().getTime()}",
							   "examinerId":"${examinerId}",
							   "deliveryTypeId":"24342653623263",
							   "status":"",
							   "rater":{
								  "firstName":"Ruth",
								  "lastName":"Antonio",
								  "email":"ruth.antonio@gmail.com"
							   },
							   "subtestIds":[
								  "5942dcfd6034fbab245e47b8999",
								  "5942dcfd6034fbab245e47bc2"
							   ],
							   "bundleVariables":null
							}
														
						 ]
					  },
					  {
						   "groupId":"WISC-V",
						   "name":"WISC-V",
						   "examineeAssessments":[
							  {
								 "version":null,
								 "id":null,
								 "formId":"",
								 "adminstrationDate":"${new Date().getTime()}",
								 "examinerId":"${examinerId}",
								 "deliveryTypeId":"24342653623263",
								 "status":"",
								 "rater":{
									"firstName":"Ruth",
									"lastName":"Antonio",
									"email":"ruth.antonio@gmail.com"
								 },
								 "subtestIds":[
									"5942dcfd6034fbab245e47b8999",
									"5942dcfd6034fbab245e47bc2"
								 ],
								 "bundleVariables":null
							  }
						   ]
						}
				   ]
				}
			"""
		)).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		println "Container Id:" + resultJson.id
		println new JsonBuilder(resultJson).toPrettyString()
		println "Assessment id:" + resultJson.examineeAssessmentIds[0]["ids"][0]
		def updateResult = this.mockMvc.perform(post("/api/examineeAssessmentContainer/update").contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
				{
					"id":"${resultJson.id}",
				   "examineeId":"${examinee.id}",
				   "examineeAssessmentHolders":[
					  {
						 "groupId":"BASC-3-PRS-Child",
						 "name":"BASC-3-PRS-Child",
						 "examineeAssessments":[
							{
							   "version":null,
							   "id":"${resultJson.examineeAssessmentIds[0]['ids'][0]}",
							   "formId":"BASC-3-PRS-Child",
							   "adminstrationDate":${new Date().getTime()},
							   "examinerId":"${examinerId}",
							   "deliveryTypeId":"24342653623263",
							   "status":"ASSESSMENT_STATUS.Ready.for.Administration",
							   "rater":{
								  "firstName":"John",
								  "lastName":"Mark",
								  "email":"john.mark@gmail.com"
							   },
							   "subtestIds":[
								  "5942dcfd6034fbab245e47bc1",
								  "5942dcfd6034fbab245e47bc2",
								  "5942dcfd6034fbab245e47bc3"
							   ],
							   "bundleVariables":null
							}
														
						 ]
					  }
				   ]
				}
			"""

		)).andExpect(status().isOk()).andReturn();
		def updateResultJson = new JsonSlurper().parseText(updateResult.response.contentAsString)
		println new JsonBuilder(updateResultJson).toPrettyString()

		then:
		resultJson.id == updateResultJson.id
		resultJson.examineeAssessmentIds[0]["ids"].size() == 2
		updateResultJson.examineeAssessmentIds[0]["ids"].size() == 1
		updateResultJson.examineeAssessmentIds[1] == null
		updateResultJson.examineeAssessmentIds[0]["ids"].contains(resultJson.examineeAssessmentIds[0]["ids"][0])
		resultJson.examineeAssessmentIds[0]["ids"][0] != null
		resultJson.examineeAssessmentIds[0]["groupId"] == "BASC-3-PRS-Child"

		updateResultJson.examineeAssessmentIds[0]["groupId"] == "BASC-3-PRS-Child"
	}


	def cleanup() {
		this.examineeAssessmentContainerDao.deleteAll();
		this.examineeDao.deleteAll();
	}

	/**
	 * create Examinee entity.
	 * @return
	 */
	private Examinee createExaminee() {
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
		return this.examineeDao.findOne(createdExaminee.id);

	}
	/**
	 * Create Examiner and return id.
	 *
	 * @return
	 */
	private String createExaminerAndReturnId() {
		def result = this.mockMvc.perform(put("/api/examiner").contentType(MediaType.APPLICATION_JSON).content(
				"""
			{
			   "title":"Mr.",
			   "firstName":"Prasad",
			   "lastName":"Kakarlamudi",
			   "middleName":"",
			   "suffix":"Sr.",
			   "examinerId":"ex00067"
			}
			"""
		)).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)
		return resultJson.id;

	}

	/**
	 * Create ExamineeAssessmentConatiner JSON.
	 * @param id
	 * @return
	 */
	private String getExamineeAssessmentContainerJson(String id, String examinerId) {
		String examineeAssessmentJson = """
				{
				   "examineeId":"${id}",
				   "examineeAssessmentHolders":[
					  {
						 "groupId":"BASC-3-PRS-Child",
						 "name":"BASC-3-PRS-Child",
						 "examineeAssessments":[
							{
							   "version":null,
							   "id":null,
							   "formId":"BASC-3-PRS-Child",
							   "adminstrationDate":${new Date().getTime()},
							   "examinerId":"${examinerId}",
							   "deliveryTypeId":"24342653623263",
							   "status":"ASSESSMENT_STATUS.Ready.for.Administration",
							   "rater":{
								  "firstName":"John",
								  "lastName":"Mark",
								  "email":"john.mark@gmail.com"
							   },
							   "subtestIds":[
								  "5942dcfd6034fbab245e47bc1",
								  "5942dcfd6034fbab245e47bc2",
								  "5942dcfd6034fbab245e47bc3"
							   ],
							   "bundleVariables":null
							}
						 ]
					  }
				   ]
				}
			"""
		return examineeAssessmentJson;
	}

	/**
	 * Create ExamineeAssessmentConatiner JSON.
	 * @param id
	 * @return
	 */
	private String getUpdateExamineeAssessmentContainerJson(String id, String examinerId) {
		String examineeAssessmentJson =
				"""
				{
				   "examineeId":"${id}",
				   "examineeAssessmentHolders":[
					  {
						 "groupId":"BASC-3-PRS-Child",
						 "name":"BASC-3-PRS-Child",
						 "examineeAssessments":[
							{
							   "version":null,
							   "id":null,
							   "formId":"BASC-3-PRS-Child",
							   "adminstrationDate":${new Date().getTime()},
							   "examinerId":"${examinerId}",
							   "deliveryTypeId":"24342653623263",
							   "status":"ASSESSMENT_STATUS.Ready.for.Administration",
							   "rater":{
								  "firstName":"John",
								  "lastName":"Mark",
								  "email":"john.mark@gmail.com"
							   },
							   "subtestIds":[
								  "5942dcfd6034fbab245e47bc1",
								  "5942dcfd6034fbab245e47bc2",
								  "5942dcfd6034fbab245e47bc3"
							   ],
							   "bundleVariables":null
							}
						 ]
					  }
				   ]
				}
			"""
		return examineeAssessmentJson;
	}
}
