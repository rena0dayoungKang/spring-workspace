package com.example.di.sample3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DiExamApplication {

	public static void main(String[] args) {
		//sample2���� �� �����ϰ��ϱ� ���� �ܺο��� ����������
		//beans3.xml��� -> �ܺ� ���� ���Ͽ��� Ŭ���� ���踦 ���Խ�Ű�� ���� ���Ѵ�. 
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans3.xml");
		MessageBean bean = context.getBean("messageBeanKr", MessageBean.class);
		bean.sayHello("Spring");
		
		MessageBean bean2 = context.getBean("messageBeanEn", MessageBean.class);
		bean2.sayHello("Spring");
	}
}

