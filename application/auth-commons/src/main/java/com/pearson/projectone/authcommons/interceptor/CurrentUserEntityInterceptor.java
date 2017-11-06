package com.pearson.projectone.authcommons.interceptor;


import com.pearson.projectone.authcommons.dto.AppUserDetails;
import com.pearson.projectone.authcommons.service.user.CurrentUserService;
import com.pearson.projectone.core.support.data.AbstractEntity;
import com.pearson.projectone.core.support.data.interceptor.DefaultEntityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(0)
@Component
public class CurrentUserEntityInterceptor extends DefaultEntityInterceptor {

	@Autowired
	private CurrentUserService currentUserService;

	protected String getCurrentUserId() {
		String currentUserId = null;
		try {
			AppUserDetails currentUser = currentUserService.getCurrentUser();
			if (currentUser != null) {
				currentUserId = currentUser.getId();
			}
		} catch (RuntimeException e) {
			// ignored
		}
		return currentUserId;
	}

	@Override
	public void beforeInsert(AbstractEntity abstractEntity) {
		String currentUserId = getCurrentUserId();
		if (currentUserId != null) {
			abstractEntity.setCreatedBy(currentUserId);
			abstractEntity.setUpdatedBy(currentUserId);
		}
	}

	@Override
	public void beforeUpdate(AbstractEntity abstractEntity) {
		String currentUserId = getCurrentUserId();
		if (currentUserId != null) {
			abstractEntity.setUpdatedBy(currentUserId);
		}
	}
}
