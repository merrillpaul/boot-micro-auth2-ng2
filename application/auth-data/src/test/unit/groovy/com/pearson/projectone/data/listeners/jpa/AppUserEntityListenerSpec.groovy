package com.pearson.projectone.data.listeners.jpa

import com.pearson.projectone.core.utils.AutoWireHelper
import com.pearson.projectone.data.entity.security.user.AppUser
import org.junit.Rule
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

import static org.powermock.api.mockito.PowerMockito.mockStatic
import static org.powermock.api.mockito.PowerMockito.when

@PrepareForTest([AutoWireHelper])
class AppUserEntityListenerSpec extends Specification {

	// rule to allow Spock and PowerMock together to get static interception
	@Rule
	PowerMockRule rule = new PowerMockRule()

	private static class SampleEncoder implements PasswordEncoder {
		@Override
		String encode(CharSequence rawPassword) {
			rawPassword + "ENCODED"
		}

		@Override
		boolean matches(CharSequence rawPassword, String encodedPassword) {
			true
		}
	}

	def "should encrypt users password if provided on insert"() {
		given:
		def encoder = new SampleEncoder()
		def user = new AppUser(password: "password1!")
		mockStatic(AutoWireHelper)
		when(AutoWireHelper.getBean(PasswordEncoder)).thenReturn(encoder)

		when:
		user.onBeforeInsert()

		then:
		user.password == 'password1!ENCODED'
		user.passwordHistories.size() == 1
		user.passwordHistories[0].password == user.password
		user.passwordHistories[0].user == user
	}
}
