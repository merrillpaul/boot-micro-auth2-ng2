package com.pearson.projectone.global.dev

import com.pearson.projectone.core.support.populator.BasePopulator
import com.pearson.projectone.data.dao.global.library.DomainDao
import com.pearson.projectone.data.dao.global.library.account.AccountDao
import com.pearson.projectone.data.dao.global.library.account.BusinessUnitDao
import com.pearson.projectone.data.dao.global.library.account.UserAccountDao
import com.pearson.projectone.data.dao.global.library.assessment.AssessmentDao
import com.pearson.projectone.data.dao.global.library.assessment.BusinessUnitAssessmentDao
import com.pearson.projectone.data.entity.global.library.Domain
import com.pearson.projectone.data.entity.global.library.account.Account
import com.pearson.projectone.data.entity.global.library.account.BusinessUnit
import com.pearson.projectone.data.entity.global.library.account.UserAccount
import com.pearson.projectone.data.entity.global.library.assessment.BusinessUnitAssessment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
 * Populates the global schema for dev env
 */
@Component
@Order(1)
class GlobalAccountDevDataPopulator implements BasePopulator {

	@Autowired
	BusinessUnitDao businessUnitDao

	@Autowired
	AccountDao accountDao

	@Autowired
	UserAccountDao userAccountDao

	@Autowired
	AssessmentDao assessmentDao

	@Autowired
	BusinessUnitAssessmentDao businessUnitAssessmentDao

	@Autowired
	DomainDao domainDao

	@Override
	void populate() {
		createDomains(DEVXML.domain)
		createBusinessUnits(DEVXML.business_units[0].business_unit)
	}

	private createBusinessUnits(businessUnits) {
		businessUnits.each {
			def bu = businessUnitDao.save(new BusinessUnit(
					id: it.@id,
					guid: it.@guid,
					name: it.@name,
					firstName: it.@firstName,
					lastName: it.@lastName,
					middleName: it.@middleName,
					mainContactPhone: it.@phone,
					mainContactEmail: it.@email,
					timezoneId: it.@timezoneId
			))

			it.account.each { acc ->
				def account = accountDao.save(new Account(
						id: acc.@id,
						name: acc.@name,
						ownerNumber: acc.@owner,
						ownerFirstName: acc.@firstName,
						ownerLastName: acc.@lastName,
						businessUnit: bu
				))
				acc.user.each { user ->
					userAccountDao.save(new UserAccount(
							userId: user.@id,
							account: account
					))
				}
			}
		}

		// map assessments to business unit
		def usBu = businessUnitDao.findByGuid("8456fe717d5f449b8b7620d18f883318")
		assessmentDao.findAll().each {
			businessUnitAssessmentDao.save(new BusinessUnitAssessment(
					businessUnit: usBu,
					assessment: it
			))
		}

	}

	private createDomains(domains) {
		domains.each {
			domainDao.save(new Domain(
					code: it.@code,
					description: it.@name,
					type: it.@type,
					ordering: Integer.parseInt(it.@order)
			))
		}
	}

}
