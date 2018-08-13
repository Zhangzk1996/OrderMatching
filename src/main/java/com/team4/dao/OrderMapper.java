package com.team4.dao;

import java.util.List;

import com.team4.pojo.Order;

public interface OrderMapper {

	public void addOrder(Order order);

	public void deleteOrder(int orderId);

	public Order getOrderById(int orderId);

	public List<Order> getAllBidOrders(String side);

	public List<Order> getAllAskOrders(String side);

	public List<Order> getBidOrdersBySymbol(String symbol);

	public List<Order> getAskOrdersBySymbol(String symbol);

	public int getOrderCount();

}
