package com.team4.service;

import java.util.List;

import com.team4.pojo.Order;

public interface OrderService {
	
	public void addOrder(Order order);
	
	public void deleteOrder(int orderId);
	
	public Order getOrderById(int orderId);
	
	public List<Order> getAllOrders();
	
	public int getOrderCount();
	
}
