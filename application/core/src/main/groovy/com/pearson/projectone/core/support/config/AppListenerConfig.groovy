package com.pearson.projectone.core.support.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(["com.pearson.projectone.core.support.listeners", "com.pearson.projectone.listeners"])
class AppListenerConfig {
}
