package com.pearson.projectone.customer.data

import com.pearson.projectone.authcommons.dto.AppUserDetails
import com.pearson.projectone.customer.BaseCustomerServerIntgTest
import com.pearson.projectone.data.constants.Gender
import com.pearson.projectone.data.dao.customer.ExamineeDao
import com.pearson.projectone.data.entity.customer.examinee.Examinee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

import java.sql.Timestamp
import java.time.LocalDateTime

class ExamineeITSpec extends BaseCustomerServerIntgTest {

	@Autowired
	ExamineeDao examineeDao

	def loggedOnUser

	def setup() {
		loggedOnUser = new AppUserDetails("lg111", 'jdoe', 'John', 'Doe', 'Dr.',
				'America/Chicago', new HashMap<String, Serializable>(0), null)
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
				loggedOnUser, 'na', loggedOnUser.getAuthorities()))

	}

	def cleanup() {
		SecurityContextHolder.clearContext()
		examineeDao.deleteAll()
	}

	def "should persist an examinee"() {
		when:
		def examinee = examineeDao.save(new Examinee(examineeId: "Ex001", firstName: "Jane", lastName: "Student",
				createdBy: 'mpaul', lastCreated: Timestamp.valueOf(LocalDateTime.now())))
		def lastnameFld = examineeDao.retrieveDocument(examinee.id, "lastName")

		then:
		examinee.id != null
		lastnameFld.lastName == "Student"
	}

	def "should persist a new examinee with timestamps and logged on user information"() {
		when:
		def examinee = examineeDao.save(new Examinee(examineeId: "Ex001", firstName: "Jane", lastName: "Student"))

		then:
		examinee.lastCreated != null
		examinee.lastUpdated != null
		examinee.createdBy == examinee.updatedBy
		examinee.createdBy == loggedOnUser.id
	}

	def "should tag the logged on user's id when entity is updated and increment version"() {
		when:
		def examinee = examineeDao.save(new Examinee(examineeId: "Ex001", firstName: "Jane", lastName: "Student"))
		def initialVersion = examinee.version
		SecurityContextHolder.clearContext()
		and: 'set a new loggedon user'
		def secondUser = new AppUserDetails("lg222", 'jdoe', 'John', 'Doe', 'Dr.',
				'America/Chicago', new HashMap<String, Serializable>(0), null)
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
				secondUser, 'na', secondUser.getAuthorities()))
		examinee = examineeDao.findOne(examinee.id)
		examinee.firstName = 'Bob'
		examinee = examineeDao.save(examinee)
		examinee = examineeDao.findOne(examinee.id)
		def newVersion = examinee.version

		then:
		examinee.createdBy == 'lg111'
		examinee.updatedBy == 'lg222'
		initialVersion == 0
		newVersion == 1
	}

	def "should persist a new examinee with gender and boolean attributes"() {
		when:
		def examinee = examineeDao.save(new Examinee(
				examineeId: "Ex001", firstName: "Jane", lastName: "Student", gender: Gender.MALE,
				archived: true, practiceMode: false))
		examinee = examineeDao.findOne(examinee.id)

		then:
		examinee.gender == Gender.MALE
		examinee.archived
		!examinee.practiceMode
		!examinee.deleted
	}
}
