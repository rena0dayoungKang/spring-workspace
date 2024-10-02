package com.example.di.sample4;

import java.io.IOException;

public class MessageBeanImpl implements MessageBean {
	private String name;
	private String greeting;
	private Outputter outputter;
	
	//name은 생성자를 통해 초기화
	public MessageBeanImpl(String name) {
		this.name = name;
	} 
	
	//greeting, outputter 셋터를 통해 초기화
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public void setOutputter(Outputter outputter) {
		this.outputter = outputter;
	}
	
	//greeting, name, outputter
	@Override
	public void sayHello() {
		String message = greeting +", " + name + "!";
		System.out.println(message);
		
		try {
			outputter.output(message);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
