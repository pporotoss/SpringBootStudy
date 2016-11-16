package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication	// 다양한 스프링 관련 설정을 자동으로 해주는 어노테이션. @Configuration + @EnableAutoConfiguration + @ComponentScan
public class SpringBootStudyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootStudyApplication.class, args);
	}
	
}
