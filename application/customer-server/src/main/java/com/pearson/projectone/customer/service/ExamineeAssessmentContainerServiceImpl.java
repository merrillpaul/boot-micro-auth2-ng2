package com.pearson.projectone.customer.service;

import com.pearson.projectone.core.support.data.search.PageableSearchRequestDTO;
import com.pearson.projectone.core.support.data.search.querydsl.QueryDslPredicateBuilder;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentContainerDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentContainerDetailedDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentContainerResponseDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeAssessmentHolderDTO;
import com.pearson.projectone.customer.dto.examinee.ExamineeDTO;
import com.pearson.projectone.customer.dto.examinee.GroupedIdHolderDTO;
import com.pearson.projectone.data.dao.customer.qg.ExamineeAssessmentContainerDao;
import com.pearson.projectone.data.entity.customer.examinee.assessment.ExamineeAssessmentContainer;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class ExamineeAssessmentContainerServiceImpl implements ExamineeAssessmentContainerService {

	@Autowired
	private ExamineeAssessmentContainerDao examineeAssessmentContainerDao;

	@Autowired
	private ExamineeAssessmentService examineeAssessmentService;

	@Autowired
	private ExamineeService examineeService;

	/**
	 * Fetches Pageable containers by filtering the search criteria.
	 *
	 * @param pageableSearchRequestDTO
	 * @return
	 */
	public Page<ExamineeAssessmentContainerDTO> getContainers(final PageableSearchRequestDTO pageableSearchRequestDTO) {
		Predicate predicate = new QueryDslPredicateBuilder().with(pageableSearchRequestDTO.getCriteria())
				.build(ExamineeAssessmentContainer.class);
		return this.examineeAssessmentContainerDao.findAll(predicate, pageableSearchRequestDTO.toPageable())
				.map(eac -> this.convertEntityToDTO(eac));
	}

	/**
	 * Fetches Pageable conatainer by filtering serach criteria.
	 *
	 * @param pageableSearchRequestDTO
	 * @return
	 */
	public Page<ExamineeAssessmentContainerDetailedDTO> findMostRecentContainers(
			final PageableSearchRequestDTO pageableSearchRequestDTO) {
		Predicate predicate = new QueryDslPredicateBuilder().with(pageableSearchRequestDTO.getCriteria())
				.build(ExamineeAssessmentContainer.class);
		return this.examineeAssessmentContainerDao.findAll(predicate, pageableSearchRequestDTO.toPageable()).map(eac ->
				this.convertEntityToDTO(eac, this.examineeService.findExamineeById(eac.getExamineeId()).get()));
	}


	/**
	 * Converts ExamineeAssessmentContainer to ExamineeAssessmentContainerDTO.
	 *
	 * @param examineeAssessmentContainer
	 * @return ExamineeAssessmentContainerDTO object.
	 */
	private ExamineeAssessmentContainerDTO convertEntityToDTO(
			final ExamineeAssessmentContainer examineeAssessmentContainer) {
		return new ExamineeAssessmentContainerDTO(
				examineeAssessmentContainer.getId(),
				examineeAssessmentContainer.getExamineeId(),
				populateExamineeAsssessmentFromIds(examineeAssessmentContainer.getExamineeAssessmentsMap())
		);

	}

	/**
	 * converts ExamineeAssessmentContainer to ExamineeAssessmentContainerDetailedDTO
	 *
	 * @param examineeAssessmentContainer
	 * @param examineeDTO
	 * @return
	 */
	public ExamineeAssessmentContainerDetailedDTO convertEntityToDTO(
			final ExamineeAssessmentContainer examineeAssessmentContainer, final ExamineeDTO examineeDTO) {

		return new ExamineeAssessmentContainerDetailedDTO(
				examineeAssessmentContainer.getId(),
				examineeDTO,
				populateExamineeAsssessmentFromIds(examineeAssessmentContainer.getExamineeAssessmentsMap())
		);

	}

	private ExamineeAssessmentHolderDTO[] populateExamineeAsssessmentFromIds(
			Map<String, List<String>> examineeAssessmentsMap) {
		if (CollectionUtils.isEmpty(examineeAssessmentsMap)) {
			return null;
		}

		List<ExamineeAssessmentHolderDTO> result = new ArrayList<>();
		examineeAssessmentsMap.keySet().forEach(key -> {
			List<ExamineeAssessmentDTO> examineeAssessments = new ArrayList<>();

			if (!CollectionUtils.isEmpty(examineeAssessmentsMap.get(key))) {
				examineeAssessmentsMap.get(key).forEach(id -> {
					examineeAssessments.add(this.examineeAssessmentService.getExamineeAsssessmentById(id).get());
				});
				result.add(new ExamineeAssessmentHolderDTO(key, key,
						examineeAssessments.toArray(new ExamineeAssessmentDTO[examineeAssessments.size()])));
			}
		});

		return result.toArray(new ExamineeAssessmentHolderDTO[result.size()]);
	}

	/**
	 * Creates ExamineeAssessmentContainer.
	 *
	 * @param examineeAssessmentContainerDTO
	 * @return ExamineeAssessmentContainerResponseDTO
	 */
	public ExamineeAssessmentContainerResponseDTO create(
			final ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO) {

		ExamineeAssessmentContainerResponseDTO examineeAssessmentContainerResponseDTO = this.
				saveExamineeAssessments(examineeAssessmentContainerDTO);

		ExamineeAssessmentContainer examineeAssessmentContainer = this.examineeAssessmentContainerDao.save(
				new ExamineeAssessmentContainer(
						examineeAssessmentContainerDTO.getExamineeId(),
						prepareExamineeAssessmentIds(examineeAssessmentContainerResponseDTO.getExamineeAssessmentIds())
				)
		);
		examineeAssessmentContainerResponseDTO.setId(examineeAssessmentContainer.getId());
		return examineeAssessmentContainerResponseDTO;

	}

	public ExamineeAssessmentContainerResponseDTO saveExamineeAssessments(ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO) {
		final String examineeId = examineeAssessmentContainerDTO.getExamineeId();
		final Map<String, List<String>> examineeAssessmentIds = new HashMap<>();
		ExamineeAssessmentHolderDTO[] examineeAssessmentHolderDTOs = examineeAssessmentContainerDTO
				.getExamineeAssessmentHolders();

		if (!ObjectUtils.isEmpty(examineeAssessmentHolderDTOs)) {
			for (ExamineeAssessmentHolderDTO examineeAssessmentHolderDTO : examineeAssessmentHolderDTOs) {
				ExamineeAssessmentDTO[] examineeAssessmentDTOs = examineeAssessmentHolderDTO.getExamineeAssessments();
				final String groupId = examineeAssessmentHolderDTO.getGroupId();
				if (!ObjectUtils.isEmpty(examineeAssessmentDTOs)) {
					for (ExamineeAssessmentDTO examineeAssessmentDTO : examineeAssessmentDTOs) {
						/* Set groupId as formId */
						examineeAssessmentDTO.setFormId(examineeAssessmentHolderDTO.getGroupId());
						ExamineeAssessmentDTO savedExamineeAssessment = this.examineeAssessmentService.save(examineeAssessmentDTO, examineeId);
						addExamineeIdToMap(examineeAssessmentIds, groupId, savedExamineeAssessment);
					}
				}
			}
		}
		return new ExamineeAssessmentContainerResponseDTO(null, convertToGroupIdArray(examineeAssessmentIds));
	}

	/**
	 * This method converts examineeAssessmentIds to GroupedIdHolderDTO array.
	 *
	 * @param examineeAssessmentIds
	 * @return
	 */
	private GroupedIdHolderDTO[] convertToGroupIdArray(Map<String, List<String>> examineeAssessmentIds) {
		List<GroupedIdHolderDTO> groupedIdHolderDTOs = new ArrayList<>();
		if (!CollectionUtils.isEmpty(examineeAssessmentIds)) {
			for (String key : examineeAssessmentIds.keySet()) {
				if (!CollectionUtils.isEmpty(examineeAssessmentIds.get(key))) {
					groupedIdHolderDTOs.add(new GroupedIdHolderDTO(key, examineeAssessmentIds.get(key)
							.toArray(new String[examineeAssessmentIds.get(key).size()])));

				}

			}
		}
		return groupedIdHolderDTOs.toArray(new GroupedIdHolderDTO[groupedIdHolderDTOs.size()]);
	}

	/**
	 * Adds ExamineeAssessment id to the list mapped to formId.
	 *
	 * @param examineeAssessmentIds
	 * @param examineeAssessmentDTO
	 */
	private void addExamineeIdToMap(final Map<String, List<String>> examineeAssessmentIds, final String groupId,
	                                final ExamineeAssessmentDTO examineeAssessmentDTO) {
		if (ObjectUtils.isEmpty(examineeAssessmentIds.get(groupId))) {
			examineeAssessmentIds.put(groupId, new ArrayList<String>());
		}
		examineeAssessmentIds.get(groupId).add(examineeAssessmentDTO.getId());
	}

	/**
	 * Converts GroupedIdHolderDTO[] to Mapped List
	 *
	 * @param ids
	 * @return
	 */
	private Map<String, List<String>> prepareExamineeAssessmentIds(GroupedIdHolderDTO[] ids) {
		Map<String, List<String>> mappedExamineIds = new HashMap<>();
		if (!ObjectUtils.isEmpty(ids)) {
			for (GroupedIdHolderDTO groupedIdHolderDTO : ids) {
				mappedExamineIds.put(groupedIdHolderDTO.getGroupId(), Arrays.asList(groupedIdHolderDTO.getIds()));
			}
		}
		return mappedExamineIds;
	}

	/**
	 * Checks existence of entity with the given id.
	 *
	 * @param id
	 * @return
	 */
	public boolean exists(String id) {
		return this.examineeAssessmentContainerDao.exists(id);
	}

	/**
	 * Checks existence of ExamineeAssessment in ExamineeAssessmentContainer entity.
	 *
	 * @param id
	 * @param assessmentId
	 * @return
	 */
	public boolean exists(String id, String assessmentId) {
		if (!this.exists(id)) {
			return false;
		}

		ExamineeAssessmentContainer examineeAssessmentContainer = this.examineeAssessmentContainerDao.findOne(id);
		Map<String, List<String>> examineeAssessmentsMap = examineeAssessmentContainer.getExamineeAssessmentsMap();
		if (CollectionUtils.isEmpty(examineeAssessmentsMap)) {
			for (String key : examineeAssessmentsMap.keySet()) {
				if (examineeAssessmentsMap.get(key).contains(assessmentId)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Finds ExamineeAssessmentContainer entity with the given id.
	 *
	 * @param id
	 * @return
	 */
	public ExamineeAssessmentContainerDTO find(final String id) {
		return this.convertEntityToDTO(this.examineeAssessmentContainerDao.findOne(id));
	}


	/**
	 * Delete ExamineeAssessmentContainer entity with the give id
	 *
	 * @param id non null id
	 */
	public void delete(String id) {
		ExamineeAssessmentContainer examineeAssessmentContainer = this.examineeAssessmentContainerDao.findOne(id);
		Map<String, List<String>> examineeAssessmentsMap = examineeAssessmentContainer.getExamineeAssessmentsMap();
		if (!CollectionUtils.isEmpty(examineeAssessmentsMap)) {
			examineeAssessmentsMap.keySet().forEach(key -> {
				if (!CollectionUtils.isEmpty(examineeAssessmentsMap.get(key))) {
					examineeAssessmentsMap.get(key).forEach(e -> {
						this.examineeAssessmentService.delete(e);
					});
				}
			});
		}
		this.examineeAssessmentContainerDao.delete(id);
	}

	/**
	 * Delete ExamineeAssessment in ExamineeAssessmentContainer entity with the give id
	 *
	 * @param id non null id
	 */
	public void delete(String id, String assessmentId) {

		ExamineeAssessmentContainer examineeAssessmentContainer = this.examineeAssessmentContainerDao.findOne(id);
		Map<String, List<String>> examineeAssessmentsMap = examineeAssessmentContainer.getExamineeAssessmentsMap();
		if (CollectionUtils.isEmpty(examineeAssessmentsMap)) {
			for (String key : examineeAssessmentsMap.keySet()) {
				if (CollectionUtils.isEmpty(examineeAssessmentsMap.get(key)) &&
						examineeAssessmentsMap.get(key).contains(assessmentId)) {
					examineeAssessmentsMap.get(key).remove(assessmentId);
					this.examineeAssessmentService.delete(assessmentId);
					this.examineeAssessmentContainerDao.save(examineeAssessmentContainer);
					break;
				}
			}
		}
	}

	/**
	 * Checks whether ExamineeAssessment container is valid or not.
	 *
	 * @param examineeAssessmentContainerDTO
	 * @return
	 */
	public boolean isValidExamineeAssessmentContainer(final ExamineeAssessmentContainerDTO
			                                                  examineeAssessmentContainerDTO) {
		final String examineeId = examineeAssessmentContainerDTO.getExamineeId();
		final String id = examineeAssessmentContainerDTO.getId();
		return (ObjectUtils.isEmpty(examineeId) || !examineeService.exists(examineeId) || ObjectUtils.isEmpty(id) ||
				this.exists(id)) ? false : true;
	}


	/**
	 * Updates ExamineeAssessmentContainer.
	 *
	 * @param examineeAssessmentContainerDTO
	 * @return ExamineeAssessmentContainerResponseDTO
	 */
	public ExamineeAssessmentContainerResponseDTO update(ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO) {

		String examineeId = examineeAssessmentContainerDTO.getExamineeId();
		ExamineeAssessmentContainer examineeAssessmentContainer = this.examineeAssessmentContainerDao
				.findOne(examineeAssessmentContainerDTO.getId());

		List<String> newGroupList = new ArrayList<>();
		/**Add a new group **/

		addNewGroup(examineeAssessmentContainerDTO, examineeId, examineeAssessmentContainer, newGroupList);

		/** Delete removed group  **/
		deleteRemovedGroup(examineeAssessmentContainerDTO, examineeAssessmentContainer);

		/** Delete one or more removed assessments from a group **/
		deleteRemovedExamineeAssessmentsInExistingGroup(examineeAssessmentContainerDTO,
				examineeAssessmentContainer, newGroupList);


		/** Add one or more newly added (id == null) ExamineeAssessments to group **/
		addExamineeAssessmentsToExistingGroup(examineeAssessmentContainerDTO, examineeId, examineeAssessmentContainer,
				newGroupList);

		/* Update individual ExamineeAssessments */
		ExamineeAssessmentContainer savedExamineeAssessmentContainer = updateExamineeAssessmentsInExistingGroup(
				examineeAssessmentContainerDTO, examineeId, examineeAssessmentContainer);

		//Clean up
		newGroupList.clear();

		return new ExamineeAssessmentContainerResponseDTO(savedExamineeAssessmentContainer.getId(),
				convertToGroupIdArray(savedExamineeAssessmentContainer.getExamineeAssessmentsMap()));
	}

	/**
	 * Update Existing assessments in existing Group
	 *
	 * @param examineeAssessmentContainerDTO
	 * @param examineeId
	 * @param examineeAssessmentContainer
	 * @return
	 */
	private ExamineeAssessmentContainer updateExamineeAssessmentsInExistingGroup(
			ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO, String examineeId,
			ExamineeAssessmentContainer examineeAssessmentContainer) {

		Arrays.stream(examineeAssessmentContainerDTO.getExamineeAssessmentHolders()).forEach(eah -> {
			Arrays.stream(eah.getExamineeAssessments()).forEach(ea -> {
				if (!ObjectUtils.isEmpty(ea.getId())) {
					this.examineeAssessmentService.update(ea, examineeId);
				}
			});
		});

		return this.examineeAssessmentContainerDao
				.save(examineeAssessmentContainer);
	}

	/**
	 * Add ExammineeAssessments to existing group if required.
	 *
	 * @param examineeAssessmentContainerDTO
	 * @param examineeId
	 * @param examineeAssessmentContainer
	 * @param newGroupList
	 */
	private void addExamineeAssessmentsToExistingGroup(ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO,
	                                                   String examineeId,
	                                                   ExamineeAssessmentContainer examineeAssessmentContainer,
	                                                   List<String> newGroupList) {
		Map<String, List<String>> newIdsMap = new HashMap<>();

		Arrays.stream(examineeAssessmentContainerDTO.getExamineeAssessmentHolders()).forEach(eah -> {
			// Do this only for existing groups.
			if (!newGroupList.contains(eah.getGroupId())) {
				Arrays.stream(eah.getExamineeAssessments()).forEach(ea -> {
					// id is null for a new entity
					if (ObjectUtils.isEmpty(ea.getId())) {
						ea.setFormId(eah.getGroupId());
						ExamineeAssessmentDTO savedExamineeAssessment = this.examineeAssessmentService.save(ea,
								examineeId);
						if (CollectionUtils.isEmpty(newIdsMap.get(eah.getGroupId()))) {
							newIdsMap.put(eah.getGroupId(), new ArrayList<>());
						}
						newIdsMap.get(eah.getGroupId()).add(savedExamineeAssessment.getId());
					}
				});
			}
		});

		examineeAssessmentContainer.getExamineeAssessmentsMap().keySet().forEach(key -> {
			if (!CollectionUtils.isEmpty(newIdsMap.get(key))) {
				examineeAssessmentContainer.getExamineeAssessmentsMap().get(key).addAll(newIdsMap.get(key));
			}
		});
	}

	/**
	 * Delete reomoved examineeAssessments in existing group.
	 *
	 * @param examineeAssessmentContainerDTO
	 * @param examineeAssessmentContainer
	 * @param newGroupList
	 */
	private void deleteRemovedExamineeAssessmentsInExistingGroup(
			ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO,
			ExamineeAssessmentContainer examineeAssessmentContainer, List<String> newGroupList) {
		List<String> entityIds = new ArrayList<>();
		examineeAssessmentContainer.getExamineeAssessmentsMap().keySet().forEach(key -> {
			if (!newGroupList.contains(key)) {
				entityIds.addAll(examineeAssessmentContainer.getExamineeAssessmentsMap().get(key));
			}
		});

		List<String> dtoIds = new ArrayList<>();
		Arrays.stream(examineeAssessmentContainerDTO.getExamineeAssessmentHolders()).forEach(eah -> {
			Arrays.stream(eah.getExamineeAssessments()).forEach(ea -> {
				if (!ObjectUtils.isEmpty(ea.getId())) {
					dtoIds.add(ea.getId());
				}
			});
		});

		entityIds.removeAll(dtoIds);
		if (!CollectionUtils.isEmpty(entityIds)) {
			//Delete all ExamineeAssessments whose ids found in entityIds list
			entityIds.forEach(id -> {
				this.examineeAssessmentService.delete(id);
			});

			// removed deleted entityIds from container list.
			examineeAssessmentContainer.getExamineeAssessmentsMap().keySet().forEach(key -> {
				examineeAssessmentContainer.getExamineeAssessmentsMap().get(key).removeAll(entityIds);
			});
		}
	}

	/**
	 * Delete removed Group
	 *
	 * @param examineeAssessmentContainerDTO
	 * @param examineeAssessmentContainer
	 */
	private void deleteRemovedGroup(ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO,
	                                ExamineeAssessmentContainer examineeAssessmentContainer) {
		List<String> notMatchedIds = new ArrayList<>();
		for (String key : examineeAssessmentContainer.getExamineeAssessmentsMap().keySet()) {
			boolean match = false;

			for (ExamineeAssessmentHolderDTO examineeAssessmentHolderDTO :
					examineeAssessmentContainerDTO.getExamineeAssessmentHolders()) {
				if (examineeAssessmentHolderDTO.getGroupId().equalsIgnoreCase(key)) {
					match = true;
					break;
				}
			}
			if (!match) {
				notMatchedIds.add(key);

			}
		}
		notMatchedIds.forEach(key -> deleteGroup(examineeAssessmentContainer, key));
	}

	/**
	 * Add new group.
	 *
	 * @param examineeAssessmentContainerDTO
	 * @param examineeId
	 * @param examineeAssessmentContainer
	 * @param newGroupList
	 */
	private void addNewGroup(ExamineeAssessmentContainerDTO examineeAssessmentContainerDTO, String examineeId,
	                         ExamineeAssessmentContainer examineeAssessmentContainer,
	                         List<String> newGroupList) {
		Arrays.stream(examineeAssessmentContainerDTO.getExamineeAssessmentHolders()).forEach(eah -> {

			if (CollectionUtils.isEmpty(examineeAssessmentContainer.getExamineeAssessmentsMap().get(eah.getGroupId()))) {
				newGroupList.add(eah.getGroupId());
				examineeAssessmentContainer.getExamineeAssessmentsMap().put(eah.getGroupId(), new ArrayList<String>());
				Arrays.stream(eah.getExamineeAssessments()).forEach(ea -> {
					ea.setFormId(eah.getGroupId());
					String id = this.examineeAssessmentService.save(ea, examineeId).getId();
					examineeAssessmentContainer.getExamineeAssessmentsMap().get(eah.getGroupId()).add(id);
				});

			}
		});
	}

	/**
	 * Deletes ExamineeAssessment group from the container.
	 *
	 * @param examineeAssessmentContainer
	 * @param key                         group key
	 */
	private void deleteGroup(ExamineeAssessmentContainer examineeAssessmentContainer, String key) {
		List<String> assessmentIds = examineeAssessmentContainer.getExamineeAssessmentsMap().get(key);
		assessmentIds.forEach(id -> {
			this.examineeAssessmentService.delete(id);
		});

		examineeAssessmentContainer.getExamineeAssessmentsMap().remove(key);
	}


}
