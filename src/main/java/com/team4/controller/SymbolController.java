package com.team4.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team4.pojo.Symbol;
import com.team4.service.SymbolService;

@Controller
@RequestMapping(value = "/symbols")
public class SymbolController {
	
	@Autowired
	private SymbolService symbolService;
	
	/**
	 * 显示所有symbol
	 */
	@RequestMapping(value = "/allSymbols")
	public ModelAndView allSymbols(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Symbol> symbolList = symbolService.getAllSymbols();
		modelAndView.addObject("allSymbol", symbolList);
		modelAndView.setViewName("/login");
		
		return modelAndView;
	}
	
	/**
	 * 查询指定symbol
	 */
	@RequestMapping(value = "/search", method=RequestMethod.POST)
	public ModelAndView searchSymbolByName(HttpServletRequest request, @RequestParam(value = "symbol",required = false) String symbol) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Symbol> symbolList = symbolService.getSymbolsByStr(symbol);
		symbolList.add(symbolService.getSymbolById(symbol));
		modelAndView.addObject("searchResult", symbolList);
		modelAndView.setViewName("symbols/searchSymbols");
		
		return modelAndView;
	}

}
