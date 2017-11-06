package com.pearson.projectone.customer.resource

import com.pearson.projectone.customer.BaseCustomerServerIntgTest
import com.pearson.projectone.data.dao.customer.ExamineeDao
import com.pearson.projectone.data.entity.customer.examinee.Examinee
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ExamineeAssessmentResourceITSpec extends BaseCustomerServerIntgTest {


	@Autowired
	WebApplicationContext webApplicationContext

	@Autowired
	ExamineeDao examineeDao;

	MockMvc mockMvc;


	def setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
	}

	def "create examinee assessment"() {
		given:
		Examinee examinee = this.createExaminee();

		String examineeAssessmnetJson = """
		{
   			"examineeId":"${examinee.getId()}",
   			"examineeAssessmentHolders":[{
   			"name":"BASC-3-PRS",
   			"groupId": "BASC-3-PRS",
   			"examineeAssessments":[
			  {
				 "version": null,
				 "id":null,
				 "formId":"BASC-3-PRS-Child",
				 "adminstrationDate":${new Date().getTime()},
				 "examinerId": "5942dcfd6034fbab245e47bc4",
				 "deliveryTypeId":"24342653623263",
				 "status":"Ready for Adminstration",
				 "rater":{
					"firstName": "John",
					"lastName":"Mark",
					"email":"john.mark@gmail.com"
				 },
				 "subtestIds":["5942dcfd6034fbab245e47bc1", "5942dcfd6034fbab245e47bc2", "5942dcfd6034fbab245e47bc3"],
				 "bundleVariables":null
			  }
		   ]
		   }]
		}
			"""

		println "JSON Object" + examineeAssessmnetJson;
		when:

		def result = this.mockMvc.perform(put("/api/examineeAssessment").contentType(MediaType.APPLICATION_JSON)
				.content(examineeAssessmnetJson)).andExpect(status().isOk()).andReturn()
		def responseJson = new JsonSlurper().parseText(result.response.contentAsString)
		println responseJson

		def assessmentId = responseJson.examineeAssessmentIds[0]["ids"][0];
		println "Examinee Assessment id:" + assessmentId

		def assessmentResult = this.mockMvc.perform(get("/api/examineeAssessment/${assessmentId}"))
				.andExpect(status().isOk()).andReturn()

		def assessmentJosn = new JsonSlurper().parseText(assessmentResult.response.contentAsString)
		println "Examinee Assessment :" + assessmentJosn
		then:
		responseJson.examineeAssessmentIds.size() > 0
		responseJson.examineeAssessmentIds["BASC-3-PRS-Child"] != null

		assessmentJosn.formId == "BASC-3-PRS"
		assessmentJosn.subtestIds.size() == 3
		assessmentJosn.status == "ASSESSMENT_STATUS.Ready.for.Administration"
		assessmentJosn.rater["firstName"] == "John"
		assessmentJosn.rater["lastName"] == "Mark"
		assessmentJosn.rater["email"] == "john.mark@gmail.com"
	}

	def "create examinee assessment no examinee id "() {
		given:

		String examineeAssessmnetJson = """
		{
   			"examineeId":null,
   			"examineeAssessmentHolders":[{
				"name":"BASC-3-PRS",
				"groupId":"BASC-3-PRS",
				"examineeAssessments":[
			  {
				 "version": null,
				 "id":null,
				 "formId":"BASC-3-PRS-Child",
				 "adminstrationDate":${new Date().getTime()},
				 "examinerId": "5942dcfd6034fbab245e47bc4",
				 "deliveryTypeId":"24342653623263",
				 "status":"ASSESSMENT_STATUS.Ready.for.Administration",
				 "rater":{
					"firstName": "John",
					"lastName":"Mark",
					"email":"john.mark@gmail.com"
				 },
				 "subtestIds":["5942dcfd6034fbab245e47bc1", "5942dcfd6034fbab245e47bc2", "5942dcfd6034fbab245e47bc3"],
				 "bundleVariables":null
			  }
		   	]
		   	}]
		}
			"""
		when:

		def request = this.mockMvc.perform(put("/api/examineeAssessment").contentType(MediaType.APPLICATION_JSON)
				.content(examineeAssessmnetJson))


		then:
		request.andExpect(status().isBadRequest())
	}

	def "create examinee assessment no examinee assessments "() {
		given:

		String examineeAssessmnetJson = """
		{
   			"examineeId":"dagfgd3e686426384823",
   			"examineeAssessmentHolders":[]
		}
			"""
		when:

		def request = this.mockMvc.perform(put("/api/examineeAssessment").contentType(MediaType.APPLICATION_JSON)
				.content(examineeAssessmnetJson))

		then:
		request.andExpect(status().isBadRequest())
	}

	def "Delete examinee assessment"() {
		given:
		Examinee examinee = this.createExaminee();

		String examineeAssessmnetJson = """
				{
				   "examineeId":"${examinee.getId()}",
				   "examineeAssessmentHolders":[
					  {
						 "name":"BASC-3-PRS",
						 "groupId":"BASC-3-PRS",
						 "examineeAssessments":[
							{
							   "version":null,
							   "id":null,
							   "formId":"BASC-3-PRS-Child",
							   "adminstrationDate": ${new Date().getTime()},
							   "examinerId":"5942dcfd6034fbab245e47bc4",
							   "deliveryTypeId":"24342653623263",
							   "status":"Ready for Adminstration",
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

		println "JSON Object" + examineeAssessmnetJson;
		when:

		def result = this.mockMvc.perform(put("/api/examineeAssessment").contentType(MediaType.APPLICATION_JSON)
				.content(examineeAssessmnetJson)).andExpect(status().isOk()).andReturn()
		def responseJson = new JsonSlurper().parseText(result.response.contentAsString)
		println responseJson

		def assessmentId = responseJson.examineeAssessmentIds[0]["ids"][0];


		def deleteResult = this.mockMvc.perform(delete("/api/examineeAssessment/${assessmentId}"))
				.andExpect(status().isOk()).andReturn()

		then:
		this.mockMvc.perform(delete("/api/examineeAssessment/${assessmentId}"))
				.andExpect(status().isBadRequest())

	}


	def cleanup() {
		examineeDao.deleteAll();
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
}
