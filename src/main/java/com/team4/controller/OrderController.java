package com.team4.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team4.pojo.Order;
import com.team4.pojo.Trader;
import com.team4.service.OrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/addOrder")
	public ModelAndView addOrder(HttpServletRequest request, @ModelAttribute("order") Order order) throws Exception {
		String url = request.getHeader("Referer");
		Trader trader = (Trader) request.getSession().getAttribute("cur_trader");
		String symbol = (String) request.getSession().getAttribute("cur_symbol");
		order.setTraderId(trader.getName());
		order.setSymbol(symbol);
		order.setStatus("alive");
		orderService.addOrder(order);
		return new ModelAndView("redirect:" + url);
	}
	
	@RequestMapping(value = "/deleteOrder")
	public ModelAndView deleteOrder(HttpServletRequest request) throws Exception {
		String url=request.getHeader("Referer");
		Order cur_order = (Order) request.getSession().getAttribute("cur_order");
		cur_order.setStatus("deleted");
		orderService.deleteOrder(cur_order.getOrderId());
		request.getSession().setAttribute("cur_order", cur_order);
		return new ModelAndView("redirect:"+url);
	}
	
	@RequestMapping(value = "/symbolOrders/{symbol}")
	public ModelAndView searchOrders(HttpServletRequest request, @PathVariable("symbol")String symbol) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Order> symbolOrders = orderService.getBidOrdersBySymbol(symbol);
		symbolOrders.addAll(orderService.getAskOrdersBySymbol(symbol));
		modelAndView.addObject("symbolOrders", symbolOrders);
		modelAndView.setViewName("/order/symbolOrders");
		return modelAndView;
	}
	
	@RequestMapping(value = "/allOrders")
	public ModelAndView allOrders(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Order> allOrders = orderService.getAllBidOrders();
		allOrders.addAll(orderService.getAllAskOrders());
		modelAndView.addObject("allOrders", allOrders);
		modelAndView.setViewName("/order/allOrders");
		return modelAndView;
	}
	
}
