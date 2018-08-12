package com.team4.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team4.pojo.Trader;
import com.team4.service.TraderService;
import com.team4.util.MD5;

@Controller
@RequestMapping(value = "trader")
public class TraderController {
	
	@Autowired
	private TraderService traderService;
	
	/**
	 * register
	 * @param request
	 * @param trader1
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addTrader")
	public ModelAndView addTrader(HttpServletRequest request, @ModelAttribute("trader") Trader trader1) throws Exception {
		String url = request.getHeader("Referer");
		Trader trader = traderService.getTraderInfo(trader1.getName());
		if (trader != null) {
			// 对密码进行加密
			String pass = MD5.md5(trader1.getPassword());
			trader1.setPassword(pass);
			traderService.addTrader(trader1);
		}
		if (trader != null) {
			System.out.println("The trader name has been used! Please change nama to try again!");
		}
		return new ModelAndView("redirect:" + url);
	}

	@RequestMapping(value = "/deleteTrader")
	public ModelAndView deleteTrader(HttpServletRequest request, @RequestParam(value = "id") Integer traderId) throws Exception {
		String url = request.getHeader("Referer");
		traderService.deleteTrader(traderId);
		return new ModelAndView("redirect:" + url);
	}
	
	@RequestMapping(value = "/allTraders")
	public ModelAndView allTraders(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Trader> allTraders = traderService.getAllTraders();
		modelAndView.addObject("allTraders", allTraders);
		modelAndView.setViewName("trader/allTraders");
		return modelAndView;
	}
	
}
