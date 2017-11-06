package com.pearson.projectone.global.service;

import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.data.search.jpa.JpaSearchSpecificationBuilder;
import com.pearson.projectone.data.dao.global.library.assessment.AssessSubtestDao;
import com.pearson.projectone.data.dao.global.library.assessment.AssessSubtestTypeDao;
import com.pearson.projectone.data.dao.global.library.assessment.AssessTestDao;
import com.pearson.projectone.data.dao.global.library.assessment.AssessTestDomainDao;
import com.pearson.projectone.data.entity.global.library.assessment.AssessSubtest;
import com.pearson.projectone.data.entity.global.library.assessment.AssessSubtestType;
import com.pearson.projectone.data.entity.global.library.assessment.AssessTest;
import com.pearson.projectone.data.entity.global.library.assessment.AssessTestDomain;
import com.pearson.projectone.data.entity.global.library.assessment.Assessment;
import com.pearson.projectone.global.dto.assesstest.AssessSubtestDTO;
import com.pearson.projectone.global.dto.assesstest.AssessTestResultDTO;
import com.pearson.projectone.global.dto.assesstest.BaseAssessSubtestDTO;
import com.pearson.projectone.global.dto.assesstest.CreateAssessTestCommandDTO;
import com.pearson.projectone.global.dto.assesstest.SimpleAssessTestDTO;
import com.pearson.projectone.global.dto.assesstest.UpdateAssessTestCommandDTO;
import com.pearson.projectone.global.dto.subtesttype.SubtestTypeDTO;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssessTestService {
	@Autowired
	private AssessSubtestDao subtestDao;

	@Autowired
	private AssessTestDao testDao;

	@Autowired
	private AssessTestDomainDao testDomainDao;

	@Autowired
	private AssessSubtestTypeDao subtestTypeDao;

	@Autowired
	private JsonParser jsonParser;


	public AssessTestResultDTO findTestByIdNameOrGuid(String id, String name, String guid) {
		// TODO impl
		return null;
	}

	public AssessTest createTest(CreateAssessTestCommandDTO createAssessTestCommand) {
		AssessTest test = new AssessTest();
		// TODO copy props
		return testDao.saveAndFlush(test);
	}

	public AssessTest updateTest(UpdateAssessTestCommandDTO updateAssessTestCommand) {
		AssessTest test = testDao.findOne(updateAssessTestCommand.getId());
		// TODO copy props
		return testDao.saveAndFlush(test);
	}

	public void deleteTest(String id) {
		// TODO impl
	}

	public Page<AssessTestResultDTO> listTests(PageableSearchRequestDTO searchRequest) {
		Specification<AssessTest> specification = new JpaSearchSpecificationBuilder<>()
				.with(searchRequest.getCriteria()).build();
		List<AssessTestResultDTO> list = new ArrayList<>(0);
		if (ObjectUtils.isEmpty(specification)) {
			return testDao.findAll(searchRequest.toPageable()).map(getTestConverter());
		} else {
			return testDao.findAll(specification, searchRequest.toPageable()).map(getTestConverter());
		}
	}

	public void saveTestFromJson(String metadataJson) {
		Map<String, Object> json = jsonParser.parseMap(metadataJson);

		// create domain if not exists
		String domain = json.get("domain").toString();
		if (testDomainDao.countByName(domain) == 0) {
			testDomainDao.save(new AssessTestDomain(domain));
		}
		List<Map<String, Object>> subtests = (List) json.get("subtests");
		List<String> catNames = subtests.stream().map(subtest -> {
			Map metaData = (Map) subtest.get("metaData");
			return metaData.get("subtestCategory").toString();
		}).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());


		// create categories if not exists
		catNames.forEach(name -> {
			if (!ObjectUtils.isEmpty(name) && subtestTypeDao.countByName(name) == 0) {
				AssessSubtestType type = new AssessSubtestType();
				type.setName(name);
				subtestTypeDao.save(type);
			}
		});
		saveAssessTest(json);
	}

	public AssessSubtestDTO findSubtestByIdNameOrGuid(String id, String name, String guid) {
		// TODO impl
		return null;
	}

	public AssessSubtest createSubtest(AssessSubtestDTO createCommand) {
		// TODO impl
		AssessSubtest assessSubtest = new AssessSubtest();
		return subtestDao.saveAndFlush(assessSubtest);
	}

	public AssessSubtest updateSubtest(BaseAssessSubtestDTO updateCommand) {
		AssessSubtest assessSubtest = subtestDao.findOne(updateCommand.getId());
		// TODO copy props
		return subtestDao.saveAndFlush(assessSubtest);
	}

	public void deleteSubtest(String id) {
		// TODO impl
	}

	public Page<AssessSubtestDTO> listSubtests(PageableSearchRequestDTO searchRequest) {
		Specification<AssessSubtest> specification = new JpaSearchSpecificationBuilder<>()
				.with(searchRequest.getCriteria()).build();
		List<AssessSubtestDTO> list = new ArrayList<>(0);
		if (ObjectUtils.isEmpty(specification)) {
			return subtestDao.findAll(searchRequest.toPageable()).map(getSubtestConverter());
		} else {
			return subtestDao.findAll(specification, searchRequest.toPageable()).map(getSubtestConverter());
		}
	}


	public Page<AssessSubtestDTO> listSubtests(PageableSearchRequestDTO searchRequest, String testId) {
		Specification<AssessSubtest> specification = new JpaSearchSpecificationBuilder<>()
				.with(searchRequest.getCriteria()).build();

		Specification<AssessSubtest> testSpecification = hasTest(testId);
		if (ObjectUtils.isEmpty(specification)) {
			specification = testSpecification;
		} else {
			specification = Specifications.where(testSpecification).and(specification);
		}
		List<AssessSubtestDTO> list = new ArrayList<>(0);
		return subtestDao.findAll(specification, searchRequest.toPageable()).map(getSubtestConverter());
	}

	private Converter<AssessSubtest, AssessSubtestDTO> getSubtestConverter() {
		return new Converter<AssessSubtest, AssessSubtestDTO>() {
			@Override
			public AssessSubtestDTO convert(AssessSubtest source) {
				AssessSubtestDTO dto = new AssessSubtestDTO();
				dto.setId(source.getId());
				dto.setVersion(source.getVersion());
				// TODO copy rest of props
				SubtestTypeDTO type = new SubtestTypeDTO();
				type.setId(source.getSubtestType().getId());
				type.setName(source.getSubtestType().getName());
				dto.setSubtestType(type);
				SimpleAssessTestDTO test = new SimpleAssessTestDTO();
				test.setId(source.getTest().getId());
				test.setName(source.getTest().getDisplayName());
				dto.setTest(test);
				return dto;
			}
		};
	}

	private Converter<AssessTest, AssessTestResultDTO> getTestConverter() {
		return new Converter<AssessTest, AssessTestResultDTO>() {
			@Override
			public AssessTestResultDTO convert(AssessTest source) {
				AssessTestResultDTO dto = new AssessTestResultDTO();
				dto.setId(source.getId());
				dto.setVersion(source.getVersion());
				// TODO copy rest of props
				return dto;
			}
		};
	}

	/**
	 * Gets a JPA specification condition for 'belongs' to test
	 *
	 * @param testId
	 * @return
	 */
	private Specification<AssessSubtest> hasTest(final String testId) {
		AssessTest assessTest = testDao.findOne(testId);
		return (root, query, cb) -> {
			Root<AssessSubtest> subtest = root;
			Expression<Assessment> testExpression = subtest.get("test");
			return cb.and(cb.equal(testExpression, assessTest));
		};
	}

	/**
	 * Saves an Assess test
	 *
	 * @param json
	 * @return
	 */
	private AssessTest saveAssessTest(Map<String, Object> json) {
		AssessTest assessTest = testDao.findByGuid(json.get("testGUID").toString());
		AssessTestDomain domain = testDomainDao.findByName(json.get("domain").toString());

		if (assessTest == null) {
			assessTest = new AssessTest();
			assessTest.setGuid(json.get("testGUID").toString());
		}
		assessTest.setName(json.get("name").toString());
		assessTest.setDisplayName(json.get("displayName").toString());
		assessTest.setDescription(json.get("description").toString());
		assessTest.setNormType(json.get("normType").toString());
		Object observations = org.apache.commons.lang3.ObjectUtils.defaultIfNull(json.get("hasObservations"), false);
		assessTest.setObservationsEnabled(BooleanUtils.toBoolean(observations.toString()));
		assessTest.setName(json.get("name").toString());
		assessTest.setDomain(domain);
		testDao.save(assessTest);

		List<Map<String, Object>> subtests = (List) json.get("subtests");
		Set<AssessSubtest> assessSubtests = new HashSet<>(0);

		for (Map<String, Object> subtestJson : subtests) {
			assessSubtests.add(saveSubtest(subtestJson, assessTest));
		}
		assessTest.setSubtests(assessSubtests);
		testDao.saveAndFlush(assessTest);
		return assessTest;
	}

	/**
	 * Saves subtest
	 *
	 * @param subtestJson
	 * @param assessTest
	 */
	private AssessSubtest saveSubtest(Map<String, Object> subtestJson, AssessTest assessTest) {
		AssessSubtest subtest = subtestDao.findByGuid(subtestJson.get("subtestGUID").toString());
		AssessTest test = testDao.findByGuid(subtestJson.get("testGUID").toString());
		AssessSubtestType subtestType = subtestTypeDao.findByName(((Map) subtestJson.get("metaData"))
				.get("subtestCategory").toString());
		Object observations = org.apache.commons.lang3.ObjectUtils.defaultIfNull(subtestJson.get("hasObservations"), false);

		if (ObjectUtils.isEmpty(subtest)) {
			subtest = new AssessSubtest();
			subtest.setGuid(subtestJson.get("subtestGUID").toString());
		}

		subtest.setName(subtestJson.get("name").toString());
		subtest.setDisplayName(subtestJson.get("displayName").toString());
		subtest.setAbbreviation(subtestJson.get("abbr") == null ? null : subtestJson.get("abbr").toString());
		Map metadata = (Map) subtestJson.get("metaData");
		subtest.setAverageDuration(NumberUtils.toInt(metadata.get("averageDuration").toString()));
		//subtest.setDescription(metadata.get("descriptionCS").toString());
		subtest.setSubtestType(subtestType);
		subtest.setTest(assessTest);
		subtest.setObservationsEnabled(BooleanUtils.toBoolean(observations.toString()));

		// TODO add min, max ages
		subtestDao.save(subtest);
		return subtest;
	}

	/**
	 * checks AssessTest with the id exists or not.
	 *
	 * @param id AssessTest id
	 * @return true if AssessTest exists, false otherwise.
	 */
	public boolean isAssessTestExist(final String id) {
		return this.testDao.exists(id);
	}

	/**
	 * Finds AssessTest with id.
	 *
	 * @param id AssessTest id
	 * @return AssessTest entity
	 */
	public AssessTest findOne(final String id) {
		return this.testDao.findOne(id);
	}

}
