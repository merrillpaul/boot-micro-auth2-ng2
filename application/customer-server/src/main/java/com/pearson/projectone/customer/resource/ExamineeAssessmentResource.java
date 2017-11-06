package com.pearson.projectone.customer.resource;

import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentContainerDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentContainerResponseDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentDTO;
import com.pearson.projectone.customer.service.ExamineeAssessmentContainerService;
import com.pearson.projectone.customer.service.ExamineeAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

/**
 * ExamineeAssessment resource has API to manage Examinee Assessment.
 */
@RestApi
public class ExamineeAssessmentResource {

	@Autowired
	ExamineeAssessmentService examineeAssessmentService;

	@Autowired
	private ExamineeAssessmentContainerService examineeAssessmentContainerService;

	/**
	 * Creates ExamineeAssessment.
	 *
	 * @param examineeAssessmentContainerDTO
	 * @return
	 */
	@PutMapping
	public ResponseEntity<?> createExamineeAssessment(
			@RequestBody ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO) {
		if (ObjectUtils.isEmpty(examineeAssessmentContainerDTO.getExamineeId()) ||
				ObjectUtils.isEmpty(examineeAssessmentContainerDTO.getExamineeAssessmentHolders())) {
			return ResponseEntity.badRequest().build();
		}
		ExamineeAssessmentContainerResponseDTO examineeAssessmentContainerResponseDTO =
				this.examineeAssessmentContainerService.create(examineeAssessmentContainerDTO);
		return ResponseEntity.ok(examineeAssessmentContainerResponseDTO);
	}

//	@PutMapping()
//	public ResponseEntity<?> create(@RequestBody ExamineeAssessmentDTO examineeAssessmentDTO) {
//		ExamineeAssessmentDTO savedExamineeAssessmentDTO = this.examineeAssessmentService.create(examineeAssessmentDTO);
//
//		if (savedExamineeAssessmentDTO.getId() != null) {
//			URI location = ServletUriComponentsBuilder
//					.fromCurrentRequest().path("/{id}")
//					.buildAndExpand(savedExamineeAssessmentDTO.getId()).toUri();
//
//			return ResponseEntity.created(location).build();
//		} else {
//			return ResponseEntity.noContent().build();
//		}
//	}
//

	/**
	 * Gets ExamineeAssessment with the given id.
	 *
	 * @param id Non null id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getExamineeAssessmentById(@PathVariable("id") String id) {
		if (ObjectUtils.isEmpty(id)) {
			return ResponseEntity.badRequest().build();
		}

		Optional<ExamineeAssessmentDTO> examineeAssessmentDTOOptional = this.examineeAssessmentService.getExamineeAsssessmentById(id);
		if (!examineeAssessmentDTOOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(examineeAssessmentDTOOptional.get());
	}

	/**
	 * Delete ExamineeAssessment with the given id
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		if (ObjectUtils.isEmpty(id) || !this.examineeAssessmentService.exists(id)) {
			return ResponseEntity.badRequest().build();
		}

		this.examineeAssessmentService.delete(id);
		return ResponseEntity.ok().build();
	}
//
//
//	@GetMapping("/list")
//	public ResponseEntity<?> list() {
//		List<ExamineeAssessmentDTO> examineeAssessmentDTOList = this.examineeAssessmentService.list();
//		return examineeAssessmentDTOList.isEmpty() ?
//				ResponseEntity.noContent().build() :
//				ResponseEntity.ok(examineeAssessmentDTOList);
//	}
//
//	@GetMapping("/list/{examineeId}")
//	public ResponseEntity<?> listByExamineeId(@PathVariable("examineeId") String examineeId) {
//		List<ExamineeAssessmentDTO> examineeAssessmentDTOList = this.examineeAssessmentService.listByExamineeId(examineeId);
//		return examineeAssessmentDTOList.isEmpty() ?
//				ResponseEntity.noContent().build() :
//				ResponseEntity.ok(examineeAssessmentDTOList);
//	}
//
//

//
//	@PostMapping
//	public ResponseEntity<?> update(@RequestBody ExamineeAssessmentDTO examineeAssessmentDTO) {
//		if (ObjectUtils.isEmpty(examineeAssessmentDTO)) {
//			return ResponseEntity.badRequest().build();
//		}
//
//		if (!this.examineeAssessmentService.exists(examineeAssessmentDTO.getId())) {
//			return ResponseEntity.badRequest().body("Entity does not exist.");
//		}
//
//		this.examineeAssessmentService.update(examineeAssessmentDTO);
//		return ResponseEntity.ok().build();
//	}
//
//	/**
//	 * Searches for ExamineeAssessments.
//	 * @param text The text can be assessment form name or modality.
//	 * @return
//	 */
//	@GetMapping("/search/{text}")
//	public ResponseEntity<?> search(@PathVariable("text") String text) {
//		if (ObjectUtils.isEmpty(text)) {
//			return ResponseEntity.badRequest().build();
//		}
//		List<ExamineeAssessmentDTO> examineeAssessmentDTOList = this.examineeAssessmentService.search(text);
//		return examineeAssessmentDTOList.isEmpty() ?
//				ResponseEntity.noContent().build() :
//				ResponseEntity.ok(examineeAssessmentDTOList);
//	}

}
