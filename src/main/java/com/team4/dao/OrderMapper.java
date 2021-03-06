package com.team4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team4.pojo.Order;

public interface OrderMapper {

	public void addOrder(Order order);

	public void deleteOrder(int orderId);

	public Order getOrderById(int orderId);
	
	public void updateOrder(Order order);

	public List<Order> getAllBidOrders();

	public List<Order> getAllAskOrders();

	public List<Order> getBidOrdersBySymbol(@Param("symbol")String symbol);

	public List<Order> getAskOrdersBySymbol(@Param("symbol")String symbol);
	
	public List<Order> getBidOrdersByTrader(@Param("traderName")String traderName);

	public List<Order> getAskOrdersByTrader(@Param("traderName")String traderName);
	
	public List<Order> getMaxBidOrdersBySymbol(@Param("symbol")String symbol);

	public List<Order> getMinAskOrdersBySymbol(@Param("symbol")String symbol);
	
	public int currentOrderId();
	
	public void closeMatchedOrder(int orderId);

	public int getOrderCount();

}
