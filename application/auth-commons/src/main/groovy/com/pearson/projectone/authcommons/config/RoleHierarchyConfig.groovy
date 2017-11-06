package com.pearson.projectone.authcommons.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl

@Configuration
class RoleHierarchyConfig {
	@Bean
	def RoleHierarchy roleHierarchy() {

		def roleHierarchy = new RoleHierarchyImpl()
		roleHierarchy.setHierarchy("""
			ROLE_INTERNAL_RESOURCE > ROLE_SYSTEM_ADMIN
			ROLE_SYSTEM_ADMIN > ROLE_BU_ADMIN
			ROLE_SYSTEM_ADMIN > ROLE_CLIENT
			ROLE_SYSTEM_ADMIN > ROLE_FINANCE
			ROLE_SYSTEM_ADMIN > ROLE_CONTENT_UPLOAD
			ROLE_SYSTEM_ADMIN > ROLE_CUSTOMER_SERVICE
			ROLE_BU_ADMIN > ROLE_USER
			ROLE_CUSTOMER_SERVICE > ROLE_USER
			ROLE_CONTENT_UPLOAD > ROLE_USER
			ROLE_CLIENT > ROLE_CLINICIAN
			ROLE_CLINICIAN > ROLE_SYNC
			ROLE_FINANCE > ROLE_USER
			ROLE_SYNC > ROLE_USER
			ROLE_USER > ROLE_NO_ROLES
		""")
		roleHierarchy

	}
}
