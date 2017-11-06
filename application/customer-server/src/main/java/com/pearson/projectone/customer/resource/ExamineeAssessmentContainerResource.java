package com.pearson.projectone.customer.resource;

import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentContainerDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentContainerDetailedDTO;
import com.pearson.projectone.customer.service.ExamineeAssessmentContainerService;
import com.pearson.projectone.customer.service.ExamineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestApi
public class ExamineeAssessmentContainerResource {

	@Autowired
	private ExamineeAssessmentContainerService examineeAssessmentContainerService;

	@Autowired
	private ExamineeService examineeService;


	/**
	 * This endpoint gets Pageable list of ExamineeAssessmentContainerDTO wid the given criteria and page.
	 *
	 * @param pageableSearchRequestDTO Pageabe and searchable PageableSearchRequestDTO.
	 * @return Pageable result if data exists, noContent (204 status) otherwise.
	 */
	@PostMapping("default")
	public ResponseEntity<?> findWithGivenSearch(final @RequestBody PageableSearchRequestDTO pageableSearchRequestDTO) {
		Page<ExamineeAssessmentContainerDTO> examineeAssessmentDTOPage = this.examineeAssessmentContainerService
				.getContainers(pageableSearchRequestDTO);
		if (examineeAssessmentDTOPage.getTotalElements() > 0) {
			return ResponseEntity.ok(examineeAssessmentDTOPage);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping("/recent")
	public ResponseEntity<?> findAssessmentGroups(final @RequestBody PageableSearchRequestDTO pageableSearchRequestDTO) {
		Page<ExamineeAssessmentContainerDetailedDTO> examineeAssessmentContainerDetailedDTOs =
				this.examineeAssessmentContainerService.findMostRecentContainers(pageableSearchRequestDTO);
		if (examineeAssessmentContainerDetailedDTOs.getTotalElements() > 0) {
			return ResponseEntity.ok(examineeAssessmentContainerDetailedDTOs);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * Get ExamineeAssessmentContainer with the given id.
	 *
	 * @param id non null id
	 * @return
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> find(@PathVariable("id") final String id) {
		if (this.examineeAssessmentContainerService.exists(id)) {
			return ResponseEntity.ok(this.examineeAssessmentContainerService.find(id));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping
	public ResponseEntity<?> createContainer(
			@RequestBody final ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO) {
		final String examineeId = examineeAssessmentContainerDTO.getExamineeId();
		if (ObjectUtils.isEmpty(examineeId) || !examineeService.exists(examineeId)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(this.examineeAssessmentContainerService.create(examineeAssessmentContainerDTO));
	}

	/**
	 * Update ExamineeAssessmentContainer update
	 *
	 * @param examineeAssessmentContainerDTO
	 * @return
	 */
	@PostMapping("update")
	public ResponseEntity<?> updateContainer(
			@RequestBody final ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO) {
		if (this.examineeAssessmentContainerService.isValidExamineeAssessmentContainer(examineeAssessmentContainerDTO)) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(this.examineeAssessmentContainerService.update(examineeAssessmentContainerDTO));
	}


	/**
	 * Delete ExamineeAssessmentContainer with given id.
	 *
	 * @param id non null id
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") final String id) {
		if (this.examineeAssessmentContainerService.exists(id)) {
			this.examineeAssessmentContainerService.delete(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Delete ExamineeAssessment with given ExamineeAssessmentContainer id and ExamineeAssessment id.
	 *
	 * @param id non null id
	 * @return
	 */
	@DeleteMapping("{id}/assessment/{assessmentId}")
	public ResponseEntity<?> delete(@PathVariable("id") final String id,
	                                @PathVariable("assessmentId") final String assessmentId) {
		if (this.examineeAssessmentContainerService.exists(id, assessmentId)) {
			this.examineeAssessmentContainerService.delete(id, assessmentId);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}


}
