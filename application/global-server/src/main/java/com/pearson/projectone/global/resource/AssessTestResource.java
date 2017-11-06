package com.pearson.projectone.global.resource;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.data.entity.global.library.assessment.AssessTest;
import com.pearson.projectone.global.dto.assesstest.AssessTestResultDTO;
import com.pearson.projectone.global.dto.assesstest.CreateAssessTestCommandDTO;
import com.pearson.projectone.global.dto.assesstest.UpdateAssessTestCommandDTO;
import com.pearson.projectone.global.service.AssessTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;

@Component
@RestApi
@PreAuthorize("hasAuthority('ROLE_SYSTEM_ADMIN')")
public class AssessTestResource {
	@Autowired
	private AssessTestService testService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public AssessTestResultDTO get(@RequestParam(name = "id", required = false) String id,
	                               @RequestParam(name ="guid", required = false) String guid,
	                               @RequestParam(name = "name", required = false) String name) {
		return testService.findTestByIdNameOrGuid(id, name, guid);
	}

	@PutMapping
	public ResponseEntity<BaseDTO> create(@RequestBody CreateAssessTestCommandDTO createCommand) {
		AssessTest test = testService.createTest(createCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(test.getId(), test.getVersion()),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BaseDTO> create(@RequestBody UpdateAssessTestCommandDTO updateCommand) {
		AssessTest test = testService.updateTest(updateCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(test.getId(), test.getVersion()),
				HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestBody BaseDTO deleteCommand) {
		testService.deleteTest(deleteCommand.getId());
	}

	@PostMapping("list")
	@ResponseStatus(HttpStatus.OK)
	public Page<AssessTestResultDTO> list(@RequestBody PageableSearchRequestDTO searchRequest) {
		return testService.listTests(searchRequest);
	}

	@PostMapping("uploadMetadataJson")
	public ResponseEntity uploadMetadataJson(
			@RequestParam("file") MultipartFile file) {
		try {
			testService.saveTestFromJson(StreamUtils.copyToString(file.getInputStream(), Charset.forName("UTF-8")));
			return ResponseEntity.ok().build();
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
