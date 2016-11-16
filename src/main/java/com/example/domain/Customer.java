package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customers")
@Data
@AllArgsConstructor	// 모든 필드를 인자로 받는 생성자 생성.
@NoArgsConstructor	// 기본 생성자 생성.
public class Customer {
	@Id @GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
}
