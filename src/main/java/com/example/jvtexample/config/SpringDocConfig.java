package com.example.jvtexample.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("JWT EXAMPLE")
                        .version("0.0.1")
                        .description("JWT Security with new APIs"));
//                         можно дописать всякую доп инфу
//                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
//                        .contact(new Contact().name("Your Name").email("your-email@example.com"))
    }

}
