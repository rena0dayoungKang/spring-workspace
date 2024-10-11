package com.kosta.fac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.fac.dao.EmployeeDao;
import com.kosta.fac.dto.Employee;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public void signUp(Employee employee) throws Exception {
		employeeDao.insertEmployee(employee);
	}

	@Override
	public Employee login(String empNo, String empPw) throws Exception {
		Employee emp = employeeDao.selectEmployee(empNo);
		if (emp == null) throw new Exception("���̵� ����");
		if (!emp.getEmpPw().equals(empPw)) throw new Exception("��й�ȣ ����");
		return emp;
	}

	

}
