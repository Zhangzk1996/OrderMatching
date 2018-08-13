package com.team4.pojo;

public class MatchOrder {
	private String matchID;
	private String symbol;
	private int bid_trader_id;
	private int ask_trdaer_id;
	private float bid_price;
	private float ask_price;
	private double bid_size;
	private double ask_size;

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

	public int getBid_trader_id() {
		return bid_trader_id;
	}

	public void setBid_trader_id(int bid_trader_id) {
		this.bid_trader_id = bid_trader_id;
	}

	public int getAsk_trdaer_id() {
		return ask_trdaer_id;
	}

	public void setAsk_trdaer_id(int ask_trdaer_id) {
		this.ask_trdaer_id = ask_trdaer_id;
	}

	public float getBid_price() {
		return bid_price;
	}

	public void setBid_price(float bid_price) {
		this.bid_price = bid_price;
	}

	public float getAsk_price() {
		return ask_price;
	}

	public void setAsk_price(float ask_price) {
		this.ask_price = ask_price;
	}

	public double getBid_size() {
		return bid_size;
	}

	public void setBid_size(double bid_size) {
		this.bid_size = bid_size;
	}

	public double getAsk_size() {
		return ask_size;
	}

	public void setAsk_size(double ask_size) {
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
		return "MatchOrder [matchID=" + matchID + ", symbol=" + symbol + ", bid_trader_id=" + bid_trader_id
				+ ", ask_trdaer_id=" + ask_trdaer_id + ", bid_price=" + bid_price + ", ask_price=" + ask_price
				+ ", bid_size=" + bid_size + ", ask_size=" + ask_size + "]";
	}

}
