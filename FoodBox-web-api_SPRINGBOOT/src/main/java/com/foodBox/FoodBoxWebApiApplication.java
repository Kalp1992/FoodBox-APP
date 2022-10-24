package com.foodBox;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class FoodBoxWebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodBoxWebApiApplication.class, args);
		System.out.println("FoodBox web app works!");
	}
	
	@Bean
    public HttpFirewall allowUrlSemicolonHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true);;
        return firewall;
    }
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FoodBoxWebApiApplication.class);
	}
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.foodbox"))
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"FoodBox Web API",
				"Spring Boot REST API for foodbox.com",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Kalpana Rathod", "https://github.com/Kalp1992/", "kalpanarathod6@gmail.com"),
				"API License",
				"https://github.com/Kalp1992/",
				Collections.emptyList()
				);

}

}
