package com.example.di.sample3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiExamApplication {

	public static void main(String[] args) {
		//sample2보다 더 느슨하게하기 위해 외부에서 의존성주입
		//beans3.xml사용 -> 외부 설정 파일에서 클래스 관계를 주입시키는 것을 말한다. 
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans3.xml");
		MessageBean bean = context.getBean("messageBeanKr", MessageBean.class);
		bean.sayHello("Spring");
		
		MessageBean bean2 = context.getBean("messageBeanEn", MessageBean.class);
		bean2.sayHello("Spring");
	}
}

