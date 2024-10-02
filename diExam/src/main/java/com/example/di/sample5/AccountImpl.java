package com.example.di.sample5;

public class AccountImpl implements Account {
	private String name;
	private String account;
	private int balance;
	
	
	//2. ������ ��� 
	public AccountImpl(String name, String account, int balance) {
		super();
		this.name = name;
		this.account = account;
		this.balance = balance;
	}
	
	
	//1. ���� ��� 
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
		//1. ���� ��� 
		//String message = "���¹�ȣ : "+account+" �̸� : "+name+" �ܾ� : " +balance;
		//System.out.println(message);
		
		//2. ������ ���
		System.out.println(String.format("���¹�ȣ : %s, �̸� : %s, �ܾ� : %d", account, name, balance));
	}
	
}
