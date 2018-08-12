package com.team4.pojo;

public class Trader {
	private int traderID;
	private String name;
	private String password;
	private String email;
	public int getTraderID() {
		return traderID;
	}
	public void setTraderID(int traderID) {
		this.traderID = traderID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Trader(int traderID, String name, String password, String email) {
		super();
		this.traderID = traderID;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Trader [traderID=" + traderID + ", name=" + name
				+ ", password=" + password + ", email=" + email + "]";
	}
	
	

}
