package com.pearson.projectone.global.resource;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.data.entity.global.library.account.ResourceMetaData;
import com.pearson.projectone.global.dto.metadata.MetadataDTO;
import com.pearson.projectone.global.service.MetadataService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Component
@RestApi
@RequestMapping("{businessUnitId}")
@PreAuthorize("hasAuthority('ROLE_CLIENT')")
public class MetadataResource {

	@Autowired
	private MetadataService metadataService;

	@GetMapping("{key}")
	@ResponseStatus(HttpStatus.OK)
	public MetadataDTO getForKey(@PathVariable String key, @PathVariable String businessUnitId) {
		return metadataService.getForKey(businessUnitId, key);
	}

	@GetMapping("{type}/list")
	@ResponseStatus(HttpStatus.OK)
	public List<MetadataDTO> forType(@PathVariable String type, @PathVariable String businessUnitId) {
		return metadataService.getForType(businessUnitId, type);
	}

	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<MetadataDTO> list(@PathVariable String businessUnitId) {
		return metadataService.getForBusinessUnit(businessUnitId);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('ROLE_BU_ADMIN')")
	public ResponseEntity<BaseDTO> create(@PathVariable String businessUnitId, @RequestBody MetadataDTO createCommand) {
		ResourceMetaData metaData = metadataService.createMetadata(createCommand, businessUnitId);
		return new ResponseEntity<BaseDTO>(new BaseDTO(metaData.getId(), metaData.getVersion()),
				HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_BU_ADMIN')")
	public ResponseEntity<BaseDTO> update(@PathVariable String businessUnitId, @RequestBody MetadataDTO updateCommand) {
		ResourceMetaData metaData = metadataService.updateMetadata(updateCommand, businessUnitId);
		return new ResponseEntity<BaseDTO>(new BaseDTO(metaData.getId(), metaData.getVersion()),
				HttpStatus.OK);
	}

	@DeleteMapping
	@PreAuthorize("hasAuthority('ROLE_BU_ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestParam String id) {
		metadataService.deleteMetadata(id);
	}
}
