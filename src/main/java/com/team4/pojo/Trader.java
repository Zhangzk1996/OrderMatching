package com.team4.pojo;

public class Trader {
	private int traderID;
	private String traderName;
	private String password;
	private String email;
	public int getTraderID() {
		return traderID;
	}
	public void setTraderID(int traderID) {
		this.traderID = traderID;
	}
	public String getTraderName() {
		return traderName;
	}
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Trader(int traderID, String traderName, String password, String email) {
		super();
		this.traderID = traderID;
		this.traderName = traderName;
		this.password = password;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Trader [traderID=" + traderID + ", traderName=" + traderName
				+ ", password=" + password + ", email=" + email + "]";
	}
	
	

}
