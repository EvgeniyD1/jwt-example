package com.example.jvtexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/*без @EnableTransactionManagement(proxyTargetClass = true) не работает @PreAuthorize*/
@EnableTransactionManagement(proxyTargetClass = true)
public class JwtExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtExampleApplication.class, args);
	}

}
