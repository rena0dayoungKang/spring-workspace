package com.kosta.exam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	//기존에는 컨트롤러를 항상 서블릿으로 만들어야 했지만 지금은 @Controller 를 사용해서 만들 수 있음
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String hello() {
		return "hello"; //hello.jsp로 향한다 
	}
	
	
}
