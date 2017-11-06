package com.pearson.projectone.global.resource;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.data.entity.global.library.assessment.Assessment;
import com.pearson.projectone.global.dto.assessment.AssessmentDTO;
import com.pearson.projectone.global.dto.assessment.MapAssessTestCommandDTO;
import com.pearson.projectone.global.service.AssessmentService;
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
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@RestApi
@PreAuthorize("hasAuthority('ROLE_SYSTEM_ADMIN')")
public class AssessmentResource {

	@Autowired
	private AssessmentService assessmentService;

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public AssessmentDTO get(@PathVariable String id) {
		return assessmentService.getAssessment(id);
	}

	@PutMapping
	public ResponseEntity<BaseDTO> create(@RequestBody AssessmentDTO createCommand) {
		Assessment assessment = assessmentService.createAssessment(createCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(assessment.getId(), assessment.getVersion()),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BaseDTO> update(@RequestBody AssessmentDTO updateCommand) {
		Assessment assessment = assessmentService.updateAssessment(updateCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(assessment.getId(), assessment.getVersion()),
				HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestBody BaseDTO deleteCommand) {
		assessmentService.deleteAssessment(deleteCommand.getId());
	}


	@PostMapping("list")
	@ResponseStatus(HttpStatus.OK)
	public Page<AssessmentDTO> list(@RequestBody PageableSearchRequestDTO searchRequest) {
		return assessmentService.list(searchRequest);
	}

	@PostMapping("mapAssessTest")
	@ResponseStatus(HttpStatus.OK)
	public void mapAsssessTest(@RequestBody MapAssessTestCommandDTO mapCommand) {
		assessmentService.mapAssessTest(mapCommand);
	}
}
