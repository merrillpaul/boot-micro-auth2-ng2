package com.pearson.projectone.core.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * A spring utility to wire beans into components through the application context directly. Such target
 * bean's lifecycle is usually managed by non-spring, for eg hibernate, in case of certain
 * hibernate entity listeners
 */
@Component
public class AutoWireHelper implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	private static final AutoWireHelper INSTANCE = new AutoWireHelper();

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		AutoWireHelper.applicationContext = applicationContext;
	}

	/**
	 * Tries to autowire the specified instance of the class if one of the specified beans which need to be autowired
	 * are null.
	 *
	 * @param classToAutowire        the instance of the class which holds @Autowire annotations
	 * @param beansToAutowireInClass the beans which have the @Autowire annotation in the specified {#classToAutowire}
	 */
	public static void autowire(Object classToAutowire, Object... beansToAutowireInClass) {
		for (Object bean : beansToAutowireInClass) {
			if (ObjectUtils.isEmpty(bean)) {
				applicationContext.getAutowireCapableBeanFactory().autowireBean(classToAutowire);
				return;
			}
		}
	}

	/**
	 * Gets the bean for a specified class
	 *
	 * @param clazz
	 * @return the bean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class clazz) {
		return (T) applicationContext.getBean(clazz);
	}

	/**
	 * Gets the beans for a specified class
	 *
	 * @param clazz
	 * @return the bean
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> getBeansOfType(Class clazz) {
		return applicationContext.getBeansOfType(clazz);
	}

	/**
	 * @return the singleton instance.
	 */
	public static AutoWireHelper getInstance() {
		return INSTANCE;
	}
}
