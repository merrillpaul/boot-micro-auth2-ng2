package com.pearson.projectone.core.support.listeners

import com.pearson.projectone.core.support.populator.BasePopulator
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.core.env.Environment
import spock.lang.Specification

class DevDataPopulatorListenerSpec extends Specification {

	@Order
	class Populator1 implements BasePopulator {
		@Override
		void populate() {
			callStack << 'populator1'
		}
	}

	@Order(Integer.MIN_VALUE)
	class Populator2 implements BasePopulator {
		@Override
		void populate() {
			callStack << 'populator2'
		}
	}

	@Order(4)
	class Populator3 implements BasePopulator {
		@Override
		void populate() {
			callStack << 'populator3'
		}
	}

	@Order(3)
	class Populator4 implements BasePopulator {
		@Override
		void populate() {
			callStack << 'populator4'
		}
	}

	def environment
	def profile = "dev"
	def appContext
	def callStack = []
	def populatorMap = [
			populator1: new Populator1(),
			populator2: new Populator2(),
			populator3: new Populator3(),
			populator4: new Populator4()
	]

	def setup() {
		callStack = []
		environment = Stub(Environment) {
			getActiveProfiles() >> {
				[profile]
			}
		}
		appContext = Stub(ApplicationContext) {
			getBeanDefinitionNames() >> []

			getBeanNamesForType(_) >> { arguments ->
				assert arguments[0] == BasePopulator
				[
						'populator1',
						'populator2',
						'populator3',
						'populator4'
				]
			}

			getBean(_) >> { arguments ->
				def beanName = arguments[0]
				populatorMap[beanName]
			}
		}
	}

	def "should run populators in order"() {
		given:
		def listener = new DevDataPopulatorListener(applicationContext: appContext, environment: environment)

		when:
		listener.onApplicationEvent(null)

		then:
		callStack == [
				'populator2',
				'populator4',
				'populator3',
				'populator1'
		]
	}

	def "should NOT run populators if active profile is not dev"() {
		given:
		profile = '-not-dev-'
		def listener = new DevDataPopulatorListener(environment: environment)

		when:
		listener.onApplicationEvent(null)

		then:
		callStack == []
	}
}
