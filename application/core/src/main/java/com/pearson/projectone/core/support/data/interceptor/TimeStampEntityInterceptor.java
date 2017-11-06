package com.pearson.projectone.core.support.data.interceptor;

import com.pearson.projectone.core.support.data.AbstractEntity;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@Order(Integer.MIN_VALUE)
public class TimeStampEntityInterceptor extends DefaultEntityInterceptor {
	@Override
	public void beforeInsert(AbstractEntity abstractEntity) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		abstractEntity.setLastCreated(now);
		abstractEntity.setLastUpdated(now);
	}

	@Override
	public void beforeUpdate(AbstractEntity abstractEntity) {
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		abstractEntity.setLastUpdated(now);
	}
}
