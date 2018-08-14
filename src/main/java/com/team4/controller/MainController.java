package com.team4.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.team4.pojo.Order;
import com.team4.pojo.Symbol;
import com.team4.pojo.Trader;
import com.team4.service.MatchOrderService;
import com.team4.service.OrderService;
import com.team4.service.SymbolService;
import com.team4.service.TraderService;
import com.team4.util.MD5;
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

}