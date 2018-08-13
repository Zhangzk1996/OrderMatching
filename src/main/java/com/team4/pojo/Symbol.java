package com.team4.pojo;

public class Symbol {
	private String symbol;
	private String company;
	private double last_sale;
	private String change_net;
	private long share_volume;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public double getLast_sale() {
		return last_sale;
	}
	public void setLast_sale(double last_sale) {
		this.last_sale = last_sale;
	}
	public String getChange_net() {
		return change_net;
	}
	public void setChange_net(String change_net) {
		this.change_net = change_net;
	}
	public long getShare_volume() {
		return share_volume;
	}
	public void setShare_volume(long share_volume) {
		this.share_volume = share_volume;
	}
//	public Symbol(String symbol, String company, double last_sale,
//			float change_net, int share_volume) {
//		super();
//		this.symbol = symbol;
//		this.company = company;
//		this.last_sale = last_sale;
//		this.change_net = change_net;
//		this.share_volume = share_volume;
//	}
	
	
}
