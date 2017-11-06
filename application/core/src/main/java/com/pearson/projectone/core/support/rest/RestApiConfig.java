package com.pearson.projectone.core.support.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * Enables all rest endpoints to have `/api` prefix
 */
@Configuration
public class RestApiConfig {

	private static final String API_BASE_PATH = "/api";

	@Bean
	public WebMvcRegistrationsAdapter webMvcRegistrationsHandlerMapping() {
		return new WebMvcRegistrationsAdapter() {
			@Override
			public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {

				return (new RequestMappingHandlerMapping() {
					@Override
					protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
						Class beanType = method.getDeclaringClass();
						if (AnnotationUtils.findAnnotation(beanType, RestApi.class) != null) {
							PatternsRequestCondition baseBattern = new PatternsRequestCondition(API_BASE_PATH);
							RestApi restApiAnnotation = AnnotationUtils.getAnnotation(beanType, RestApi.class);
							if (restApiAnnotation.value().length == 0) {
								// we now become intelligent and grab the path based on the name of the class
								String[] controllerNames = StringUtils.splitByCharacterTypeCamelCase(beanType.getSimpleName());
								StringBuilder controllerName = new StringBuilder(0);
								int i = 0;
								for (String part : controllerNames) {
									i++;
									if (!(i == controllerNames.length &&
											(part.equalsIgnoreCase("resource") || part.equalsIgnoreCase("endpoint") ||
													part.equalsIgnoreCase("controller")))) {
										controllerName.append(part);
									}

								}
								Character ch = Character.toLowerCase(controllerName.charAt(0));
								controllerName.replace(0, 1, ch.toString());
								baseBattern = baseBattern.combine(new PatternsRequestCondition(controllerName.toString()));
							}

							PatternsRequestCondition apiPattern = baseBattern
									.combine(mapping.getPatternsCondition());

							mapping = new RequestMappingInfo(mapping.getName(), apiPattern, mapping.getMethodsCondition(),
									mapping.getParamsCondition(), mapping.getHeadersCondition(), mapping.getConsumesCondition(),
									mapping.getProducesCondition(), mapping.getCustomCondition());
						}
						super.registerHandlerMethod(handler, method, mapping);
					}
				});
			}
		};
	}
}
