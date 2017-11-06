package com.pearson.projectone.customer.dto.examinee;

import java.io.Serializable;

/**
 * This class holds Generic grouped Id holder
 */

public class GroupedIdHolderDTO implements Serializable {

	public GroupedIdHolderDTO() {
		//default constructor
	}

	public GroupedIdHolderDTO(String groupId, String[] ids) {
		this.groupId = groupId;
		this.ids = ids;
	}

	private String groupId;
	private String[] ids;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
}
