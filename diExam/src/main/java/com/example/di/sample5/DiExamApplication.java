package com.example.di.sample5;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiExamApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans5.xml");
		Account bean = context.getBean("account", Account.class);
		bean.info();

	}
}


//���¹�ȣ:1001, �̸�:ȫ�浿, �ܾ�:100000
