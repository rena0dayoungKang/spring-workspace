package com.kosta.exam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	//�������� ��Ʈ�ѷ��� �׻� �������� ������ ������ ������ @Controller �� ����ؼ� ���� �� ����
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String hello() {
		return "hello"; //hello.jsp�� ���Ѵ� 
	}
	
	
}
