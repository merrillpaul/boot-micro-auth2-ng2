package com.pearson.projectone.global.dto.businessunit;


import com.pearson.projectone.core.support.data.AbstractDTO;

import java.util.ArrayList;
import java.util.List;

public class AssignTestsCommandDTO extends AbstractDTO {

	private List<String> testIds = new ArrayList<>(0);

	public List<String> getTestIds() {
		return testIds;
	}

	public void setTestIds(List<String> testIds) {
		this.testIds = testIds;
	}
}
