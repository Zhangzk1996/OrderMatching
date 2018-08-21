package com.team4.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.team4.pojo.MatchOrder;
import com.team4.pojo.Order;
import com.team4.pojo.Symbol;
import com.team4.pojo.Trader;
import com.team4.service.MatchOrderService;
import com.team4.service.OrderService;
import com.team4.service.SymbolService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private MatchOrderService matchOrderService;
	
	@Autowired
	private SymbolService symbolService;

	@RequestMapping(value = "/addOrder")
	public ModelAndView addOrder(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			Order order) throws Exception {
		ModelAndView modelAndView = new ModelAndView("redirect:/main/mainPage");

		Trader trader = (Trader) request.getSession().getAttribute("cur_trader");
		double cur_price = 0; // (Double) request.getSession().getAttribute("cur_price");
		List<Symbol> symbolList = symbolService.getAllSymbol();
		for (Symbol symbol : symbolList) {
			if (symbol.getSymbol().equals(order.getSymbol())) {
				cur_price = symbol.getLast_sale();
				break;
			}
		}
		System.out.println("cur_price: " + cur_price);
		order.setTraderName(trader.getTraderName());
		order.setSta("alive");

		List<Order> symbolOrders = orderService.getAskOrdersBySymbol(order.getSymbol());
		symbolOrders.addAll(orderService.getBidOrdersBySymbol(order.getSymbol()));
		Symbol newSymbol = new Symbol();

		for (Order so : symbolOrders) {
			if (so.getSta().equals("limit")) {
				if (so.getCond().equals("Market Order")) {
					if (so.getSide().equals("buy")) {
						if (cur_price <= so.getPrice()) {
							so.setSta("alive");
							orderService.updateOrder(so);
						}
					} else {
						if (cur_price >= so.getPrice()) {
							so.setSta("alive");
							orderService.updateOrder(so);
						}
					}
				} else if (order.getCond().equals("Limit Order")) {
					if (so.getSide().equals("buy")) {
						if (cur_price <= so.getOtherPrice()) {
							so.setSta("alive");
							orderService.updateOrder(so);
						}
					} else {
						if (cur_price >= so.getOtherPrice()) {
							so.setSta("alive");
							orderService.updateOrder(so);
						}
					}
				}
			}
		}

		orderService.addOrder(order);
		order.setOrderId(orderService.currentOrderId());

		if (order.getCond().equals("Limit Order")) {
			if (order.getSide().equals("buy")) {
				if (cur_price > order.getOtherPrice()) {
					order.setSta("limit");
					orderService.updateOrder(order);
				} else {
					modelAndView = limitOrder(request, order);
				}
			} else {
				if (cur_price < order.getOtherPrice()) {
					order.setSta("limit");
					orderService.updateOrder(order);
				} else {
					modelAndView = limitOrder(request, order);
				}
			}
			newSymbol.setLast_sale(order.getOtherPrice());
		} else if (order.getCond().equals("Market Order")) {
			if (order.getSide().equals("buy")) {
				if (cur_price > order.getPrice()) {
					order.setSta("limit");
					orderService.updateOrder(order);
				} else {
					modelAndView = matchingMarket(request, order);
				}
			} else {
				if (cur_price < order.getPrice()) {
					order.setSta("limit");
					orderService.updateOrder(order);
				} else {
					modelAndView = matchingMarket(request, order);
				}
			}
			newSymbol.setLast_sale(order.getPrice());
		}
		
		symbolService.updateSymbol(newSymbol);
		return modelAndView;
	}

	private ModelAndView matchingMarket(HttpServletRequest request, Order order) throws Exception {
		System.out
				.println("=====================================MarketOrder Begin Matching============================");
		ModelAndView modelAndView = new ModelAndView();
		int count = order.getQty();
		int matchCount = 0;
		List<Order> matchOrders = new ArrayList<Order>();
		List<Order> existOrders = null;
		if (order.getSide().equals("buy")) {
			existOrders = orderService.getAskOrdersBySymbol(order.getSymbol());
		} else {
			existOrders = orderService.getBidOrdersBySymbol(order.getSymbol());
		}
		for (Order o : existOrders) {
			if (o.getQty() == order.getQty()) {
				matchOrders.add(o);
				matchCount += order.getQty();
				orderService.closeMatchedOrder(order.getOrderId());
				orderService.closeMatchedOrder(o.getOrderId());
			} else if (o.getQty() < order.getQty()) {
				matchOrders.add(o);
				matchCount += o.getQty();
				order.setQty(order.getQty() - o.getQty());
				orderService.closeMatchedOrder(o.getOrderId());
				orderService.updateOrder(order);
				// addMatchedOrder(request, order, o);
			} else {
				if (o.getFok().equals("FILL")) {
					matchOrders.add(o);
					matchCount += order.getQty();
					o.setQty(o.getQty() - order.getQty());
					orderService.closeMatchedOrder(order.getOrderId());
					orderService.updateOrder(o);
				}
			}
			if (matchCount == count) {
				break;
			}
		}
		if (matchCount < count) {
			orderService.deleteOrder(order.getOrderId());
		}
		modelAndView = matchSuccessOperation(request, order, count, matchOrders);
		System.out.println("=====================================MarketOrder End Matching============================");
		modelAndView.setViewName("redirect:/main/mainPage");
		return modelAndView;
	}

	private ModelAndView limitOrder(HttpServletRequest request, Order order) throws Exception {
		System.out
				.println("=====================================LimitOrder Begin Matching============================");
		ModelAndView modelAndView = new ModelAndView();
		List<Order> orders = new ArrayList<Order>();
		List<Order> matchedOrder = new ArrayList<Order>();
		int count = order.getQty();
		int matchCount = 0;
		if (order.getSide().equals("buy")) {
			orders = orderService.getAskOrdersBySymbol(order.getSymbol());
			for (Order o : orders) {
				if (o.getPrice() <= order.getPrice()) {
					if (o.getQty() == order.getQty()) {
						matchedOrder.add(o);
						matchCount += order.getQty();
						orderService.closeMatchedOrder(order.getOrderId());
						orderService.closeMatchedOrder(o.getOrderId());
					} else if (o.getQty() > order.getQty()) {
						if (o.getFok().equals("FILL")) {
							matchedOrder.add(o);
							o.setQty(o.getQty() - order.getQty());
							matchCount += order.getQty();
							orderService.closeMatchedOrder(order.getOrderId());
							orderService.updateOrder(o);
						}
					} else {
						if (order.getFok().equals("FILL")) {
							matchedOrder.add(o);
							order.setQty(order.getQty() - o.getQty());
							matchCount += o.getQty();
							orderService.closeMatchedOrder(o.getOrderId());
							orderService.updateOrder(order);
						}
					}
				}
				if (matchCount == count) {
					break;
				}
			}
		} else {
			orders = orderService.getBidOrdersBySymbol(order.getSymbol());
			for (Order o : orders) {
				if (o.getPrice() >= order.getPrice()) {
					if (o.getQty() == order.getQty()) {
						matchedOrder.add(o);
						matchCount += order.getQty();
						orderService.closeMatchedOrder(order.getOrderId());
						orderService.closeMatchedOrder(o.getOrderId());
					} else if (o.getQty() > order.getQty()) {
						if (o.getFok().equals("FILL")) {
							matchedOrder.add(o);
							o.setQty(o.getQty() - order.getQty());
							matchCount += order.getQty();
							orderService.closeMatchedOrder(order.getOrderId());
							orderService.updateOrder(o);
						}
					} else {
						if (order.getFok().equals("FILL")) {
							matchedOrder.add(o);
							order.setQty(order.getQty() - o.getQty());
							matchCount += o.getQty();
							orderService.closeMatchedOrder(o.getOrderId());
							orderService.updateOrder(order);
						}
					}
				}
				if (matchCount == count) {
					break;
				}
			}
		}
		modelAndView = matchSuccessOperation(request, order, count, matchedOrder);
		modelAndView.setViewName("redirect:/main/mainPage");
		System.out.println("=====================================LimitOrder End Matching============================");
		return modelAndView;
	}

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
		if (matchOrders.size() > 0) {
			request.getSession().setAttribute("cur_price", matchOrders.get(matchOrders.size() - 1).getPrice());
		}
		return modelAndView;
	}

	private void addMatchedOrder(HttpServletRequest request, Order bid, Order ask) throws Exception {
		System.out.println("askOrder:" + ask);
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

	}

	@RequestMapping(value = "/deleteOrder")
	// @ResponseBody
	public ModelAndView deleteOrder(HttpServletRequest request, @RequestParam("orderId") Integer orderId)
			throws Exception {
		String url = request.getHeader("Referer");
		System.out.println(url);
		orderService.deleteOrder(orderId);
		return new ModelAndView("redirect:" + url);
		// return "success";
	}

	@RequestMapping(value = "/symbolOrders/{symbol}")
	public ModelAndView searchOrders(HttpServletRequest request, @PathVariable("symbol") String symbol)
			throws Exception {
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
	public ModelAndView traderOrders(HttpServletRequest request, @PathVariable("traderName") String traderName)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Order> traderOrders = orderService.getBidOrdersByTrader(traderName);
		traderOrders.addAll(orderService.getAskOrdersByTrader(traderName));
		modelAndView.addObject("traderOrders", traderOrders);
		modelAndView.setViewName("/orderAndSymbolList");
		return modelAndView;
	}

}
