package com.team4.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.team4.service.TraderService;
import com.team4.util.MD5;
import com.team4.util.MatchOrderGrid;
import com.team4.util.OrderGrid;
import com.team4.util.SymbolGrid;

@Controller
@RequestMapping(value = "/main")
public class MainController {

	@Autowired
	private MatchOrderService matchOrderService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private SymbolService symbolService;

	@Autowired
	private TraderService traderService;

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, @ModelAttribute("trader") Trader trader1) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/login");
		return modelAndView;
	}

	@RequestMapping(value = "/register")
	public ModelAndView register(HttpServletRequest request, @ModelAttribute("trader") Trader trader1)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/register");
		return modelAndView;
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, @ModelAttribute("trader") Trader trader1) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/login");
		return modelAndView;
	}

	@RequestMapping(value = "/mainPage")
	public ModelAndView mainPage(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/mainPage");
		return modelAndView;
	}

	@RequestMapping(value = "/mainPage1")
	public ModelAndView mainPage1(HttpServletRequest request, @RequestParam("symbol") String symbol) throws Exception {
		// ModelAndView modelAndView = new ModelAndView();
		request.getSession().setAttribute("cur_symbol", symbol);
		// modelAndView.setViewName("/mainPage");
		System.out.println("go to main page!");
		return new ModelAndView("redirect:/main/mainPage");
	}

	@RequestMapping(value = "/allSymbols", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public SymbolGrid getGoodList(@RequestParam("current") int current, @RequestParam("rowCount") int rowCount) {
		System.out.println("获取所有的symbol分页。。。。");
		int allSymbols = symbolService.getAllSymbol().size();
		List<Symbol> symbolsList = symbolService.getPageSymbols(current, rowCount);
		SymbolGrid symboldGrid = new SymbolGrid();
		symboldGrid.setCurrent(current);
		symboldGrid.setRowCount(rowCount);
		symboldGrid.setRows(symbolsList);
		symboldGrid.setTotal(allSymbols);
		return symboldGrid;
	}

	@RequestMapping(value = "/allOrders", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public OrderGrid getUserList(HttpServletRequest request, @RequestParam("current") int current,
			@RequestParam("rowCount") int rowCount) {
		Trader cur_trader = (Trader) request.getSession().getAttribute("cur_trader");
		System.out.println(cur_trader.getTraderName());
		int total = orderService.getAskOrdersByTrader(cur_trader.getTraderName()).size()
				+ orderService.getBidOrdersByTrader(cur_trader.getTraderName()).size();
		List<Order> list = orderService.getAskOrdersByTrader(cur_trader.getTraderName());
		list.addAll(orderService.getBidOrdersByTrader(cur_trader.getTraderName()));
		for (Order o : list) {
			System.out.println(o);
		}
		OrderGrid orderGrid = new OrderGrid();
		orderGrid.setCurrent(current);
		orderGrid.setRowCount(rowCount);
		orderGrid.setRows(list);
		orderGrid.setTotal(total);
		return orderGrid;
	}

	@RequestMapping(value = "/firstLevel", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public MatchOrderGrid firstLevel(HttpServletRequest request, @RequestParam("current") int current,
			@RequestParam("rowCount") int rowCount) throws Exception {
		// ModelAndView modelAndView = new ModelAndView();
		// System.out.println("44444444444444444444");
		List<MatchOrder> leve1Orders = new ArrayList<MatchOrder>();
		List<Symbol> symbols = symbolService.getAllSymbol();
		// System.out.println("555555555555555555555");
		for (Symbol symbol : symbols) {
			// System.out.println("66666666666666666666");
			List<Order> maxBid = orderService.getMaxBidOrdersBySymbol(symbol.getSymbol());
			List<Order> minAsk = orderService.getMinAskOrdersBySymbol(symbol.getSymbol());
			int i = 0;
			for (i = 0; i < maxBid.size(); i++) {
				// System.out.println("777777777777777777777");
				MatchOrder matchOrder = new MatchOrder();
				Order bidOrder = maxBid.get(i);
				matchOrder.setMatchID("" + bidOrder.getOrderId() + "_");
				matchOrder.setBid_trader_name(bidOrder.getTraderName());
				matchOrder.setBid_price(bidOrder.getPrice());
				matchOrder.setBid_size(bidOrder.getQty());
				matchOrder.setSymbol(bidOrder.getSymbol());
				if (minAsk.size() > i) {
					Order askOrder = minAsk.get(i);
					if (askOrder != null) {
						matchOrder.setMatchID(matchOrder.getMatchID() + askOrder.getOrderId());
						matchOrder.setAsk_trdaer_name(askOrder.getTraderName());
						matchOrder.setAsk_price(askOrder.getPrice());
						matchOrder.setAsk_size(askOrder.getQty());
						matchOrder.setSymbol(askOrder.getSymbol());
					}
				} else {
					matchOrder.setMatchID(matchOrder.getMatchID() + "0");
				}
				leve1Orders.add(matchOrder);
			}
			if (i < minAsk.size()) {
				for (int j = i; j < minAsk.size(); j++) {
					MatchOrder matchOrder = new MatchOrder();
					Order askOrder = minAsk.get(i);
					matchOrder.setMatchID("0_" + askOrder.getOrderId());
					matchOrder.setAsk_trdaer_name(askOrder.getTraderName());
					matchOrder.setAsk_price(askOrder.getPrice());
					matchOrder.setAsk_size(askOrder.getQty());
					matchOrder.setSymbol(askOrder.getSymbol());
					leve1Orders.add(matchOrder);
				}
			}
		}
		// modelAndView.setViewName("/mainPage");
		// return modelAndView;
		MatchOrderGrid matchOrderGrid = new MatchOrderGrid();
		matchOrderGrid.setCurrent(current);
		matchOrderGrid.setRowCount(rowCount);
		matchOrderGrid.setRows(leve1Orders);
		matchOrderGrid.setTotal(leve1Orders.size());
		// System.out.println("8888888888888888888888888");
		return matchOrderGrid;
	}

	@RequestMapping(value = "/secondLevel", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public MatchOrderGrid secondLevel(HttpServletRequest request, @RequestParam("current") int current,
			@RequestParam("rowCount") int rowCount) throws Exception {
		List<MatchOrder> leve1Orders = new ArrayList<MatchOrder>();
		List<Symbol> symbols = symbolService.getAllSymbol();
		for (Symbol symbol : symbols) {
			List<Order> maxBid = orderService.getMaxBidOrdersBySymbol(symbol.getSymbol());
			List<Order> minAsk = orderService.getMinAskOrdersBySymbol(symbol.getSymbol());
			int i = 0;
			for (i = 0; i < maxBid.size(); i++) {
				MatchOrder matchOrder = new MatchOrder();
				Order bidOrder = maxBid.get(i);
				matchOrder.setMatchID("" + bidOrder.getOrderId() + "_");
				matchOrder.setBid_trader_name(bidOrder.getTraderName());
				matchOrder.setBid_price(bidOrder.getPrice());
				matchOrder.setBid_size(bidOrder.getQty());
				matchOrder.setSymbol(bidOrder.getSymbol());
				if (minAsk.size() > i) {
					Order askOrder = minAsk.get(i);
					if (askOrder != null) {
						matchOrder.setMatchID(matchOrder.getMatchID() + askOrder.getOrderId());
						matchOrder.setAsk_trdaer_name(askOrder.getTraderName());
						matchOrder.setAsk_price(askOrder.getPrice());
						matchOrder.setAsk_size(askOrder.getQty());
						matchOrder.setSymbol(askOrder.getSymbol());
					}
				} else {
					matchOrder.setMatchID(matchOrder.getMatchID() + "0");
				}
				leve1Orders.add(matchOrder);
			}
			if (i < minAsk.size()) {
				for (int j = i; j < minAsk.size(); j++) {
					MatchOrder matchOrder = new MatchOrder();
					Order askOrder = minAsk.get(i);
					matchOrder.setMatchID("_" + askOrder.getOrderId());
					matchOrder.setAsk_trdaer_name(askOrder.getTraderName());
					matchOrder.setAsk_price(askOrder.getPrice());
					matchOrder.setAsk_size(askOrder.getQty());
					matchOrder.setSymbol(askOrder.getSymbol());
					leve1Orders.add(matchOrder);
				}
			}
		}
		MatchOrderGrid matchOrderGrid = new MatchOrderGrid();
		matchOrderGrid.setCurrent(current);
		matchOrderGrid.setRowCount(rowCount);
		matchOrderGrid.setRows(leve1Orders);
		matchOrderGrid.setTotal(leve1Orders.size());
		return matchOrderGrid;
	}

}