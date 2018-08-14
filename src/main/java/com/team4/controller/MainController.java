package com.team4.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team4.pojo.Trader;
import com.team4.service.MatchOrderService;
import com.team4.service.OrderService;
import com.team4.service.SymbolService;
import com.team4.service.TraderService;
import com.team4.util.MD5;

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
	public ModelAndView register(HttpServletRequest request, @ModelAttribute("trader") Trader trader1) throws Exception {
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

}
