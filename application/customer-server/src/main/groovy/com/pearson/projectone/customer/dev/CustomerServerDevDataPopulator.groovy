package com.pearson.projectone.customer.dev

import com.pearson.projectone.core.support.populator.BasePopulator
import com.pearson.projectone.data.constants.Gender
import com.pearson.projectone.data.dao.customer.ExamineeDao
import com.pearson.projectone.data.entity.customer.examinee.Examinee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(1)
class CustomerServerDevDataPopulator implements BasePopulator {

	@Autowired
	ExamineeDao examineeDao

	@Override
	void populate() {
		def e = examineeDao.save(new Examinee(examineeId: "Ex001", firstName: "Jane", lastName: "Student", gender: Gender.FEMALE, dob: new Date()))
	}
}
