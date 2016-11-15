package com.example;

import java.io.InputStream;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.example.app.Argument;
import com.example.app.ArgumentResolver;

@Component
public class ScannerArgumentResolver implements ArgumentResolver{

	@Override
	public Argument resolve(InputStream stream) {
		try(Scanner scanner = new Scanner(System.in);) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			return new Argument(a, b);
		}
	}

}
