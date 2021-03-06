package com.pearson.projectone.global.resource;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.data.entity.global.library.assessment.AssessSubtest;
import com.pearson.projectone.global.dto.assesstest.AssessSubtestDTO;
import com.pearson.projectone.global.dto.assesstest.BaseAssessSubtestDTO;
import com.pearson.projectone.global.service.AssessTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@RestApi
@PreAuthorize("hasAuthority('ROLE_SYSTEM_ADMIN')")
public class AssessSubtestResource {
	@Autowired
	private AssessTestService testService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public AssessSubtestDTO get(@RequestParam("id") String id, @RequestParam("guid") String guid,
	                            @RequestParam("name") String name) {
		return testService.findSubtestByIdNameOrGuid(id, name, guid);
	}

	@PutMapping
	public ResponseEntity<BaseDTO> create(@RequestBody AssessSubtestDTO createCommand) {
		AssessSubtest subtest = testService.createSubtest(createCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(subtest.getId(), subtest.getVersion()),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BaseDTO> update(@RequestBody BaseAssessSubtestDTO updateCommand) {
		AssessSubtest subtest = testService.updateSubtest(updateCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(subtest.getId(), subtest.getVersion()),
				HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestBody BaseDTO deleteCommand) {
		testService.deleteSubtest(deleteCommand.getId());
	}

	@PostMapping("list")
	@ResponseStatus(HttpStatus.OK)
	public Page<AssessSubtestDTO> list(@RequestBody PageableSearchRequestDTO searchRequest) {
		return testService.listSubtests(searchRequest);
	}

	@PostMapping("list/{testId}")
	@ResponseStatus(HttpStatus.OK)
	public Page<AssessSubtestDTO> listForTest(@RequestBody PageableSearchRequestDTO searchRequest, @PathVariable String testId) {
		return testService.listSubtests(searchRequest, testId);
	}
}
