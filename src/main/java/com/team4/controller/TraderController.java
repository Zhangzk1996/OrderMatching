package com.team4.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team4.pojo.Symbol;
import com.team4.pojo.Trader;
import com.team4.service.SymbolService;
import com.team4.service.TraderService;
import com.team4.util.MD5;

@Controller
@RequestMapping(value = "/trader")
public class TraderController {

	@Autowired
	private TraderService traderService;

	@Autowired
	private SymbolService symbolService;

	/**
	 * register
	 * 
	 * @param request
	 * @param trader1
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addTrader")
	public ModelAndView addTrader(HttpServletRequest request, @ModelAttribute("trader") Trader trader)
			throws Exception {
		String url = request.getHeader("Referer");
		Trader trader1 = traderService.getTraderInfo(trader.getTraderName());
		Trader trader2 = traderService.getTraderInfo(trader.getEmail());
//		System.out.println("trader1: " + trader1);
//		System.out.println("trader2: " + trader2);
		if (trader1 == null && trader2 == null) {
			// 对密码进行加密
			String pass = MD5.md5(trader.getPassword());
			trader.setPassword(pass);
			traderService.addTrader(trader);
		}
		else if(trader2 != null){
			request.getSession().setAttribute("emailRegisted", "Email has been registed");
			return new ModelAndView("redirect:/main/register");
		} 
		else {
			System.out.println("The trader name has been used! Please change nama to try again!");
			request.getSession().setAttribute("emailRegisted", "Name has been registed");
			return new ModelAndView("redirect:/main/register");
		}
		return new ModelAndView("redirect:/main/login");
	}

	@RequestMapping(value = "/login")
	public ModelAndView loginValid(HttpServletRequest request, HttpServletResponse response, Trader trader,
			ModelMap modelMap) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Trader cur_trader = traderService.getTraderInfo(trader.getEmail());
//		System.out.println("cur_trader: " + cur_trader);
		List<Symbol> symbols = symbolService.getAllSymbol();
		request.getSession().setAttribute("symbolData", symbols);
		if (cur_trader != null) {
			String pwd = MD5.md5(trader.getPassword());
			if (pwd.equals(cur_trader.getPassword())) {
				request.getSession().setAttribute("cur_trader", cur_trader);
//				modelAndView.setViewName("/mainPage");
				return new ModelAndView("redirect:/main/mainPage");
				// System.out.println("1111111111111");
			} else {
				modelAndView.addObject("passError", "Error password or username!");
				modelAndView.setViewName("/login");
				// System.out.println("22222222");
			}
			// System.out.println("333333333");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/deleteTrader")
	public ModelAndView deleteTrader(HttpServletRequest request, @RequestParam(value = "id") Integer traderId)
			throws Exception {
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
