package com.team4.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	public ModelAndView addOrder(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Order order) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Trader trader = (Trader) request.getSession().getAttribute("cur_trader");
		// String symbol = (String) request.getSession().getAttribute("cur_symbol");
		order.setTraderName(trader.getTraderName());
		// order.setSymbol(symbol);
		order.setSta("alive");
		
		orderService.addOrder(order);
//		System.out.println("44444444444444" + order);
		modelAndView.setViewName("/mainPage");
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteOrder")
	public ModelAndView deleteOrder(HttpServletRequest request) throws Exception {
		String url=request.getHeader("Referer");
		Order cur_order = (Order) request.getSession().getAttribute("cur_order");
		cur_order.setSta("deleted");
		orderService.deleteOrder(cur_order.getOrderId(), cur_order.getSta());
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
	
	@RequestMapping(value = "/traderOrders/{traderName}")
	public ModelAndView traderOrders(HttpServletRequest request, @PathVariable("traderName")String traderName) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Order> traderOrders = orderService.getBidOrdersByTrader(traderName);
		traderOrders.addAll(orderService.getAskOrdersByTrader(traderName));
		modelAndView.addObject("traderOrders", traderOrders);
		modelAndView.setViewName("/orderAndSymbolList");
		return modelAndView;
	}
	
}
