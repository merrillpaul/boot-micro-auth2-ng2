package com.pearson.projectone.global.config

import com.pearson.projectone.authcommons.config.AbstractResourceServerConfig
import com.pearson.projectone.core.support.config.DefaultWebSecurityConfigurerAdapter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
class GlobalResourceServerConfig extends AbstractResourceServerConfig {
}


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class DefaultGlobalSecurityConfig extends DefaultWebSecurityConfigurerAdapter {}
