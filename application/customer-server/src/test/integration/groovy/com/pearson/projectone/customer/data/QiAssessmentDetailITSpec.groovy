package com.pearson.projectone.customer.data

import com.pearson.projectone.customer.BaseCustomerServerIntgTest
import com.pearson.projectone.data.constants.AssessProgressState
import com.pearson.projectone.data.constants.AssessmentType
import com.pearson.projectone.data.dao.customer.assess.QiAssessmentDao
import com.pearson.projectone.data.entity.customer.assess.QiAssessmentDetails
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired

class QiAssessmentDetailITSpec extends BaseCustomerServerIntgTest {

	@Autowired
	QiAssessmentDao assessmentDao

	def resultJson = this.getClass().getResource("/testFiles/resultsJson/qi_assessment_results.json").text

	def cleanup() {
		assessmentDao.deleteAll()
	}

	def "should create a qi assessment"() {
		given:
		def qiAssessment = new QiAssessmentDetails(
				examineeAssessmentId: 'exasmt001',
				progressState: AssessProgressState.GIVE,
				assessmentType: AssessmentType.NORMAL,
				syncSucceeded: true,
				resultsJson: resultJson
		)
		qiAssessment = assessmentDao.save(qiAssessment)

		when:
		qiAssessment = assessmentDao.findOne(qiAssessment.id)
		def examineeAssessment = assessmentDao.findByExamineeAssessmentId('exasmt001')

		then:
		qiAssessment != null
		qiAssessment.progressState == AssessProgressState.GIVE
		qiAssessment.assessmentType == AssessmentType.NORMAL
		qiAssessment.resultsJson == null // ensures this is not saved and is handled separately
		qiAssessment.syncSucceeded
		examineeAssessment.id == qiAssessment.id
	}

	def "should save and extract result json"() {
		given: 'create an assessment'
		def qiAssessment = new QiAssessmentDetails(
				examineeAssessmentId: 'exasmt001',
				progressState: AssessProgressState.GIVE,
				assessmentType: AssessmentType.NORMAL,
				syncSucceeded: true,
				resultsJson: resultJson
		)
		qiAssessment = assessmentDao.save(qiAssessment)
		qiAssessment.resultsJson = resultJson
		def expectedTests = """
		{
		  "gradeLevel": null,
		  "testGUID": "e180b1b1469f4915922effaa90958526",
		  "displayName": "GFTA-3 Ages 2-6:11",
		  "compositeScoreData": {
			"composites": [],
			"comparisons": []
		  },
		  "yearsOfEducation": null,
		  "testName": "GFTA-3_Ages_2-6:11"
		}
		"""

		when:
		assessmentDao.save(qiAssessment)
		def resultsJson = assessmentDao.retrieveDocument(qiAssessment.id, "resultsJson")
		def testsFromJson = assessmentDao.retrieveDocument(qiAssessment, "resultsJson.tests")

		then:
		testsFromJson.resultsJson.tests.size() == 1
		new JsonSlurper().parseText(JsonOutput.toJson(testsFromJson.resultsJson.tests[0])) == new JsonSlurper().parseText(expectedTests)
		resultsJson.resultsJson.tests == testsFromJson.resultsJson.tests
		resultsJson.resultsJson.identifier == "111"
	}


	def "should get the tenth question from the second item group"() {
		given: 'create an assessment'
		def qiAssessment = new QiAssessmentDetails(
				examineeAssessmentId: 'exasmt001',
				progressState: AssessProgressState.GIVE,
				assessmentType: AssessmentType.NORMAL,
				syncSucceeded: true,
				resultsJson: resultJson
		)
		qiAssessment = assessmentDao.save(qiAssessment)

		when:
		def result = assessmentDao.retrieveDocumentByCommands(qiAssessment,
				// project 1st subtest
				"{\$project: { _id: 0, subtest: {\$arrayElemAt: ['\$resultsJson.subtests', 0]}}}",
				// project 2nd item group
				"{\$project: { item: {\$arrayElemAt: ['\$subtest.itemGroups', 1]}}}",
				// 10th question, but removed predefinedBehavior
				"{\$project: { targetQuestion: {\$arrayElemAt: ['\$item.questions', 9]}}}"
		)


		then:
		result.size() == 1
		result[0].targetQuestion.id == 'gfta3-soundsinsentences-cartoon-s5'
	}
}
