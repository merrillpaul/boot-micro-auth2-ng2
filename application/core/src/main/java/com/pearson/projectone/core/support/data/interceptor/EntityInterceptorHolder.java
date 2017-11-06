package com.pearson.projectone.core.support.data.interceptor;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Component
public class EntityInterceptorHolder<T extends EntityInterceptor> implements ApplicationContextAware {

	private List<T> interceptors = new ArrayList<>(0);

	@Override
	@SuppressWarnings("unchecked")
	public void setApplicationContext(final ApplicationContext applicationContext) {

		Set<Map<String, Object>> set = new TreeSet<>(new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer o1Order = (Integer) o1.get("order");
				Integer o2Order = (Integer) o2.get("order");
				return o1Order.compareTo(o2Order);
			}
		});
		for (String beanName : applicationContext.getBeanNamesForType(EntityInterceptor.class)) {
			T bean = (T) applicationContext.getBean(beanName);
			int order = Integer.MIN_VALUE;
			Order orderAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), Order.class);
			if (orderAnnotation != null) {
				order = orderAnnotation.value();
			}
			Map<String, Object> map = new HashMap<>(0);
			map.put("bean", bean);
			map.put("order", order);
			set.add(map);
		}

		for (Map<String, Object> eachBean : set) {
			interceptors.add((T) eachBean.get("bean"));
		}

	}

	public List<T> getInterceptors() {
		return interceptors;
	}
}
