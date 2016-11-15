package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication	// 다양한 스프링 관련 설정을 자동으로 해주는 어노테이션. @Configuration + @EnableAutoConfiguration + @ComponentScan
//@Import(AppConfig.class)	// 설정파일 임포트.
public class SpringBootDiApplication {

	public static void main(String[] args) {
		try(ConfigurableApplicationContext context = SpringApplication.run(SpringBootDiApplication.class, args)) {
			Frontend frontend = context.getBean(Frontend.class);
			frontend.run();
		}
	}
	
}
