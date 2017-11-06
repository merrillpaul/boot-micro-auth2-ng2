package com.pearson.projectone.service

import com.pearson.projectone.BaseGlobalServerIntegrationSpec
import com.pearson.projectone.data.dao.global.library.assessment.AssessSubtestDao
import com.pearson.projectone.data.dao.global.library.assessment.AssessTestDao
import com.pearson.projectone.global.service.AssessTestService
import org.springframework.beans.factory.annotation.Autowired

class AssessTestServiceITSpec extends BaseGlobalServerIntegrationSpec {

	def testMetadataJson = this.getClass().getResource("/testFiles/assessMetadata/test.json").text

	@Autowired
	AssessTestDao testDao

	@Autowired
	AssessSubtestDao subtestDao

	@Autowired
	AssessTestService assessTestService



	def "save domain subtest categories , test and subtest data from json" () {
		given:
		assessTestService.saveTestFromJson(testMetadataJson)

		when:
		def tests = testDao.findAll()

		then:
		tests.size() == 1
		tests[0].subtests.size() == 1
		tests[0].displayName == "GFTA-2"
		tests[0].subtests[0].name == "Test of Articulation"
	}
}
