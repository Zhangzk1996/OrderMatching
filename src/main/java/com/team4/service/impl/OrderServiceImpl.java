package com.team4.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
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

	public List<Order> getAllBidOrders() {
		List<Order> orders = orderMapper.getAllBidOrders();
		return orders;
	}

	public int getOrderCount() {
		int count = 0;
		count = orderMapper.getOrderCount();
		return count;
	}

	public List<Order> getAllAskOrders() {
		List<Order> orders = orderMapper.getAllAskOrders();
		return orders;
	}

	public List<Order> getBidOrdersBySymbol(String symbol) {
		List<Order> orders = orderMapper.getBidOrdersBySymbol(symbol);
		return orders;
	}

	public List<Order> getAskOrdersBySymbol(String symbol) {
		List<Order> orders = orderMapper.getAskOrdersBySymbol(symbol);
		return orders;
	}

	public List<Order> getBidOrdersByTrader(String traderName) {
		List<Order> orders = orderMapper.getBidOrdersByTrader(traderName);
		return orders;
	}

	public List<Order> getAskOrdersByTrader(String traderName) {
		List<Order> orders = orderMapper.getAskOrdersByTrader(traderName);
		return orders;
	}

	public List<Order> getMaxBidOrdersBySymbol(String symbol) {
		List<Order> orders = orderMapper.getMaxBidOrdersBySymbol(symbol);
		return orders;
	}

	public List<Order> getMinAskOrdersBySymbol(String symbol) {
		List<Order> orders = orderMapper.getMinAskOrdersBySymbol(symbol);
		return orders;
	}

	public void closeMatchedOrder(int orderId) {
		orderMapper.closeMatchedOrder(orderId);
	}

	public void updateOrder(Order order) {
		orderMapper.updateOrder(order);
	}

	public int currentOrderId() {
		int id = orderMapper.currentOrderId();
		return id;
	}

	public List<Order> getPageOrders(int current, int rowCount, String traderName) {
		PageHelper.startPage(current,rowCount);
		List<Order> orders = orderMapper.getBidOrdersByTrader(traderName);
		orders.addAll(orderMapper.getAskOrdersByTrader(traderName));
		return orders;
	}

}
