package com.team4.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.team4.service.MatchOrderService;
import com.team4.service.OrderService;
import com.team4.service.SymbolService;
import com.team4.service.TraderService;

@Controller
@RequestMapping(value = "")
public class MainController {
	
	@Autowired
	private MatchOrderService matchOrderService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SymbolService symbolService;
	
	@Autowired
	private TraderService traderService;
	
	

}
