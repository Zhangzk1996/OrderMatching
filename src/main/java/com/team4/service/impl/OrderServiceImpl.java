package com.team4.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team4.dao.OrderMapper;
import com.team4.pojo.Order;
import com.team4.service.OrderService;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderMapper orderMapper;
	
	public void addOrder(Order order) {
		orderMapper.addOrder(order);
	}

	public void deleteOrder(int orderId) {
		orderMapper.deleteOrder(orderId);
	}

	public Order getOrderById(int orderId) {
		Order order = orderMapper.getOrderById(orderId);
		return order;
	}

	public List<Order> getAllOrders() {
		List<Order> orders = orderMapper.getAllOrders();
		return orders;
	}

	public int getOrderCount() {
		int count = 0;
		count = orderMapper.getOrderCount();
		return count;
	}

}
