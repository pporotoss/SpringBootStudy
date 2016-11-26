package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration
public class AppConfig {
	
	@Order(Ordered.HIGHEST_PRECEDENCE)	// 순서를 가장 먼저 적용.
	@Bean
	CharacterEncodingFilter characterEncodingFilter() {	// 인코딩 필터 빈 등록
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}
	
	
	
}
