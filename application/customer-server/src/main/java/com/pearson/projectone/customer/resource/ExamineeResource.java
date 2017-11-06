package com.pearson.projectone.customer.resource;

import com.pearson.projectone.core.support.data.BaseDTO;
import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.rest.RestApi;
import com.pearson.projectone.customer.dto.examinee.ExamineeDTO;
import com.pearson.projectone.customer.service.ExamineeService;
import com.pearson.projectone.data.entity.customer.examinee.Examinee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

/**
 * Examinee Resource provides required REST API around examinee management.
 * Created by ukakapr on 3/23/17.
 */
@RestApi
public class ExamineeResource {


	@Autowired
	private ExamineeService examineeService;

	/**
	 * Request for examinee by id.
	 *
	 * @param id
	 * @return
	 */

	@GetMapping("/{id}")
	public ResponseEntity<?> findExaminee(@PathVariable String id) {
		Optional<ExamineeDTO> examineeOptional = examineeService.findExamineeById(id);

		if (examineeOptional.isPresent()) {
			return ResponseEntity.ok(examineeOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * Create new examinee.
	 *
	 * @param examineeDTO
	 * @return
	 */
	@PutMapping
	public ResponseEntity<?> createExaminee(@RequestBody ExamineeDTO examineeDTO) {
		Examinee savedExaminee = examineeService.create(examineeDTO);

		if (!ObjectUtils.isEmpty(savedExaminee.getId())) {
			return ResponseEntity.ok(new BaseDTO(savedExaminee.getId(), savedExaminee.getVersion()));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}


	/**
	 * Get all Examinees.
	 *
	 * @return
	 */
	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> list() {
		Optional<List<ExamineeDTO>> examineListOptional = examineeService.list();

		if (examineListOptional.isPresent()) {
			return ResponseEntity.ok(examineListOptional.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}


	/**
	 * Get examinees by Pageable object values.The page
	 *
	 * @param pageableSearchRequestDTO
	 * @return
	 */
	@PostMapping("/list")
	public ResponseEntity<?> PageableList(@RequestBody PageableSearchRequestDTO pageableSearchRequestDTO) {
		Page<ExamineeDTO> pageOfExaminees = examineeService.list(pageableSearchRequestDTO);

		if (pageOfExaminees.getTotalElements() > 0) {
			return ResponseEntity.ok(pageOfExaminees);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * Lists examinee by name.
	 * Method Type: GET
	 *
	 * @param examineName
	 * @return
	 */
	@GetMapping("/search/name/{examineName}")
	public ResponseEntity<?> listExamineeByName(@PathVariable("examineName") String examineName) {
		List<Examinee> examineeList = this.examineeService.filterByName(examineName);
		if (!CollectionUtils.isEmpty(examineeList)) {
			return ResponseEntity.ok(examineeList);
		} else {
			return ResponseEntity.noContent().build();
		}


	}

	/**
	 * Delete examinee. It takes examinee id.
	 * Method Type: DELETE
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		if (this.examineeService.exists(id)) {
			this.examineeService.delete(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * Updates examinee. It takes examinee object in JSON format.
	 * Method Type: POST
	 *
	 * @param examineeDTO
	 * @return
	 */
	@PostMapping(value = "/update")
	public ResponseEntity<?> update(@RequestBody ExamineeDTO examineeDTO) {
		if (this.examineeService.exists(examineeDTO.getId())) {
			Examinee updatedExaminee = this.examineeService.update(examineeDTO);
			return ResponseEntity.ok(new BaseDTO(updatedExaminee.getId(), updatedExaminee.getVersion()));

		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
