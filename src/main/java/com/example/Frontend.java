package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Frontend {
	@Autowired
	private ArgumentResolver argumentResolver;
	
	@Autowired
	private Calculator calculator;
	
	public void run() {
		System.out.println("Enter 2 numbers like 'a b' : ");
		Argument argument = argumentResolver.resolve(System.in);
		int result = calculator.calc(argument.getA(), argument.getB());
		System.out.println("result = "+result);
	}
}
