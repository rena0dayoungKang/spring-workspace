package com.example.di.sample6;

public class EmployeeImpl implements Employee {
	private String id;
	private String name;
	private Department department;
	
	public EmployeeImpl(String id, String name, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
	}


	@Override
	public void info() {
		System.out.println(String.format("사번:%s, 사원명:%s, 부서명:%s, 부서위치:%s", id, name, department.getName(), department.getLoc()));
	}

}
