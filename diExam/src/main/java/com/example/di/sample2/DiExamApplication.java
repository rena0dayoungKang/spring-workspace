package com.example.di.sample2;

public class DiExamApplication {

	public static void main(String[] args) {
		//sample1���� �ణ �������� 
		//MessageBean bean = new MessageBeanKr();
		MessageBean bean = new MessageBeanEn();
		/*�� ������� �ϸ� BeanKr, BeanEn �� �ٲ㰡�鼭 ����ؾ��Ѵ�*/
		bean.sayHello("Spring");
	}
}

