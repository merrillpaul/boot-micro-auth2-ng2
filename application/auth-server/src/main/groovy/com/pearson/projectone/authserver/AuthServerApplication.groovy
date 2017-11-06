package com.pearson.projectone.authserver

import com.pearson.projectone.authcommons.dto.AppUserDetails
import com.pearson.projectone.authcommons.provider.client.JpaClientDetailsService
import com.pearson.projectone.authcommons.provider.token.converters.AuthServerAccessTokenConverter
import com.pearson.projectone.authcommons.provider.token.store.DefaultTokenStore
import com.pearson.projectone.authcommons.service.token.Oauth2TokenStoreService
import com.pearson.projectone.authcommons.service.token.impl.DefaultTokenStoreServiceImpl
import com.pearson.projectone.authcommons.service.user.CurrentUserService
import com.pearson.projectone.authcommons.service.user.impl.AuthServerCurrentUserService
import com.pearson.projectone.authcommons.service.user.impl.AuthServerUserDetailsServiceImpl
import com.pearson.projectone.authcommons.tokenpublisher.TokenPublisher
import com.pearson.projectone.authcommons.tokenpublisher.impl.NoopTokenPublisher
import com.pearson.projectone.core.support.application.BaseServerApplication
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.config.JmsListenerContainerFactory
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MessageType
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.token.AccessTokenConverter
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import javax.jms.ConnectionFactory
import java.security.Principal

/**
 * Wires up all beans needed for auth server
 */
@SpringBootApplication
@EnableJms
@EnableResourceServer
@RestController
public class AuthServerApplication extends BaseServerApplication {

	public static void main(String[] args) {
		def ctx = SpringApplication.run(AuthServerApplication.class, args)
		ctx.start()
	}

	/**
	 * This serves as the 'me' endpoint which provides user information after
	 * authentication
	 */
	@RequestMapping("/me")
	@ResponseBody
	def AppUserDetails user(Principal user) {
		AppUserDetails.clone(user.principal)
	}


	@Bean
	public JmsListenerContainerFactory<?> tokenJmsFactory(ConnectionFactory connectionFactory,
														  DefaultJmsListenerContainerFactoryConfigurer configurer) {
		def factory = new DefaultJmsListenerContainerFactory()
		// This provides all boot's default to this factory, including the message converter
		configurer.configure(factory, connectionFactory)
		// You could still override some of Boot's default if necessary.
		factory
	}

	// Serialize message content to json using TextMessage
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		def converter = new MappingJackson2MessageConverter()
		converter.setTargetType(MessageType.TEXT)
		converter.setTypeIdPropertyName("_type")
		converter
	}

	@Bean
	public TokenStore tokenStore() {
		new DefaultTokenStore()
	}

	@Bean
	public Oauth2TokenStoreService tokenStoreService() {
		new DefaultTokenStoreServiceImpl()
	}

	@Bean
	public AccessTokenConverter accessTokenConverter() {
		new AuthServerAccessTokenConverter()
	}

	@Bean
	public ClientDetailsService clientDetailsService() {
		new JpaClientDetailsService()
	}

	@Bean
	public TokenPublisher tokenPublisher() {
		// TODO finalize and decide the mode of token distribution
		new NoopTokenPublisher()
	}

	@Bean
	public UserDetailsService userDetailsService() {
		new AuthServerUserDetailsServiceImpl()
	}

	@Bean
	public CurrentUserService currentUserService() {
		new AuthServerCurrentUserService()
	}

}
