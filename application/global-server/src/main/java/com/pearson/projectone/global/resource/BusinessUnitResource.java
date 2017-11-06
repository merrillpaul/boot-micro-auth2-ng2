package com.pearson.projectone.global.resource;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.data.entity.global.library.account.BusinessUnit;
import com.pearson.projectone.global.dto.businessunit.AssignTestsCommandDTO;
import com.pearson.projectone.global.dto.businessunit.BusinessAssessmentListDTO;
import com.pearson.projectone.global.dto.businessunit.BusinessUnitCreateCommandDTO;
import com.pearson.projectone.global.dto.businessunit.BusinessUnitDTO;
import com.pearson.projectone.global.dto.businessunit.BusinessUnitFormListDTO;
import com.pearson.projectone.global.dto.businessunit.BusinessUnitUpdateCommandDTO;
import com.pearson.projectone.global.service.BusinessUnitService;
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
@PreAuthorize("hasAuthority('ROLE_BU_ADMIN')")
public class BusinessUnitResource {

	@Autowired
	private BusinessUnitService businessUnitService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public BusinessUnitDTO get(@RequestParam(name = "id", required = false) String id,
	                           @RequestParam(name = "guid", required = false) String guid,
	                           @RequestParam(name = "name", required = false) String name) {
		return businessUnitService.findByIdNameOrGuid(id, name, guid);
	}


	@PutMapping
	public ResponseEntity<BaseDTO> create(@RequestBody BusinessUnitCreateCommandDTO createCommand) {
		BusinessUnit businessUnit = businessUnitService.createBusinessUnit(createCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(businessUnit.getId(), businessUnit.getVersion()),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BaseDTO> update(@RequestBody BusinessUnitUpdateCommandDTO updateCommand) {
		BusinessUnit businessUnit = businessUnitService.updateBusinessUnit(updateCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(businessUnit.getId(), businessUnit.getVersion()),
				HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestBody BaseDTO deleteCommand) {
		businessUnitService.deleteBusinessUnit(deleteCommand.getId());
	}

	@PostMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public Page<BusinessUnitDTO> search(@RequestBody PageableSearchRequestDTO searchRequest) {
		return businessUnitService.list(searchRequest);
	}

	@PutMapping("{id}/assignAssessments")
	@ResponseStatus(HttpStatus.OK)
	public void assignAssessments(@RequestBody AssignTestsCommandDTO assignTestsCommand, @PathVariable String id) {
		// TODO
	}

	@PostMapping("{id}/listAssessments")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public Page<BusinessAssessmentListDTO> listAssessments(@RequestBody PageableSearchRequestDTO searchRequest, @PathVariable String id) {
		return businessUnitService.listAssessments(id, searchRequest);
	}

	@PostMapping("{id}/listForms")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public Page<BusinessUnitFormListDTO> listForms(@RequestBody PageableSearchRequestDTO searchRequest, @PathVariable String id) {
		return businessUnitService.listForms(id, searchRequest);
	}
}
