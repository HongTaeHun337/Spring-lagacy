package com.test.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//스프링 설정 방식 > 스프링 빈
//1. XML 방식
//2. 어노테이션
//3. 자바 방식

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	//스프링 빈 선언
	//- <bean>
	//- @Component
//	@Bean
//	public Random aaa() {
//		
//		return new Random();
//	}

	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(getApiInfo()) //API 기본 정보(메타 데이터)
					.select() //Docket 객체가 빌더 시작
					.apis(RequestHandlerSelectors.basePackage("com.test.rest"))
					//.paths(PathSelectors.ant("/address/**"))
					.paths(PathSelectors.any()) //모든 경로를 대상
					.build();
	}

	//문서의 제목, 버전, 설명 등..
	private ApiInfo getApiInfo() {
		
		return new ApiInfoBuilder()
					.title("Address REST API")
					.version("0.0.1")
					.description("Address 데이터에 대한 REST API 명세서입니다.")
					.build();
	}
	
}
























