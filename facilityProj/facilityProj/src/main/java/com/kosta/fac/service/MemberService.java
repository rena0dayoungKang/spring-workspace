package com.kosta.fac.service;

import com.kosta.fac.dto.Employee;

public interface MemberService {
	void signUp(Employee employee) throws Exception;
	Employee login(String emp_no, String pw) throws Exception;
}
