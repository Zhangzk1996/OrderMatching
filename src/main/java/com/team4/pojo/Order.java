package com.team4.pojo;

public class Order {
	private int orderId;
	private String symbol;
	private String traderName;
	private String side;
	private int Qty;
	private double price;
	private String FOK;
	private String cond;
	private String sta;
	private double otherPrice;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTraderName() {
		return traderName;
	}

	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public int getQty() {
		return Qty;
	}

	public void setQty(int qty) {
		Qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getFOK() {
		return FOK;
	}

	public void setFOK(String fOK) {
		FOK = fOK;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getSta() {
		return sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public double getOtherPrice() {
		return otherPrice;
	}

	public void setOtherPrice(double otherPrice) {
		this.otherPrice = otherPrice;
	}

//	public Order(int orderId, String symbol, String traderName, String side, int qty, double price, String fOK,
//			String condition, String status,double otherPrice) {
//		super();
//		this.orderId = orderId;
//		this.symbol = symbol;
//		this.traderName = traderName;
//		this.side = side;
//		Qty = qty;
//		this.price = price;
//		FOK = fOK;
//		this.condition = condition;
//		this.status = status;
//		this.otherPrice = otherPrice;
//	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", symbol=" + symbol + ", traderId=" + traderName + ", side=" + side
				+ ", Qty=" + Qty + ", price=" + price + ", FOK=" + FOK + ", condition=" + cond + ", status="
				+ sta + ", otherPrice=" + otherPrice + "]";
	}

}
