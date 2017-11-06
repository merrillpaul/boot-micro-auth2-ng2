package com.pearson.projectone.customer.querydsl

import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO
import com.pearson.projectone.core.support.data.search.SearchCriteriaDTO
import com.pearson.projectone.core.support.data.search.page.OrderDTO
import com.pearson.projectone.core.support.data.search.page.PageRequestDTO
import com.pearson.projectone.core.support.data.search.querydsl.QueryDslPredicateBuilder
import com.pearson.projectone.customer.BaseCustomerServerIntgTest
import com.pearson.projectone.data.constants.Gender
import com.pearson.projectone.data.dao.customer.ExamineeDao
import com.pearson.projectone.data.entity.customer.examinee.Examinee
import com.pearson.projectone.data.entity.customer.examinee.QExaminee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort

class QueryDslExamineeITSpec extends BaseCustomerServerIntgTest {
	@Autowired
	ExamineeDao examineeDao

	def examinees

	def setup() {
		examinees = []
	}

	def cleanup() {
		examineeDao.deleteAll()
	}

	def "should paginate examinees"() {
		given:
		(0..30).each {
			examineeDao.save(new Examinee(
					examineeId: "Ex00_${it}", firstName: "Jane", lastName: "Student", gender: Gender.MALE,
					archived: true, practiceMode: false))
		}
		def orders = [new OrderDTO(property: "examineeId", direction: Sort.Direction.DESC)]
		def pageRequest = new PageableSearchRequestDTO()
		pageRequest.setPageRequest(new PageRequestDTO(3, 2, orders))

		when:
		def page = examineeDao.findAll(null, pageRequest.toPageable())

		then:
		page.totalElements == 31
		page.totalPages == 16
		!page.isFirst()
		!page.isLast()
		page.number == 3
		page.content.collect { it.examineeId } == ["Ex00_30", "Ex00_3"]
	}

	def "should search for equals"() {
		given:
		createExaminees()
		def predicate = new QueryDslPredicateBuilder()
				.with(new SearchCriteriaDTO("firstName", "=", "Gary")).build(Examinee)

		when:
		def list = examineeDao.findAll(predicate)

		then:
		list.size() == 1
		"${list[0].firstName} ${list[0].lastName}" == "Gary Oldman"
	}

	def "should search with qdsl generated code"() {
		given:
		createExaminees()
		def predicate = QExaminee.examinee.lastName.containsIgnoreCase("niro")

		when:
		def list = examineeDao.findAll(predicate)

		then:
		list.size() == 1
		"${list[0].firstName} ${list[0].lastName}" == "Robert DeNiro"
	}

	def "should paginate and search"() {
		given:
		createExaminees()
		def orders = [new OrderDTO(property: "lastName", direction: Sort.Direction.DESC)]
		def pageRequest = new PageableSearchRequestDTO(criteria: [new SearchCriteriaDTO("accountId", "_*", "CA")])
		pageRequest.setPageRequest(new PageRequestDTO(0, 1, orders))
		def predicate = new QueryDslPredicateBuilder()
				.with(pageRequest.criteria).build(Examinee)

		when:
		def page = examineeDao.findAll(predicate, pageRequest.toPageable())

		then:
		page.totalElements == 2
		page.totalPages == 2
		page.isFirst()
		!page.isLast()
		page.number == 0
		page.content.collect { "${it.firstName} ${it.lastName}" } == ["Helen Mirren"]
	}

	def "should search with a boolean"() {
		given:
		createExaminees()
		def predicate = new QueryDslPredicateBuilder()
				.with(new SearchCriteriaDTO("practiceMode", "=", "true")).build(Examinee)
		def nepredicate = new QueryDslPredicateBuilder()
				.with(new SearchCriteriaDTO("practiceMode", "!", "true")).build(Examinee)

		when:
		def trueList = examineeDao.findAll(predicate)
		def falseList = examineeDao.findAll(nepredicate)

		then:
		trueList.size() == 1
		falseList.size() == 3
	}


	def "should search with multiple combo"() {
		given:
		createExaminees()
		def predicate = new QueryDslPredicateBuilder()
				.with(
				new SearchCriteriaDTO("practiceMode", "=", "false"),
				new SearchCriteriaDTO("accountId", "_*_", "US")
		).build(Examinee)

		when:
		def list = examineeDao.findAll(predicate)

		then:
		list.size() == 2
		list.collect { it.firstName } == ["Jane", "Gary"]
	}


	def "should search for enum"() {
		given:
		createExaminees()
		def predicate = new QueryDslPredicateBuilder()
				.with(new SearchCriteriaDTO("gender", "=", "FEMALE")
		).build(Examinee)

		when:
		def list = examineeDao.findAll(predicate)

		then:
		list.size() == 2
		list.collect { it.firstName } == ["Jane", "Helen"]
	}

	def "should search for a numeric range"() {
		given:
		createExaminees()
		// to change the version number
		(0..2).each {
			examinees[1].lastName += " Modified"
			examineeDao.save(examinees[1])
		}

		(0..4).each {
			examinees[0].lastName += " Modified"
			examineeDao.save(examinees[0])
		}

		(0..6).each {
			examinees[3].lastName += " Modified"
			examineeDao.save(examinees[3])
		}

		def predicate = new QueryDslPredicateBuilder()
				.with(new SearchCriteriaDTO("version", "=", "0")
		).build(Examinee)

		def predicateLess6 = new QueryDslPredicateBuilder()
				.with(new SearchCriteriaDTO("version", "<", "6")
		).build(Examinee)

		def predicateGt3 = new QueryDslPredicateBuilder()
				.with(new SearchCriteriaDTO("version", ">", "3")
		).build(Examinee)

		def comboPredicate = new QueryDslPredicateBuilder()
				.with(new SearchCriteriaDTO("version", ">", "3"), new SearchCriteriaDTO("version", "<", "6")
		).build(Examinee)

		when:
		def newList = examineeDao.findAll(predicate)
		def less5 = examineeDao.findAll(predicateLess6)
		def gt3 = examineeDao.findAll(predicateGt3)
		def comboList = examineeDao.findAll(comboPredicate)

		then:
		newList.collect { it.firstName } == ["Gary"]
		less5.collect { it.firstName } == ["Jane", "Robert", "Gary"]
		gt3.collect { it.firstName } == ["Jane", "Robert", "Helen"]
		comboList.collect { it.firstName } == ["Jane", "Robert"]
	}


	private createExaminees() {
		examinees <<
				examineeDao.save(new Examinee(
						examineeId: "Ex00_1", firstName: "Jane", lastName: "Fonda", gender: Gender.FEMALE, accountId: "PSN US",
						archived: true, practiceMode: false))
		examinees <<
				examineeDao.save(new Examinee(
						examineeId: "Ex00_2", firstName: "Robert", lastName: "DeNiro", gender: Gender.MALE, accountId: "PSN CA",
						archived: true, practiceMode: true))
		examinees <<
				examineeDao.save(new Examinee(
						examineeId: "Ex00_3", firstName: "Gary", lastName: "Oldman", gender: Gender.MALE, accountId: "PSN US",
						archived: true, practiceMode: false))
		examinees <<
				examineeDao.save(new Examinee(
						examineeId: "Ex00_4", firstName: "Helen", lastName: "Mirren", gender: Gender.FEMALE, accountId: "PSN CA",
						archived: true, practiceMode: false))
	}
}
