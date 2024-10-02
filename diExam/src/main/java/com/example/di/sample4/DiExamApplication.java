package com.example.di.sample4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiExamApplication {

	public static void main(String[] args) {
		// sample3보다 더 느슨하게하기 위해 외부에서 의존성주입
		// beans4.xml사용
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans4.xml");
		MessageBean bean = context.getBean("messageBean", MessageBean.class);
		// messageBean 클래스는 name, greeting, outputer를 가져야해서 bean 객체를 통해 외부에서 주입시켜서 초기화한다
		bean.sayHello();

	}
}
