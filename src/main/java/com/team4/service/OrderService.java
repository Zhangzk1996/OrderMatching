package com.team4.service;

import java.util.List;

import com.team4.pojo.Order;

public interface OrderService {

	public void addOrder(Order order);

	public void deleteOrder(int orderId);

	public Order getOrderById(int orderId);
	
	public void closeMatchedOrder(int orderId);
	
	public void updateOrder(Order order);

	public List<Order> getAllBidOrders();

	public List<Order> getAllAskOrders();

	public List<Order> getBidOrdersBySymbol(String symbol);

	public List<Order> getAskOrdersBySymbol(String symbol);

	public List<Order> getBidOrdersByTrader(String traderName);

	public List<Order> getAskOrdersByTrader(String traderName);
	
	public List<Order> getMaxBidOrdersBySymbol(String symbol);

	public List<Order> getMinAskOrdersBySymbol(String symbol);
	
	public int currentOrderId();
	
	public List<Order> getPageOrders(int current, int rowCount, String traderName);

	public int getOrderCount();

}
