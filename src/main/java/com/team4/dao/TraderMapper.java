package com.team4.dao;

import java.util.List;

import com.team4.pojo.Trader;

public interface TraderMapper {
	
	public void addTrader(Trader trader);
	
	public void deleteTrader(int traderId);
	
	public Trader getTraderInfo(int traderId);
	
	public List<Trader> getAllTraders();
	
	public int getTraderCount();
}