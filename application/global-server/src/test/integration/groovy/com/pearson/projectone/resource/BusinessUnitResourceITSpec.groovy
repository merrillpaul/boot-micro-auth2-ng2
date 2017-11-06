package com.pearson.projectone.resource

import com.pearson.projectone.BaseGlobalServerIntegrationSpec
import com.pearson.projectone.authcommons.dto.AppUserDetails
import com.pearson.projectone.data.dao.global.library.account.BusinessUnitDao
import com.pearson.projectone.data.dao.global.library.assessment.AssessTestDao
import com.pearson.projectone.data.dao.global.library.assessment.AssessmentDao
import com.pearson.projectone.data.dao.global.library.assessment.BusinessUnitAssessmentDao
import com.pearson.projectone.data.dao.global.library.assessment.FormDao
import com.pearson.projectone.data.entity.global.library.account.BusinessUnit
import com.pearson.projectone.data.entity.global.library.assessment.AssessTest
import com.pearson.projectone.data.entity.global.library.assessment.Assessment
import com.pearson.projectone.data.entity.global.library.assessment.BusinessUnitAssessment
import com.pearson.projectone.data.entity.global.library.assessment.Form
import com.pearson.projectone.data.entity.security.user.AppUser
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import java.sql.Timestamp
import java.time.LocalDateTime

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class BusinessUnitResourceITSpec extends BaseGlobalServerIntegrationSpec {

	@Autowired
	WebApplicationContext wac

	@Autowired
	AssessmentDao assessmentDao

	@Autowired
	AssessTestDao assessTestDao

	@Autowired
	FormDao formDao

	@Autowired
	BusinessUnitDao businessUnitDao

	@Autowired
	BusinessUnitAssessmentDao businessUnitAssessmentDao

	MockMvc mockMvc

	@PersistenceContext(unitName = "global")
	EntityManager entityManager

	def currentUser
	def authorities = ['ROLE_SYSTEM_ADMIN', 'ROLE_CLIENT'].collect {
		new SimpleGrantedAuthority(it)
	}

	def setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()

		currentUser = new AppUser(id: "USR0001", username: 'joe', password: 'Password1', firstName: "Joe",
				lastName: 'Doe', timezoneId: TimeZone.default.ID, honorific: 'Mr',
				accountExpired: false, accountLocked: false, lastCreated: Timestamp.valueOf(LocalDateTime.now()),
				enabled: true)

		def loggedPrincipal = new AppUserDetails(currentUser, authorities, false)
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
				loggedPrincipal, 'na', loggedPrincipal.getAuthorities()))
		createBusinessUnits();
	}


	def "should paginate assessments for a bu"() {
		given:
		createAssessments()
		def caBU = businessUnitDao.findByGuid("CA_GUID")

		when:
		def result = this.mockMvc.perform(post("/api/businessUnit/${caBU.id}/listAssessments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
					{
						"pageRequest": {
							"page": 2,
							"size": 3
						}
					}
				""".toString()
		)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn()
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)

		def expectedJsonResponse = new JsonSlurper().parseText("""
		{
		  "content": [
			{
			  "version": 0,
			  "id": "27",
			  "assessmentName": "ASMT_27",
			  "accronym": null
			},
			{
			  "version": 0,
			  "id": "28",
			  "assessmentName": "ASMT_28",
			  "accronym": null
			},
			{
			  "version": 0,
			  "id": "29",
			  "assessmentName": "ASMT_29",
			  "accronym": null
			}
		  ],
		  "last": false,
		  "totalElements": 10,
		  "totalPages": 4,
		  "size": 3,
		  "number": 2,
		  "numberOfElements": 3,
		  "first": false,
		  "sort": null
		}
		""")

		then:
		expectedJsonResponse == resultJson
	}


	def "should search assessments for a bu"() {
		given:
		createAssessments()
		def caBU = businessUnitDao.findByGuid("CA_GUID")
		def assessmentWisc5 = assessmentDao.save(new Assessment(id: "IDWISC5", name: "WISC5"))
		def assessmentWiat3 = assessmentDao.save(new Assessment(id: "IDWIAT3", name: "WIAT3"))
		def assessmentKtea = assessmentDao.save(new Assessment(id: "IDKTEA3", name: "KTEA3"))
		[assessmentWisc5, assessmentWiat3, assessmentKtea].each {
			businessUnitAssessmentDao.save(new BusinessUnitAssessment(assessment: it,
					businessUnit: caBU))
		}

		when:
		def result = this.mockMvc.perform(post("/api/businessUnit/${caBU.id}/listAssessments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
					{
						"criteria": [
										{
											"key": "name",
											"operation": "*_",
											"value": "wi"
										}
						],
						"pageRequest": {
							"page": 0,
							"size": 2,
							"orders": [
								{
									"property": "name"
								}
							]
						}
					}
				""".toString()
		)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn()
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)

		def expectedJsonResponse = new JsonSlurper().parseText("""
		{
		  "content": [
			{
			  "version": 0,
			  "id": "IDWIAT3",
			  "assessmentName": "WIAT3",
			  "accronym": null
			},
			{
			  "version": 0,
			  "id": "IDWISC5",
			  "assessmentName": "WISC5",
			  "accronym": null
			}
		  ],
		  "totalPages": 1,
		  "totalElements": 2,
		  "last": true,
		  "size": 2,
		  "number": 0,
		  "sort": [
			{
			  "direction": "ASC",
			  "property": "name",
			  "ignoreCase": false,
			  "nullHandling": "NATIVE",
			  "ascending": true
			}
		  ],
		  "numberOfElements": 2,
		  "first": true
		}
		""")

		then:
		resultJson == expectedJsonResponse

	}


	def "should paginate forms for a bu"() {
		given:
		createAssessments()
		def usBU = businessUnitDao.findByGuid("US_GUID")

		when:
		def result = this.mockMvc.perform(post("/api/businessUnit/${usBU.id}/listForms")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
					{
						"pageRequest": {
							"page": 2,
							"size": 3
						}
					}
				""".toString()
		)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn()
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)

		def expectedJsonResponse = new JsonSlurper().parseText("""
		{
		  "content": [
			{
			  "version": 0,
			  "id": "FRM3_0",
			  "acronym": "FRM_ASMT_3",
			  "description": "DESC_0",
			  "assessmentId": "3",
			  "assessmentName": "ASMT_3",
			  "formType": "TYPE_0"
			},
			{
			  "version": 0,
			  "id": "FRM3_1",
			  "acronym": "FRM_ASMT_3",
			  "description": "DESC_1",
			  "assessmentId": "3",
			  "assessmentName": "ASMT_3",
			  "formType": "TYPE_1"
			},
			{
			  "version": 0,
			  "id": "FRM3_2",
			  "acronym": "FRM_ASMT_3",
			  "description": "DESC_2",
			  "assessmentId": "3",
			  "assessmentName": "ASMT_3",
			  "formType": "TYPE_2"
			}
		  ],
		  "totalElements": 60,
		  "totalPages": 20,
		  "last": false,
		  "size": 3,
		  "number": 2,
		  "sort": null,
		  "numberOfElements": 3,
		  "first": false
		}
		""")

		then:
		expectedJsonResponse == resultJson
	}


	def "should not retrieve unavailable forms for a bu"() {
		given:
		createAssessments()
		def caBU = businessUnitDao.findByGuid("CA_GUID")

		when:
		def result = this.mockMvc.perform(post("/api/businessUnit/${caBU.id}/listForms")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
				"""
					{
						"pageRequest": {
							"page": 2,
							"size": 3
						}
					}
				""".toString()
		)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn()
		def resultJson = new JsonSlurper().parseText(result.response.contentAsString)

		def expectedJsonResponse = new JsonSlurper().parseText("""
		{
		  "content": [],
		  "totalElements": 0,
		  "totalPages": 0,
		  "last": true,
		  "size": 3,
		  "number": 2,
		  "sort": null,
		  "numberOfElements": 0,
		  "first": false
		}
		""")

		then:
		expectedJsonResponse == resultJson
	}

	private createAssessments() {
		def usBU = businessUnitDao.findByGuid("US_GUID")
		def caBU = businessUnitDao.findByGuid("CA_GUID")
		(1..20).each {

			def assessTest = assessTestDao.save(new AssessTest(id: "AS${it}", name: "ASSESS_${it}"))
			if (it % 2 == 0) {
				assessTest = null
			}
			def assessment = assessmentDao.save(new Assessment(id: "${it}", name: "ASMT_${it}"))
			(0..2).each { formIdx ->
				formDao.save(new Form(id: "FRM${it}_${formIdx}", acronym: "FRM_${assessment.name}", assessment: assessment, assessTest: assessTest, formType: "TYPE_${formIdx}", description: "DESC_${formIdx}", osaEngineId: 2121))
			}
			businessUnitAssessmentDao.save(new BusinessUnitAssessment(assessment: assessment,
					businessUnit: usBU))
		}
		(21..30).each {
			def assessment = assessmentDao.save(new Assessment(id: "${it}", name: "ASMT_${it}"))
			def assessTest = assessTestDao.save(new AssessTest(id: "AS${it}", name: "ASSESS_${it}"))
			businessUnitAssessmentDao.save(new BusinessUnitAssessment(assessment: assessment,
					businessUnit: caBU))
		}
	}

	private createBusinessUnits() {
		["US_GUID", "CA_GUID"].each {
			businessUnitDao.save(new BusinessUnit(name: "NAME_${it}", guid: it))
		}
	}
}
