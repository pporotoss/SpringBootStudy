package com.example;

import lombok.Data;

@Data	// final이 아닌 필드의 getter, setter를 만들어준다.  final 필드를 매개변수로 갖는 생성자를 생성해준다. hashCode(), toString(), equals() 메서드를 자동으로 생성.
public class Argument {
	private final int a;
	private final int b;
}
