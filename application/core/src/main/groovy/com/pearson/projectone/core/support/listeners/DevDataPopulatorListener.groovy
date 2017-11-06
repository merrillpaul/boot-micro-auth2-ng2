package com.pearson.projectone.core.support.listeners

import com.pearson.projectone.core.support.populator.BasePopulator
import org.springframework.beans.BeansException
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryAware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextStartedEvent
import org.springframework.core.annotation.Order
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class DevDataPopulatorListener implements ApplicationListener<ContextStartedEvent>, BeanFactoryAware {

	@Autowired
	Environment environment

	@Autowired
	ApplicationContext applicationContext

	private ConfigurableListableBeanFactory beanFactory

	@Override
	void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (ConfigurableListableBeanFactory) beanFactory
	}

	@Override
	void onApplicationEvent(ContextStartedEvent event) {
		println "@@@@Starting Up Our APP with profile : ${environment.getActiveProfiles().join(',')} "
		if (environment.getActiveProfiles().contains('dev')) {
			println("Let's inspect the beans provided by Spring Boot:")
			def beanNames = applicationContext.getBeanDefinitionNames().sort()
			beanNames.each { println it }

			// run all populators
			runPopulators()
		}
	}

	/**
	 * Runs all populators annotated with @Component and runs them in order
	 */
	def runPopulators() {
		def beans = applicationContext.getBeanNamesForType(BasePopulator).collect {
			int order = Integer.MIN_VALUE
			def bean = applicationContext.getBean(it)
			def orderAnnotation = bean.class.getAnnotation(Order)
			order = orderAnnotation.value() ?: order
			[
					bean : bean,
					order: order
			]
		}.sort({ a, b ->
			a.order <=> b.order
		}).each {
			println ">>>>>>>> Running populator of ${it.bean.class}"
			it.bean.populate()
		}
		println ">>>>>>> All populators have run"
	}
}