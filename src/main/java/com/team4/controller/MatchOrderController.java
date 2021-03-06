package com.team4.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	private ModelAndView matchSuccessOperation(HttpServletRequest request, Order order, int count,
			List<Order> matchOrders) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("matchMessage", "Match Successfully!");
		List<MatchOrder> matchResult = new ArrayList<MatchOrder>();
		for (Order o : matchOrders) {
			order.setQty(count);
			if (order.getSide().equals("buy")) {
				addMatchedOrder(request, order, o);
				matchResult.add(matchOrderService.getMatchOrderById(order.getOrderId() + "_" + o.getOrderId()));
			} else {
				addMatchedOrder(request, o, order);
				matchResult.add(matchOrderService.getMatchOrderById(o.getOrderId() + "_" + order.getOrderId()));
			}
			count = count - o.getQty();
		}
		modelAndView.addObject("matchResult", matchResult);
		return modelAndView;
	}

	@RequestMapping(value = "/limitOrder")
	public ModelAndView limitSimple(HttpServletRequest request, Order order) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Order> orders = new ArrayList<Order>();
		int count = order.getQty();
		if (order.getSide().equals("buy")) {
			orders = orderService.getAskOrdersBySymbol(order.getSymbol());
			count = 0;
			for (Order o : orders) {
				if (o.getPrice() <= order.getPrice()) {
					if (o.getQty() == order.getQty()) {
						orders.add(o);
						o.setQty(0);
						order.setQty(0);
					} else if (o.getQty() > order.getQty()) {
						if (o.getFok().equals("fill")) {
							orders.add(o);
							o.setQty(o.getQty() - order.getQty());
							order.setQty(0);
						}
					} else {
						if (order.getFok().equals("fill")) {
							orders.add(o);
							order.setQty(order.getQty() - o.getQty());
							o.setQty(0);
						}
					}
				}
				if (order.getQty() == 0) {
					break;
				}
			}
		} else {
			orders = orderService.getBidOrdersBySymbol(order.getSymbol());
			for (Order o : orders) {
				if (o.getPrice() >= order.getPrice()) {
					if (o.getQty() == order.getQty()) {
						orders.add(o);
						o.setQty(0);
						order.setQty(0);
					} else if (o.getQty() > order.getQty()) {
						if (o.getFok().equals("fill")) {
							orders.add(o);
							o.setQty(o.getQty() - order.getQty());
							order.setQty(0);
						}
					} else {
						if (order.getFok().equals("fill")) {
							orders.add(o);
							order.setQty(order.getQty() - o.getQty());
							o.setQty(0);
						}
					}
				}
				if (order.getQty() == 0) {
					break;
				}
			}
		}
		modelAndView = matchSuccessOperation(request, order, count, orders);
		modelAndView.setViewName("redirect:/main/mainPage");
		return modelAndView;
	}

	@RequestMapping(value = "/marketOrder")
	public ModelAndView matchingMarket(HttpServletRequest request, Order order) throws Exception {
		System.out.println("=====================================marketOrder1============================");
		ModelAndView modelAndView = new ModelAndView();
		int count = order.getQty();
		List<Order> matchOrders = new ArrayList<Order>();
		List<Order> existOrders = null;
		if (order.getSide().equals("buy")) {
			existOrders = orderService.getAskOrdersBySymbol(order.getSymbol());
		} else {
			existOrders = orderService.getBidOrdersBySymbol(order.getSymbol());
		}
		for (Order o : existOrders) {
			if (o.getQty() <= order.getQty()) {
				matchOrders.add(o);
				order.setQty(order.getQty() - o.getQty());
				o.setQty(0);
			} else {
				if (o.getFok().equals("fill")) {
					matchOrders.add(o);
					o.setQty(o.getQty() - order.getQty());
					order.setQty(0);
				}
			}
			if (order.getQty() == 0) {
				break;
			}
		}
		modelAndView = matchSuccessOperation(request, order, count, matchOrders);
		System.out.println("=====================================marketOrder2============================");
		modelAndView.setViewName("/main/mainPage");
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
	public ModelAndView addMatchedOrder(HttpServletRequest request, Order bid, Order ask) throws Exception {
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
	@RequestMapping(value = "/searchMatchedOder/{matchOrderId}", method = RequestMethod.POST)
	public ModelAndView searchSymbolByName(HttpServletRequest request,
			@PathVariable("matchOrderId") String matchOrderId) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		MatchOrder matchOrder = matchOrderService.getMatchOrderById(matchOrderId);
		modelAndView.addObject("searchResult", matchOrder);
		modelAndView.setViewName("match/searchMatchedOder");

		return modelAndView;
	}

}
