package com.pearson.projectone.global

import com.pearson.projectone.authcommons.support.application.BaseResourceServerApplication
import com.pearson.projectone.core.support.data.mongodb.NoopMongoDBFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
public class GlobalServerApplication extends BaseResourceServerApplication {

	public static void main(String[] args) {
		def ctx = SpringApplication.run(GlobalServerApplication.class, args)
		ctx.start()
	}

	@Bean
	def MongoDbFactory noopMongoDBFactory() {
		new NoopMongoDBFactory()
	}

}
