package com.example.di.sample6;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiExamApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans6.xml");
		Employee bean = context.getBean("employee", Employee.class);
		bean.info();

	}
}


//사번 : 1001, 사원명 : 홍길동, 부서명 : 홍보부, 부서위치 : 가산 금천구
