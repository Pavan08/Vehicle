package com.ford.vehicle.config;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private String version = "1.0.0";

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)
				.securitySchemes(newArrayList(new ApiKey("Authorization-Key", "Authorization", "header")))
				.apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.ford.vehicle"))
				.build();
	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("Ford Vehicle  API").description("Ford Vehicle service api.").version(version)
				.build();
	}
}