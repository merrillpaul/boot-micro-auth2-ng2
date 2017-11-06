package com.pearson.projectone.customer

import com.pearson.projectone.authcommons.support.application.BaseResourceServerApplication
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
public class CustomerServerApplication extends BaseResourceServerApplication {

	public static void main(String[] args) {
		def ctx = SpringApplication.run(CustomerServerApplication.class, args)
		ctx.start()
	}

}
