package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;


@SpringBootApplication	// 다양한 스프링 관련 설정을 자동으로 해주는 어노테이션. @Configuration + @EnableAutoConfiguration + @ComponentScan
public class SpringBootStudyApplication implements CommandLineRunner {	// SpringApplication.run() 에서 수행해야 할 내용을 정의하는 인터페이스를 구현.
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... arg0) throws Exception {	// SpringApplication.run() 에서 수행해야 할 내용을 정의.
		Customer created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
		System.out.println("created = "+created+" is Created");
		
		customerRepository.findAll().forEach(System.out::println);
		
		customerRepository.findAllOrderByName().forEach(System.out::println);
		
		Pageable pageable = new PageRequest(0, 3);
		Page<Customer> page = customerRepository.findAll(pageable);
		System.out.println("한 페이지당 데이터 수 : "+page.getSize());
		System.out.println("현재 페이지 : "+page.getNumber());
		System.out.println("전체 페이지 수 : "+page.getTotalPages());
		System.out.println("전체 데이터 수 : "+page.getTotalElements());
		page.getContent().forEach(System.out::println);
		
		page = customerRepository.findAllOrderByName(pageable);
		System.out.println("한 페이지당 데이터 수 : "+page.getSize());
		System.out.println("현재 페이지 : "+page.getNumber());
		System.out.println("전체 페이지 수 : "+page.getTotalPages());
		System.out.println("전체 데이터 수 : "+page.getTotalElements());
		page.getContent().forEach(System.out::println);
	}
	
	
	public static void main(String[] args) {
		try(ConfigurableApplicationContext context = SpringApplication.run(SpringBootStudyApplication.class, args)) {
			
		}
	}
	
}
