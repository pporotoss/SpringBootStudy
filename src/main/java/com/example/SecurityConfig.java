package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Import(AuthenticationConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(WebSecurity web) throws Exception {	// 웹 요청에 따른 설정위한 메서드.
		web.ignoring().antMatchers("/webjars/**", "/css/**");	// 해당 주소  접근시 시큐리티 설정 무시.
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	// 권한 인가, 로그인, 로그아웃 을 설정.
		http.authorizeRequests()
				.antMatchers("/loginForm").permitAll()	// loginForm에 모든 사용자가 접속가능 설정. 이외의 경로는 접속 불가.
				.anyRequest().authenticated();
		
		http.formLogin()
				.loginProcessingUrl("/login")	// 로그인 인증페이지 설정.
				.loginPage("/loginForm")	// 로그인하는 페이지 설정.
				.failureUrl("/loginForm?error")	// 로그인 실패시 페이지 설정.
				.defaultSuccessUrl("/customers", true)	// 로그인 성공시 이동할 페이지 설정.
				.usernameParameter("username").passwordParameter("password")	// 사용자 이름과 패스워드 관련 파라미터의 이름 설정.
		.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))	// 로그아웃 처리 경로 설정. AntPathRequestMatcher 클래스 사용 않으면, post로만 접근 가능.
				.logoutSuccessUrl("/loginForm");	// 로그아웃 성공시 이동할 페이지 설정.
	}
	
}
