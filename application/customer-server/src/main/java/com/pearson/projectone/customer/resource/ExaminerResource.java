package com.pearson.projectone.customer.resource;


import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.customer.dto.examinee.ExaminerDTO;
import com.pearson.projectone.customer.service.ExaminerService;
import com.pearson.projectone.data.entity.customer.examiner.Examiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestApi
public class ExaminerResource {

	@Autowired
	private ExaminerService examinerService;

	/**
	 * Create Examiner endpoint
	 *
	 * @param examinerDTO
	 * @return
	 */
	@PutMapping
	public ResponseEntity<?> create(@RequestBody final ExaminerDTO examinerDTO) {
		if (examinerDTO.getId() == null) {
			return ResponseEntity.ok(this.examinerService.createOrUpdate(examinerDTO));
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Update Examiner endpoint
	 *
	 * @param examinerDTO
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> update(@RequestBody final ExaminerDTO examinerDTO) {
		if (this.examinerService.isExist(examinerDTO.getId())) {
			return ResponseEntity.ok(this.examinerService.createOrUpdate(examinerDTO));
		} else {
			return ResponseEntity.badRequest().build();
		}
	}


	/**
	 * List examiner endpoint
	 *
	 * @param pageableSearchRequestDTO
	 * @return
	 */
	@PostMapping("list")
	public ResponseEntity<?> list(@RequestBody final PageableSearchRequestDTO pageableSearchRequestDTO) {
		Page<Examiner> examinerPage = this.examinerService.listExaminers(pageableSearchRequestDTO);
		if (examinerPage.getTotalElements() > 0) {
			return ResponseEntity.ok(examinerPage);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * Get Examiner endpoint
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	public ResponseEntity getExaminer(@PathVariable("id") final String id) {
		if (this.examinerService.isExist(id)) {
			return ResponseEntity.ok(this.examinerService.find(id));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Delete Examiner
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") final String id) {
		if (this.examinerService.isExist(id)) {
			this.examinerService.delete(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
