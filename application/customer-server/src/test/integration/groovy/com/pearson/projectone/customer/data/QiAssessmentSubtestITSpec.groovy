package com.pearson.projectone.customer.data

import com.pearson.projectone.customer.BaseCustomerServerIntgTest
import com.pearson.projectone.data.dao.customer.assess.QiAssessmentSubtestDao
import com.pearson.projectone.data.entity.customer.assess.QiAssessmentSubtest
import org.springframework.beans.factory.annotation.Autowired

class QiAssessmentSubtestITSpec extends BaseCustomerServerIntgTest {

	@Autowired
	QiAssessmentSubtestDao assessmentSubtestDao

	def subtestJson = this.getClass().getResource("/testFiles/resultsJson/qi_subtest_results.json").text

	def cleanup() {
		assessmentSubtestDao.deleteAll()
	}

	def "should create qi assessment subtest and return by assessment ids"() {
		given:
		assessmentSubtestDao.save(new QiAssessmentSubtest(
				qiAssessmentId: 'ex01',
				subtestInstanceId: 'inst1'
		))
		assessmentSubtestDao.save(new QiAssessmentSubtest(
				qiAssessmentId: 'ex02',
				subtestInstanceId: 'inst2'
		))
		assessmentSubtestDao.save(new QiAssessmentSubtest(
				qiAssessmentId: 'ex01',
				subtestInstanceId: 'inst3'
		))

		when:
		def list = assessmentSubtestDao.findByQiAssessmentId('ex01')

		then:
		list.size() == 2
		list.collect { it.subtestInstanceId }.sort() == ['inst1', 'inst3']
	}

	def "should save and extract subtest json"() {
		given: 'create an assessment subtest'
		def qiAssessmentSubtest = assessmentSubtestDao.save(new QiAssessmentSubtest(
				qiAssessmentId: 'ex01',
				subtestInstanceId: 'inst1',
				subtestJson: subtestJson
		))
		qiAssessmentSubtest.subtestJson = subtestJson

		when:
		assessmentSubtestDao.save(qiAssessmentSubtest)
		def subtest = assessmentSubtestDao.retrieveDocument(qiAssessmentSubtest, "subtestJson")
		def thirdItemGroupTitle = assessmentSubtestDao.retrieveDocument(qiAssessmentSubtest, "subtestJson.itemGroups.title")

		then:
		subtest.subtestJson.displayName == "Test of Articulation"
		thirdItemGroupTitle.subtestJson.itemGroups.collect { it.title } == [
				"Sounds-in-Words",
				"Sounds-in-Sentences",
				"Stimulability"
		]
	}
}
