package com.example.di.sample5;

public class AccountImpl implements Account {
	private String name;
	private String account;
	private int balance;
	
	
	//2. 생성자 방식 
	public AccountImpl(String name, String account, int balance) {
		super();
		this.name = name;
		this.account = account;
		this.balance = balance;
	}
	
	
	//1. 셋터 방식 
	public void setName(String name) {
		this.name = name;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}


	@Override
	public void info() {
		//1. 셋터 방식 
		//String message = "계좌번호 : "+account+" 이름 : "+name+" 잔액 : " +balance;
		//System.out.println(message);
		
		//2. 생성자 방식
		System.out.println(String.format("계좌번호 : %s, 이름 : %s, 잔액 : %d", account, name, balance));
	}
	
}
