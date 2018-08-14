package com.team4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team4.pojo.Order;

public interface OrderMapper {

	public void addOrder(Order order);

	public void deleteOrder(int orderId, @Param("status")String status);

	public Order getOrderById(int orderId);

	public List<Order> getAllBidOrders(@Param("side")String side);

	public List<Order> getAllAskOrders(@Param("side")String side);

	public List<Order> getBidOrdersBySymbol(@Param("symbol")String symbol, String side);

	public List<Order> getAskOrdersBySymbol(@Param("symbol")String symbol, String side);
	
	public List<Order> getBidOrdersByTrader(@Param("traderName")String traderName);

	public List<Order> getAskOrdersByTrader(@Param("traderName")String traderName);
	
	public List<Order> getMaxBidOrdersBySymbol(@Param("symbol")String symbol);

	public List<Order> getMinAskOrdersBySymbol(@Param("symbol")String symbol);

	public int getOrderCount();

}
