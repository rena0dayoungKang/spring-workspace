package com.example.di.sample2;

public class DiExamApplication {

	public static void main(String[] args) {
		//sample1보다 약간 느슨해짐 
		//MessageBean bean = new MessageBeanKr();
		MessageBean bean = new MessageBeanEn();
		/*위 방식으로 하면 BeanKr, BeanEn 을 바꿔가면서 사용해야한다*/
		bean.sayHello("Spring");
	}
}

