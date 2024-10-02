package com.example.di.sample4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiExamApplication {

	public static void main(String[] args) {
		// sample3���� �� �����ϰ��ϱ� ���� �ܺο��� ����������
		// beans4.xml���
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans4.xml");
		MessageBean bean = context.getBean("messageBean", MessageBean.class);
		// messageBean Ŭ������ name, greeting, outputer�� �������ؼ� bean ��ü�� ���� �ܺο��� ���Խ��Ѽ� �ʱ�ȭ�Ѵ�
		bean.sayHello();

	}
}
