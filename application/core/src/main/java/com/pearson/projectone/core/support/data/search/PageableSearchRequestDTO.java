package com.pearson.projectone.core.support.data.search;

import com.pearson.projectone.core.support.data.search.page.OrderDTO;
import com.pearson.projectone.core.support.data.search.page.PageRequestDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class PageableSearchRequestDTO extends SearchRequestDTO {
	private PageRequestDTO pageRequest = null;

	public PageableSearchRequestDTO() {
		this.pageRequest = new PageRequestDTO();
	}

	/**
	 * This constructor takes Pageable  and SearchCriteriaDTO list as parameters to construct
	 * PageableSearchRequestDTO object.
	 * @param pageable
	 * @param searchCriteriaDTOList
	 */
	public PageableSearchRequestDTO(Pageable pageable, List<SearchCriteriaDTO> searchCriteriaDTOList) {
		this.pageRequest = new PageRequestDTO(pageable);
		this.setCriteria(searchCriteriaDTOList);
	}

	/**
	 * This constructor takes Pageable object to construct PageableSearchRequestDTO object.
	 * @param pageable
	 */
	public PageableSearchRequestDTO(Pageable pageable) {
		this.pageRequest = new PageRequestDTO(pageable);
	}

	public PageRequestDTO getPageRequest() {
		return pageRequest;
	}

	public void setPageRequest(PageRequestDTO pageRequest) {
		this.pageRequest = pageRequest;
	}




	public Pageable toPageable() {
		if (pageRequest.getOrders().isEmpty()) {
			return new PageRequest(pageRequest.getPage(), pageRequest.getSize());
		}

		List<Sort.Order> orders = new ArrayList<>(0);
		for (OrderDTO orderDTO : pageRequest.getOrders()) {
			Sort.Order order = null;
			if (ObjectUtils.isEmpty(orderDTO.getDirection())) {
				order = new Sort.Order(orderDTO.getProperty());
			} else {
				order = new Sort.Order(orderDTO.getDirection(), orderDTO.getProperty());
			}
			orders.add(order);
		}
		Sort sort = new Sort(orders);
		return new PageRequest(pageRequest.getPage(), pageRequest.getSize(), sort);
	}
}
