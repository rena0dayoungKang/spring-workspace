package com.example.di.sample6;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiExamApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans6.xml");
		Employee bean = context.getBean("employee", Employee.class);
		bean.info();

	}
}


//��� : 1001, ����� : ȫ�浿, �μ��� : ȫ����, �μ���ġ : ���� ��õ��
