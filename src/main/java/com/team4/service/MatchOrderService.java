package com.team4.service;

import java.util.List;

import com.team4.pojo.MatchOrder;

public interface MatchOrderService {
	
	public void addMatchOrder(MatchOrder matchOrder);
	
	public MatchOrder getMatchOrderById(int matchOrderId);
	
	public List<MatchOrder> getAllMatchOrders();
	
	public int getMatchOrderCount();
	
}
