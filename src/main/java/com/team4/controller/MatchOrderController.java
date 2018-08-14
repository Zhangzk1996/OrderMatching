package com.team4.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team4.pojo.MatchOrder;
import com.team4.pojo.Order;
import com.team4.service.MatchOrderService;
import com.team4.service.OrderService;

@Controller
@RequestMapping(value = "/match")
public class MatchOrderController {
	
	@Autowired
	private MatchOrderService matchOrderService;
	
	@Autowired
	private OrderService orderService;
	
	private ModelAndView matchSuccessOperation(HttpServletRequest request, Order order, Order o) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("matchMessage", "Match Successfully!");
		addOrder(request, order, o);
		modelAndView.addObject("matchResult", matchOrderService.getMatchOrderById(order.getOrderId() + "_" + o.getOrderId()));
		return modelAndView;
	}
	
	@RequestMapping(value = "/simpleOrder")
	public ModelAndView matchingSimple(HttpServletRequest request, Order order) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Order> orders = null;
		int count;
		if (order.getSide().equals("buy")) {
			orders = orderService.getAskOrdersBySymbol(order.getSymbol());
			count = 0;
			for (Order o : orders) {
				if (o.getPrice() <= order.getPrice()) {
					if (o.getQty() == order.getQty()) {
						modelAndView = matchSuccessOperation(request, order, o);
					} else if(o.getQty() > order.getQty()) {
						if (o.getFOK().equals("fill")) {
							modelAndView = matchSuccessOperation(request, order, o);
							o.setQty(o.getQty() - order.getQty());
						}
					}else {
						if (order.getFOK().equals("fill")) {
							modelAndView = matchSuccessOperation(request, order, o);
							order.setQty(order.getQty() - o.getQty());
						}
					}
				}
			}
		} else {
			orders = orderService.getBidOrdersBySymbol(order.getSymbol());
			for (Order o : orders) {
				if (o.getPrice() >= order.getPrice()) {
					if (o.getQty() == order.getQty()) {
						modelAndView = matchSuccessOperation(request, o, order);
					} else if (o.getQty() > order.getQty()){
						if (o.getFOK().equals("fill")) {
							modelAndView = matchSuccessOperation(request, o, order);
							o.setQty(o.getQty() - order.getQty());
						}
					} else {
						if (order.getFOK().equals("fill")) {
							modelAndView = matchSuccessOperation(request, o, order);
							order.setQty(order.getQty() - o.getQty());
						}
					}
				}
			}
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/marketOrder")
	public ModelAndView matchingMarket(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/limitOrder")
	public ModelAndView matchingLimit(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/stopLossOrder")
	public ModelAndView matchingStopLoss(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/stopOrder")
	public ModelAndView matchingStop(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/stopLimitOrder")
	public ModelAndView matchingStopLimit(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/durationOrders")
	public ModelAndView matchingDuration(HttpServletRequest request, Order order) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/addMatchedOrder")
	public ModelAndView addOrder(HttpServletRequest request, Order bid, Order ask) throws Exception {
		String url = (String) request.getSession().getAttribute("Referer");
		MatchOrder matchOrder = new MatchOrder();
		matchOrder.setMatchID(bid.getOrderId() + "_" + ask.getOrderId());
		matchOrder.setSymbol(bid.getSymbol());
		matchOrder.setBid_trader_name(bid.getTraderName());
		matchOrder.setBid_price(bid.getPrice());
		matchOrder.setBid_size(bid.getQty());
		matchOrder.setAsk_trdaer_name(ask.getTraderName());
		matchOrder.setAsk_price(ask.getPrice());
		matchOrder.setAsk_size(ask.getQty());
		matchOrderService.addMatchOrder(matchOrder);
		return new ModelAndView("redirect:" + url);
	}
	
	/**
	 * 显示所有matched order
	 */
	@RequestMapping(value = "/allMatchedOrder")
	public ModelAndView allSymbols(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		List<MatchOrder> matchedOrderList = matchOrderService.getAllMatchOrders();
		modelAndView.addObject("allMatchedOrder", matchedOrderList);
		modelAndView.addObject("matchedOrderCount", matchOrderService.getMatchOrderCount());
		modelAndView.setViewName("match/allMatchedOrder");
		
		return modelAndView;
	}
	
	/**
	 * 查询指定symbol
	 */
	@RequestMapping(value = "/searchMatchedOder/{matchOrderId}", method=RequestMethod.POST)
	public ModelAndView searchSymbolByName(HttpServletRequest request, @PathVariable("matchOrderId") String matchOrderId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		MatchOrder matchOrder = matchOrderService.getMatchOrderById(matchOrderId);
		modelAndView.addObject("searchResult", matchOrder);
		modelAndView.setViewName("match/searchMatchedOder");
		
		return modelAndView;
	}
	
}
