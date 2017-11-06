package com.pearson.projectone.core.support.data.hibernate;


import com.pearson.projectone.core.support.data.AbstractEntity;
import com.pearson.projectone.core.utils.AutoWireHelper;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDHexGenerator;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.List;

/**
 * This for the benefit of dev environment when we need to supply the id
 * to refer them across multiple servers to populate referenced data.
 */
public class EnvironmentAwareUUIDHexGenerator extends UUIDHexGenerator {
	@Override
	public Serializable generate(SessionImplementor session, Object obj) {

		Environment env = AutoWireHelper.getBean(Environment.class);
		List<String> activeProfiles = CollectionUtils.arrayToList(env.getActiveProfiles());
		if ((activeProfiles.contains("dev") || activeProfiles.contains("integration")) &&
				AbstractEntity.class.isAssignableFrom(obj.getClass())) {
			
			AbstractEntity entity = (AbstractEntity) obj;
			if (!ObjectUtils.isEmpty(entity.getId())) {
				return entity.getId();
			}
		}
		return super.generate(session, obj);
	}
}

