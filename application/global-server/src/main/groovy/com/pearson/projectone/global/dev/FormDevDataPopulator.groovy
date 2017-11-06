package com.pearson.projectone.global.dev

import com.pearson.projectone.core.support.populator.BasePopulator
import com.pearson.projectone.data.dao.global.library.assessment.AssessmentDao
import com.pearson.projectone.data.dao.global.library.assessment.FormDao
import com.pearson.projectone.data.dao.global.library.assessment.FormDeliveryTypeDao
import com.pearson.projectone.data.entity.global.library.assessment.Assessment
import com.pearson.projectone.data.entity.global.library.assessment.Form
import com.pearson.projectone.data.entity.global.library.assessment.FormDeliveryType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils

/**
 * Populate Dev data for Assessments and Forms.
 */
@Component
@Order(1)
class FormDevDataPopulator implements BasePopulator {

	@Autowired
	AssessmentDao assessmentDao;

	@Autowired
	FormDao formDao;

	@Autowired
	FormDeliveryTypeDao formDeliveryTypeDao;


	private static final String DEV_ASSESSMENT_DATA_FILE_NAME = '../../tools/development/data/dev-assessment-data.xml';
	private static final String DEV_FORM_DATA_FILE_NAME = '../../tools/development/data/dev-form-data.xml';
	private static final String DEV_FORM_DELIVERY_TYPE = '../../tools/development/data/dev-form-delivery-type.xml';


	@Override
	void populate() {
		populateAssessmentsAndForms()
	}

	void populateAssessmentsAndForms() {

		Map<String, Assessment> assessmentIdMap = new HashMap<>();
		Map<String, Form> formIdMap = new HashMap<>();

		def assessments = new XmlParser().parseText(new File(DEV_ASSESSMENT_DATA_FILE_NAME).text)
		assessments.ROW.each {

			Assessment entity = new Assessment(
					it.COLUMN[1].text(), //acronym
					it.COLUMN[2].text(), //name
					it.COLUMN[3].text(), //typeId
					it.COLUMN[4].text(),  //licencedBy
					it.COLUMN[5].text(),  // requiredQualLevel
					Date.parse("dd-MM-yy hh:mm:ss", it.COLUMN[6].text()),  //startDate
					null,  //expiryDate
					it.COLUMN[8].text(),  //comments
					it.COLUMN[13].text(), //statusId
					it.COLUMN[14].text(), //productCode
					it.COLUMN[16].text(), //configSourceId
					it.COLUMN[17].text(), // definitionJson
					it.COLUMN[18].text(), //qgiVersion
					((it.COLUMN[19].text() == 'N') ? false : true),  // dataCollectionEnabled
					((it.COLUMN[20].text() == 'N') ? false : true),  // apiEnabled
					"", // assessmentDefinition
					UUID.randomUUID().toString());

			Assessment assessment = this.assessmentDao.save(entity);
			assessmentIdMap.put(it.COLUMN[0].text(), assessment);
		}

		/**
		 * Insert Forms.
		 */
		def forms = new XmlParser().parseText(new File(DEV_FORM_DATA_FILE_NAME).text)
		forms.ROW.each {
			Form form = new Form(
					(String) it.COLUMN[3].text(), // acronym
					(String) it.COLUMN[2].text(), // name
					(Assessment) assessmentIdMap.get(it.COLUMN[1].text()), //assessment
					(String) it.COLUMN[4].text(), //production code
					(String) it.COLUMN[5].text(), // description
					(String) it.COLUMN[10].text(), // statusId
					(String) it.COLUMN[11].text(), // formDefinition
					(Form) null, //it.COLUMN[12].text(), // parent form
					(String) it.COLUMN[14].text(), //dataCollectionExportDef
					(Long) (ObjectUtils.isEmpty(it.COLUMN[15].text()) ? new Long(2121) : Long.parseLong(it.COLUMN[15].text())), //osaEngineId
					(Boolean) ObjectUtils.isEmpty(it.COLUMN[16].text()) ? false : ("Y".equalsIgnoreCase(it.COLUMN[16].text()) ? true : false),  //groupAdministration
					(String) it.COLUMN[17].text(), //formType
					null //assessTest
			);

			Form formEntity = this.formDao.save(form)
			formIdMap.put(it.COLUMN[0].text(), formEntity);
		}

		/**
		 * Insert Form_delivery_type
		 */
		def formDeliveryTypes = new XmlParser().parseText(new File(DEV_FORM_DELIVERY_TYPE).text)
		println "FORMDELIEVRYTYPE insert"
		formDeliveryTypes.ROW.each {
			if (!ObjectUtils.isEmpty(formIdMap.get(it.COLUMN[1].text()))) {
				FormDeliveryType formDeliveryType = new FormDeliveryType(
						formIdMap.get(it.COLUMN[1].text()).getId(), // form_id
						it.COLUMN[2].text(), // delivery_type_id
				);
				this.formDeliveryTypeDao.save(formDeliveryType);
			}

		}
	}
}
