package com.example;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication	// 다양한 스프링 관련 설정을 자동으로 해주는 어노테이션.
@Import(AppConfig.class)	// 설정파일 임포트.
public class SpringBootDiApplication {

	public static void main(String[] args) {
		try(ConfigurableApplicationContext context = SpringApplication.run(SpringBootDiApplication.class, args); Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter 2 numbers like 'a ' : ");
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			
			Calculator calculator = context.getBean(Calculator.class);
			int result = calculator.calc(a, b);
			System.out.println("result = "+result);
		}
	}
	
}
