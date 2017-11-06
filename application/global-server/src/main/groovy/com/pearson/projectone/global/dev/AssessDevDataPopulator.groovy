package com.pearson.projectone.global.dev

import com.pearson.projectone.core.support.populator.BasePopulator
import com.pearson.projectone.global.service.AssessTestService
import groovy.io.FileType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(0)
class AssessDevDataPopulator implements BasePopulator {
	@Autowired
	AssessTestService assessTestService

	void populate() {
		importAssessTestMetadata()
	}

	private importAssessTestMetadata() {
		new File('../../tools/development/data/cit/allTestMetaDataExport').eachFileMatch(FileType.FILES, ~/.*\.json/, {
			assessTestService.saveTestFromJson(it.getText("UTF-8"))
		})
	}
}
