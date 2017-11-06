package com.pearson.projectone.customer.data

import com.pearson.projectone.customer.BaseCustomerServerIntgTest
import com.pearson.projectone.data.dao.customer.ExamineeDetailDao
import com.pearson.projectone.data.entity.customer.ExamineeDetails
import org.springframework.beans.factory.annotation.Autowired

class ExamineeDetailITSpec extends BaseCustomerServerIntgTest {
	@Autowired
	ExamineeDetailDao examineeDetailDao

	def cleanup() {
		examineeDetailDao.deleteAll()
	}

	def "should return details for examinee"() {
		given: 'setup three examinee details'
		examineeDetailDao.save(new ExamineeDetails(examineeId: 'ex0001'))
		examineeDetailDao.save(new ExamineeDetails(examineeId: 'ex0002'))
		examineeDetailDao.save(new ExamineeDetails(examineeId: 'ex0001'))

		when:
		def list = examineeDetailDao.findByExamineeId('ex0001')

		then:
		list.size() == 2
		list.collect { it.examineeId }.unique() == ['ex0001']
	}
}
