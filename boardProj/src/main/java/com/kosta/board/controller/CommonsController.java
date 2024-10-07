package com.kosta.board.controller;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonsController {
	
	@RequestMapping(value="image/{filename}", method=RequestMethod.GET)
	public void imageView(@PathVariable String filename, HttpServletResponse response) {
		//System.out.println(filename); //Ȯ���ڸ� ���� ���� �̸��� ������ 
		//�ǹ������� file�̶�� ���̺��� ���� �����ϸ鼭 file ��ȣ�� board���̺��� foreignŰ�� ���� �ְ� �Ѵ�. 
		//������ �̸��� ������ �÷��� �ߺ����� �ʱ� ������ ������ ����
		try {
			String path = "C:/kdy/uploadImage/";
			FileInputStream fis = new FileInputStream(path+filename);
			FileCopyUtils.copy(fis, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
