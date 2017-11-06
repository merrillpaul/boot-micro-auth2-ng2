package com.pearson.projectone.authcommons.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler


class DefaultGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

	@Autowired
	RoleHierarchy roleHierarchy

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		def handler = new OAuth2MethodSecurityExpressionHandler()
		handler.roleHierarchy = roleHierarchy
		handler
	}
}
