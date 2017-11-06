package com.pearson.projectone.core.utils

import org.springframework.beans.MutablePropertyValues
import org.springframework.boot.bind.RelaxedDataBinder

/**
 * Util class for bean property copying
 */
final class DataBinderUtils {
	private DataBinderUtils() {}

	/**
	 * Copies properties into bean instance properties
	 * @param target any object or bean
	 * @param properties any map, properties or groovy map
	 */
	static void populateObjectFromProperties(def target, def properties) {
		new RelaxedDataBinder(target).bind(new MutablePropertyValues(properties))
	}
}
