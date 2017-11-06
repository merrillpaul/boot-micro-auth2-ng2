package com.pearson.projectone.global.service;

import com.pearson.projectone.data.dao.global.library.assessment.AssessTestDomainDao;
import com.pearson.projectone.data.entity.global.library.assessment.AssessTestDomain;
import com.pearson.projectone.global.dto.testdomain.TestDomainDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestDomainService {

	@Autowired
	private AssessTestDomainDao testDomainDao;

	public TestDomainDTO getTestDomain(String id) {
		TestDomainDTO domain = new TestDomainDTO();
		AssessTestDomain testDomain = testDomainDao.findOne(id);
		domain.setId(id);
		domain.setName(testDomain.getName());
		domain.setVersion(testDomain.getVersion());
		return domain;
	}

	public TestDomainDTO getTestDomainByName(String name) {
		// TODO impl
		return null;
	}

	public AssessTestDomain createTestDomain(TestDomainDTO testDomainDTO) {
		AssessTestDomain domain = new AssessTestDomain();
		domain.setName(testDomainDTO.getName());
		return testDomainDao.saveAndFlush(domain);
	}

	public AssessTestDomain updateTestDomain(TestDomainDTO testDomainDTO) {
		AssessTestDomain domain = testDomainDao.findOne(testDomainDTO.getId());
		domain.setName(testDomainDTO.getName());
		return testDomainDao.saveAndFlush(domain);
	}

	public void deleteTestDomain(String id) {
		testDomainDao.delete(id);
	}

	public List<TestDomainDTO> getAllDomains() {
		List<TestDomainDTO> domains = new ArrayList<>(0);
		testDomainDao.findAll().forEach(testDomain -> {
			TestDomainDTO domain = new TestDomainDTO();
			domain.setName(testDomain.getName());
			domain.setId(testDomain.getId());
			domain.setVersion(testDomain.getVersion());
			domains.add(domain);
		});
		return domains;
	}
}
