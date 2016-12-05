package com.example.service;

import org.springframework.security.core.authority.AuthorityUtils;

import com.example.domain.User;

import lombok.Data;

/* 스프링 시큐리티의 인증 사용자용 인터페이스인 Userdetails를 구현한 스프링시큐리티의 User 클래스를 상속. */
@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User{	
													// 직접 정의한 User 클래스와 스프링 시큐리티의 User 클래스의 이름이 중복되어서 임포트 하지 않고 직접 다씀.

	private final User user;	// 스프링 시큐리티로 인증된 사용자 객체에서 직접 정의한 User 객체를 가져오기 위해 필드 추가.
	
	
	public LoginUserDetails(User user) {	// 생성자.
		// 스프링시큐리티가 제공하는 User 클래스의 생성자를 이용하여 '사용자 이름', '암호', '허가작업' 을 할 수 있는 역할을 설정.
		// AuthorityUtils를 사용하면 편리하게 역할 설정 가능.
		super(user.getUsername(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user = user;
	}
	
}
