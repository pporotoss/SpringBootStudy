package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.domain.Customer;
import com.example.service.CustomerService;


@SpringBootApplication	// 다양한 스프링 관련 설정을 자동으로 해주는 어노테이션. @Configuration + @EnableAutoConfiguration + @ComponentScan
//@Import(AppConfig.class)	// 설정파일 임포트.
public class SpringBootStudyApplication implements CommandLineRunner {	// SpringApplication.run() 에서 수행해야 할 내용을 정의하는 인터페이스를 구현.
	
	@Autowired
	private CustomerService customerService;
		
	
	public static void main(String[] args) {
		try(ConfigurableApplicationContext context = SpringApplication.run(SpringBootStudyApplication.class, args)) {
			
		}
	}

	@Override
	public void run(String... arg0) throws Exception {	// SpringApplication.run() 에서 수행해야 할 내용을 정의.
		customerService.save(new Customer(1, "Nobita", "Nobi"));
		customerService.save(new Customer(2, "Takeshi", "Goda"));
		customerService.save(new Customer(3, "Suneo", "Honekawa"));
		
		customerService.findAll().forEach(System.out::println);
	}
	
}
