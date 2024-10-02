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
		System.out.println(String.format("���:%s, �����:%s, �μ���:%s, �μ���ġ:%s", id, name, department.getName(), department.getLoc()));
	}

}
