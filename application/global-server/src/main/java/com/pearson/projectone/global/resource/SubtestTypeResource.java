package com.pearson.projectone.global.resource;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.data.entity.global.library.assessment.AssessSubtestType;
import com.pearson.projectone.global.dto.subtesttype.SubtestTypeDTO;
import com.pearson.projectone.global.service.SubtestTypeService;
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
public class SubtestTypeResource {
	@Autowired
	private SubtestTypeService subtestTypeService;

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public SubtestTypeDTO get(@PathVariable String id) {
		return subtestTypeService.getType(id);
	}

	@GetMapping("/name/{name}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public ResponseEntity byName(@PathVariable String name) {
		SubtestTypeDTO dto = subtestTypeService.getTypeByName(name);
		if (dto != null) {
			return new ResponseEntity(dto, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	@PutMapping
	public ResponseEntity<BaseDTO> create(@RequestBody SubtestTypeDTO createCommand) {
		AssessSubtestType assessSubtestType = subtestTypeService.createType(createCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(assessSubtestType.getId(), assessSubtestType.getVersion()),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BaseDTO> update(@RequestBody SubtestTypeDTO updateCommand) {
		AssessSubtestType assessSubtestType = subtestTypeService.updateType(updateCommand);
		return new ResponseEntity<BaseDTO>(new BaseDTO(assessSubtestType.getId(), assessSubtestType.getVersion()),
				HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestBody BaseDTO deleteCommand) {
		subtestTypeService.deleteType(deleteCommand.getId());
	}

	@GetMapping("list")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_CLIENT')")
	public List<SubtestTypeDTO> list() {
		return subtestTypeService.getAllTypes();
	}

}
