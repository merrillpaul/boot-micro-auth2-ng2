package com.pearson.projectone.global.resource;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.data.entity.global.library.assessment.AssessTestDomain;
import com.pearson.projectone.global.dto.testdomain.TestDomainDTO;
import com.pearson.projectone.global.service.TestDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Component
@RestApi
@PreAuthorize("hasAuthority('ROLE_SYSTEM_ADMIN')")
public class TestDomainResource {

	@Autowired
	private TestDomainService testDomainService;

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public TestDomainDTO get(@PathVariable String id) {
		return testDomainService.getTestDomain(id);
	}

	@GetMapping("/name/{name}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public ResponseEntity byName(@PathVariable String name) {
		TestDomainDTO dto = testDomainService.getTestDomainByName(name);
		if (dto != null) {
			return new ResponseEntity(dto, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	@PutMapping
	public ResponseEntity<BaseDTO> create(@RequestBody TestDomainDTO createCommand) {
		AssessTestDomain assessTestDomain = testDomainService.createTestDomain(createCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(assessTestDomain.getId(), assessTestDomain.getVersion()),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BaseDTO> update(@RequestBody TestDomainDTO updateCommand) {
		AssessTestDomain assessTestDomain = testDomainService.updateTestDomain(updateCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(assessTestDomain.getId(), assessTestDomain.getVersion()),
				HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestBody BaseDTO deleteCommand) {
		testDomainService.deleteTestDomain(deleteCommand.getId());
	}

	@GetMapping("list")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public List<TestDomainDTO> list() {
		return testDomainService.getAllDomains();
	}
}
