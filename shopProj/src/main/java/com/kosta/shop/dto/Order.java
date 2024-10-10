package com.kosta.shop.dto;

public class Order {
	
	private Integer num;
	private String userid;
	private String gCode;
	private String gName;
	private Integer gPrice;
	private String gSize;
	private String gColor;
	private Integer gAmount;
	private String gImage;
	private Integer orderinfo_num;
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getgCode() {
		return gCode;
	}
	public void setgCode(String gCode) {
		this.gCode = gCode;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public Integer getgPrice() {
		return gPrice;
	}
	public void setgPrice(Integer gPrice) {
		this.gPrice = gPrice;
	}
	public String getgSize() {
		return gSize;
	}
	public void setgSize(String gSize) {
		this.gSize = gSize;
	}
	public String getgColor() {
		return gColor;
	}
	public void setgColor(String gColor) {
		this.gColor = gColor;
	}
	public Integer getgAmount() {
		return gAmount;
	}
	public void setgAmount(Integer gAmount) {
		this.gAmount = gAmount;
	}
	public String getgImage() {
		return gImage;
	}
	public void setgImage(String gImage) {
		this.gImage = gImage;
	}
	public Integer getOrderinfo_num() {
		return orderinfo_num;
	}
	public void setOrderinfo_num(Integer orderinfo_num) {
		this.orderinfo_num = orderinfo_num;
	}
	public Order(Integer num, String userid, String gCode, String gName, Integer gPrice, String gSize,
			String gColor, Integer gAmount, String gImage, Integer orderinfo_num) {
		super();
		this.num = num;
		this.userid = userid;
		this.gCode = gCode;
		this.gName = gName;
		this.gPrice = gPrice;
		this.gSize = gSize;
		this.gColor = gColor;
		this.gAmount = gAmount;
		this.gImage = gImage;
		this.orderinfo_num = orderinfo_num;
	}
	public Order() {
		super();
	}
	
	@Override
	public String toString() {
		return "Gorder [num=" + num + ", userid=" + userid + ", gCode=" + gCode + ", gName=" + gName + ", gPrice="
				+ gPrice + ", gSize=" + gSize + ", gColor=" + gColor + ", gAmount=" + gAmount + ", gImage=" + gImage
				+ ", orderinfo_num=" + orderinfo_num + "]";
	}
	
	

}
