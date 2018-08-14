package com.team4.pojo;

public class MatchOrder {
	private String matchID;
	private String symbol;
	private String bid_trader_name;
	private String ask_trader_name;
	private double bid_price;
	private double ask_price;
	private int bid_size;
	private int ask_size;

	public String getMatchID() {
		return matchID;
	}

	public void setMatchID(String matchID) {
		this.matchID = matchID;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getBid_trader_name() {
		return bid_trader_name;
	}

	public void setBid_trader_name(String bid_trader_name) {
		this.bid_trader_name = bid_trader_name;
	}

	public String getAsk_trdaer_name() {
		return ask_trader_name;
	}

	public void setAsk_trdaer_name(String ask_trdaer_name) {
		this.ask_trader_name = ask_trader_name;
	}

	public double getBid_price() {
		return bid_price;
	}

	public void setBid_price(double bid_price) {
		this.bid_price = bid_price;
	}

	public double getAsk_price() {
		return ask_price;
	}

	public void setAsk_price(double ask_price) {
		this.ask_price = ask_price;
	}

	public int getBid_size() {
		return bid_size;
	}

	public void setBid_size(int bid_size) {
		this.bid_size = bid_size;
	}

	public int getAsk_size() {
		return ask_size;
	}

	public void setAsk_size(int ask_size) {
		this.ask_size = ask_size;
	}

	// public MatchOrder(int matchID, String symbol, int bid_trader_id,
	// int ask_trdaer_id, float bid_price, float ask_price,
	// double bid_size, double ask_size) {
	// super();
	// this.matchID = matchID;
	// this.symbol = symbol;
	// this.bid_trader_id = bid_trader_id;
	// this.ask_trdaer_id = ask_trdaer_id;
	// this.bid_price = bid_price;
	// this.ask_price = ask_price;
	// this.bid_size = bid_size;
	// this.ask_size = ask_size;
	// }
	@Override
	public String toString() {
		return "MatchOrder [matchID=" + matchID + ", symbol=" + symbol + ", bid_trader_id=" + bid_trader_name
				+ ", ask_trader_id=" + ask_trader_name + ", bid_price=" + bid_price + ", ask_price=" + ask_price
				+ ", bid_size=" + bid_size + ", ask_size=" + ask_size + "]";
	}

}
