package com.pearson.projectone.core.support.data.search.page;

import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageRequestDTO implements Serializable {

	public PageRequestDTO() {

	}

	public PageRequestDTO(Pageable pageable) {
		this.page = pageable.getPageNumber();
		this.size = pageable.getPageSize();
		if (!ObjectUtils.isEmpty(pageable.getSort())) {
			this.orders = new ArrayList<OrderDTO>();
			pageable.getSort().forEach(sort -> {
				this.orders.add(new OrderDTO(sort.getProperty(), sort.getDirection()));
			});
		}
	}

	public PageRequestDTO(int page, int size, List<OrderDTO> orders) {
		this.page = page;
		this.size = size;
		this.orders = orders;
	}

	public PageRequestDTO(int page, int size) {
		this(page, size, new ArrayList<>(0));
	}

	private int page = 0;

	private int size = 20;

	private List<OrderDTO> orders = new ArrayList<>(0);


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}
}
