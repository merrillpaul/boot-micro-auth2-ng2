package com.pearson.projectone.resource

import com.pearson.projectone.BaseGlobalServerIntegrationSpec
import com.pearson.projectone.data.dao.global.library.assessment.AssessmentDao
import com.pearson.projectone.data.dao.global.library.assessment.FormDao
import com.pearson.projectone.data.entity.global.library.assessment.Assessment
import com.pearson.projectone.data.entity.global.library.assessment.Form
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class FormResourceITSpec extends BaseGlobalServerIntegrationSpec {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	AssessmentDao assessmentDao

	@Autowired
	FormDao formDao;

	private MockMvc mockMvc;

	def setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	def "list forms should return nocontent"() {

		when:
		def result = this.mockMvc.perform(post("/api/form/list").contentType(MediaType.APPLICATION_JSON).content(
				"""
					{
					   "pageRequest":{
						  "orders":[

						  ],
						  "page":0,
						  "size":15
					   },
					   "criteria":[

					   ]
					}
			"""
		)).andExpect(status().isNoContent()).andReturn();

		then:
		result.response.status == 204

	}

	def "list forms should return one record"() {

		given:
		Form form = this.createForm();

		when:
		def result = this.mockMvc.perform(post("/api/form/list").contentType(MediaType.APPLICATION_JSON).content(
				"""
					{
					   "pageRequest":{
						  "orders":[

						  ],
						  "page":0,
						  "size":15
					   },
					   "criteria":[

					   ]
					}
			"""
		)).andExpect(status().isOk()).andReturn();

		def formJson = new JsonSlurper().parseText(result.response.contentAsString)
		def expectedFormJson = new JsonSlurper().parseText("""{
				"content": [
					{
						"acronym": "BASC-3-PRS",
						"assessmentName": "BASC-3 PRS",
						"description": "The Behavior Assessment",
						"formDeliveryTypes": [],
						"formType": "Standard",
						"id": "8aea569b5c8d8fa3015c8d8fc87d0084",
						"version": 0
					}
				],
				"first": true,
				"last": true,
				"number": 0,
				"numberOfElements": 1,
				"size": 15,
				"sort": null,
				"totalElements": 1,
				"totalPages": 1
			}""");

		expectedFormJson.content[0].id = formJson.content[0].id

		then:
		formJson.content[0].id != null
		formJson.content[0].acronym == "BASC-3-PRS"
		expectedFormJson == formJson
	}

	def "list all forms without any search or filter (get request)"() {

		given:
		Form form = this.createForm();

		when:
		def result = this.mockMvc.perform(get("/api/form/list")).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)

		then:
		resultJson.size() > 0
	}

	def "list lite version of all forms  without any search or filter (get request)"() {

		given:
		Form form = this.createForm();

		when:
		def result = this.mockMvc.perform(get("/api/form/list/lite")).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)

		then:
		resultJson.size() > 0
	}

	def "get form by id"() {

		given:
		Form form = this.createForm();

		when:
		def result = this.mockMvc.perform(get("/api/form/${form.getId()}")).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)

		then:
		resultJson.id == form.getId()
		resultJson.acronym == "BASC-3-PRS"
		resultJson.assessmentName == "BASC-3 PRS"
		resultJson.formType == "Standard"
		resultJson.version == 0

	}

	def "get form by id (bad form id)"() {

		when:
		def request = this.mockMvc.perform(get("/api/form/BAD-ID"));


		then:
		request.andExpect(status().isNotFound());

	}

	def "get detailed form by id"() {

		given:
		Form form = this.createForm();

		when:
		def result = this.mockMvc.perform(get("/api/form/${form.getId()}/detail")).andExpect(status().isOk()).andReturn();
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)

		then:
		resultJson.id == form.getId()
		resultJson.acronym == "BASC-3-PRS"
		resultJson.assessmentName == "BASC-3 PRS"
		resultJson.formType == "Standard"
		resultJson.version == 0
		resultJson.displayExaminer == true

	}

	def "delete form with given id"() {

		given:
		Form form = this.createForm();

		when:
		this.mockMvc.perform(delete("/api/form/${form.getId()}")).andExpect(status().isOk()).andReturn();

		then:
		this.mockMvc.perform(get("/api/form/${form.getId()}")).andExpect(status().isNotFound()).andReturn()
	}

	def "delete form with given id (non-exisitng form id)"() {

		when:
		def request = this.mockMvc.perform(delete("/api/form/BAD-ID"))

		then:
		request.andExpect(status().isNotFound());
	}


	def "Create Form using rest endpoint api"() {

		given:
		Assessment assessment = this.createAssessment();
		String formJson = """
			{
			   "id":null,
			   "acronym": "KTEA-3",
			   "assessmentId": "${assessment.getId()}",
			   "productCode":"KTEA3",
			   "description":" Kakarlamudi Telugu English Assessment 3",
			   "statusId":"Active",
			   "formDefinition":null,
			   "parentFormId":null,
			   "dataCollectionExportDef":null,
			   "osaEngineId":1,
			   "groupAdministration":false,
			   "formType":"Standard",
			   "assessTestId":null,
			   "formDeliveryTypeIds":[]
			}
		"""

		when:
		def result = this.mockMvc.perform(put("/api/form").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(formJson))
				.andExpect(status().isOk()).andReturn()
		def jsonResponse = new JsonSlurper().parseText(result.response.contentAsString)

		then:
		jsonResponse.id != null
		jsonResponse.version == 0

	}

	def "Create Form using rest endpoint api with bad assessment id"() {

		given:
		String formJson = """
			{
			   "id":null,
			   "acronym": "KTEA-3",
			   "assessmentId": "BAD-ID",
			   "productCode":"KTEA3",
			   "description":" Kakarlamudi Telugu English Assessment 3",
			   "statusId":"Active",
			   "formDefinition":null,
			   "parentFormId":null,
			   "dataCollectionExportDef":null,
			   "osaEngineId":1,
			   "groupAdministration":false,
			   "formType":"Standard",
			   "assessTestId":null,
			   "formDeliveryTypeIds":null
			}
		"""

		when:
		def request = this.mockMvc.perform(put("/api/form").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(formJson))


		then:
		request.andExpect(status().isBadRequest())

	}


	def "update Form using rest endpoint"() {

		given:
		Assessment assessment = this.createAssessment();
		println "Assessment id:" + assessment.getId()
		String formJsonForCreate = """
			{
			   "id":null,
			   "acronym": "KTEA-3",
			   "assessmentId": "${assessment.getId()}",
			   "productCode":"KTEA3",
			   "description":" Kakarlamudi Telugu English Assessment 3",
			   "statusId":"Active",
			   "formDefinition":null,
			   "parentFormId":null,
			   "dataCollectionExportDef":null,
			   "osaEngineId":1,
			   "groupAdministration":false,
			   "formType":"Standard",
			   "assessTestId":null,
			   "formDeliveryTypeIds":["On Screen Administration"]
			}
		"""

		def result = this.mockMvc.perform(put("/api/form").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(formJsonForCreate)).andExpect(status().isOk()).andReturn()
		def jsonResponse = new JsonSlurper().parseText(result.response.contentAsString)
		println "Id: " + jsonResponse.id

		when:
		def updateResult = this.mockMvc.perform(post("/api/form").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(
				"""
			{
			   "id":"${jsonResponse.id}",
			   "acronym": "KTEA-3",
			   "assessmentId": "${assessment.getId()}",
			   "productCode":"KTEA3",
			   "description":"Kaufman Test of Educational Achievement, Third Edition",
			   "statusId":"InActive",
			   "formDefinition":null,
			   "parentFormId":null,
			   "dataCollectionExportDef":null,
			   "osaEngineId":1,
			   "groupAdministration":false,
			   "formType":"Standard",
			   "assessTestId":null,
			   "formDeliveryTypeIds":[]
			}
		"""
		)).andExpect(status().isOk()).andReturn()
		def upadteJsonResponse = new JsonSlurper().parseText(result.response.contentAsString)
		def getResult = this.mockMvc.perform(get("/api/form/${upadteJsonResponse.id}")).andExpect(status().isOk())
				.andReturn()
		def updateFormDTO = new JsonSlurper().parseText(getResult.response.contentAsString)
		println updateFormDTO

		then:
		upadteJsonResponse.id != null
		updateFormDTO.id == upadteJsonResponse.id
		updateFormDTO.version == 1
		updateFormDTO.description == "Kaufman Test of Educational Achievement, Third Edition"

	}


	def "update Form using rest endpoint with bad form id"() {

		when:
		def request = this.mockMvc.perform(post("/api/form").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(
				"""
			{
			   "id":"BAD-Id",
			   "acronym": "KTEA-3",
			   "assessmentId": "DOESN'T MATTER",
			   "productCode":"KTEA3",
			   "description":"Kaufman Test of Educational Achievement, Third Edition",
			   "statusId":"InActive",
			   "formDefinition":null,
			   "parentFormId":null,
			   "dataCollectionExportDef":null,
			   "osaEngineId":1,
			   "groupAdministration":false,
			   "formType":"Standard",
			   "assessTestId":null,
			   "formDeliveryTypeIds":null
			}
		"""
		))

		then:
		request.andExpect(status().isNotFound())
	}

	/**
	 * Creates Form object.
	 * @return
	 */
	private Form createForm() {
		Assessment assessment = this.createAssessment();

		Form form = this.formDao.save(new Form("BASC-3-PRS", "BASC-3 PRS", assessment, "BASC-3", "The Behavior Assessment", "Ready",
				"", null, null, 1L, false, "Standard", null));
		return form;
	}

	/**
	 * Create new Assessment
	 * @return Assessment entity object.
	 */
	private Assessment createAssessment() {
		return this.assessmentDao.save(new Assessment("BASC-3", "BASC 3 Assessment", "1", "Pearson",
				"C", new Date(), new Date(), "Sample Comments", "Active", "Basc-3", null, null, null, true,
				true, "", UUID.randomUUID().toString()));
	}

}
