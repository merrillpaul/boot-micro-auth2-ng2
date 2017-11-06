package com.pearson.projectone.global.resource;

import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.global.dto.forms.FormDTO;
import com.pearson.projectone.global.dto.forms.FormDetailsDTO;
import com.pearson.projectone.global.dto.forms.FormHeavyDTO;
import com.pearson.projectone.global.dto.forms.FormLiteDTO;
import com.pearson.projectone.global.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestApi
@PreAuthorize("hasAuthority('ROLE_USER')")
public class FormResource {

	@Autowired
	private FormService formService;


	/**
	 * This API end point gets a form by form id.
	 *
	 * @param id
	 * @return Returts ResponseEntity
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> get(@PathVariable("id") final String id) {
		if (formService.isExists(id)) {
			FormDTO formDTO = formService.getForm(id);
			return ResponseEntity.ok(formDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Gets Detailed FormDTO
	 *
	 * @param id
	 * @return Returns ResponseEntity
	 */
	@GetMapping("{id}/detail")
	public ResponseEntity<?> getDetailedForm(@PathVariable("id") final String id) {
		if (formService.isExists(id)) {
			FormDetailsDTO formDetailsDTO = formService.detailedForm(id);
			return ResponseEntity.ok(formDetailsDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	/**
	 * This methods takes PageableSearchRequestDTO request and returns page results.
	 *
	 * @param pageableSearchRequestDTO
	 * @return Returns ResponseEntity
	 */
	@PostMapping("list")
	public ResponseEntity<?> listForms(@RequestBody PageableSearchRequestDTO pageableSearchRequestDTO) {
		Page<FormDTO> formDTOs = this.formService.listFormsPageWise(pageableSearchRequestDTO);
		if (formDTOs.getTotalElements() > 0) {
			return ResponseEntity.ok(formDTOs);
		} else {
			return ResponseEntity.noContent().build();
		}

	}


	/**
	 * This method lists all the forms in the system.
	 *
	 * @return Return ResponseEntity
	 */
	@GetMapping("list")
	public ResponseEntity<?> listAllForms() {
		List<FormDTO> formDTOs = this.formService.listAllForms();
		if (!CollectionUtils.isEmpty(formDTOs)) {
			return ResponseEntity.ok(formDTOs);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * This method lists all the forms in the system.
	 *
	 * @return Return ResponseEntity
	 */
	@GetMapping("list/lite")
	public ResponseEntity<?> listAllFormNamesAndIds() {
		List<FormLiteDTO> formLiteDTOs = this.formService.listAllFormNamesAndIds();
		if (!CollectionUtils.isEmpty(formLiteDTOs)) {
			return ResponseEntity.ok(formLiteDTOs);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * This endpoint deletes Form entity if exists.
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") final String id) {
		if (this.formService.isExists(id)) {
			this.formService.delete(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * This endpoint creates new Form.
	 *
	 * @param formHeavyDTO
	 * @return ResponseEntity with proper response.
	 */
	@PutMapping
	public ResponseEntity<?> create(@RequestBody final FormHeavyDTO formHeavyDTO) {
		if (this.formService.isFormAcronymExist(formHeavyDTO.getAcronym())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

		if (!this.formService.isAssessmentExist(formHeavyDTO.getAssessmentId())) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(this.formService.save(formHeavyDTO));
	}

	/**
	 * This endpoint updates Form.
	 *
	 * @param formHeavyDTO has more fields matching Form entity.
	 * @return ResponseEntity with proper response.
	 */
	@PostMapping
	public ResponseEntity<?> update(@Valid @NotNull @RequestBody final FormHeavyDTO formHeavyDTO) {
		if (this.formService.isExists(formHeavyDTO.getId())) {
			return ResponseEntity.ok(this.formService.update(formHeavyDTO));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
