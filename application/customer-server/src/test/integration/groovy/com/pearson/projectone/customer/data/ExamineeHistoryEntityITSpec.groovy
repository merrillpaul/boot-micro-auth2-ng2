package com.pearson.projectone.customer.data

import com.pearson.projectone.customer.BaseCustomerServerIntgTest
import com.pearson.projectone.data.dao.customer.ExamineeHistoryDao
import com.pearson.projectone.data.entity.customer.ExamineeHistory
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.OptimisticLockingFailureException

class ExamineeHistoryEntityITSpec extends BaseCustomerServerIntgTest {

	@Autowired
	ExamineeHistoryDao examineeHistoryDao

	def historyJson

	def setup() {
		historyJson = this.getClass().getResource("/testFiles/examinee_history_test.json").text
	}

	def cleanup() {
		this.examineeHistoryDao.deleteAll()
	}

	def "should extract json using projections"() {
		given: 'we have an examinee history'
		def history = examineeHistoryDao.save(new ExamineeHistory(examineeId: "ex001", historyJson: historyJson))

		when:
		def currentCity = examineeHistoryDao.retrieveDocument(history, "historyJson.current_city")
		def subtests = examineeHistoryDao.retrieveDocument(history, "historyJson.subtests")

		then:
		currentCity.historyJson.current_city == "Tokyo"
		subtests.historyJson.subtests.size() == 2
		subtests.historyJson.subtests == [
				[name: 'Listening Comprehension', guid: '1233'],
				[name: 'Reading Comprehension', guid: '2222']
		]
	}

	def "should extract entire history document"() {
		given: 'we have an examinee history'
		def history = examineeHistoryDao.save(new ExamineeHistory(examineeId: "ex001", historyJson: historyJson))

		when:
		def document = examineeHistoryDao.retrieveDocument(history.id, "historyJson")

		then:
		new JsonSlurper().parseText(JsonOutput.toJson(document.historyJson)) == new JsonSlurper().parseText(historyJson)

	}

	def "should return history for examinee id"() {
		given: 'create some history records'
		examineeHistoryDao.save(new ExamineeHistory(examineeId: "ex001", historyJson: historyJson))
		examineeHistoryDao.save(new ExamineeHistory(examineeId: "ex002", historyJson: historyJson))
		examineeHistoryDao.save(new ExamineeHistory(examineeId: "ex001", historyJson: historyJson))

		when:
		def histories = examineeHistoryDao.findByExamineeId("ex001")

		then:
		histories.size() == 2
	}


	def "should fail optimistic concurrency if stale version is tried to be saved"() {
		given: 'we have an examinee history'
		def staleHistory = examineeHistoryDao.save(new ExamineeHistory(examineeId: "ex001", historyJson: historyJson))
		def firstUpdated = examineeHistoryDao.findOne(staleHistory.id)
		firstUpdated.examineeId = 'newex001'
		examineeHistoryDao.save(firstUpdated)
		examineeHistoryDao.save(firstUpdated)

		when: 'we try to persist a stale object'
		staleHistory.examineeId = 'stale001'
		examineeHistoryDao.save(staleHistory)

		then:
		staleHistory.version == 1
		firstUpdated.version == 2
		thrown(OptimisticLockingFailureException)
	}
}
