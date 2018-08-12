package com.team4.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team4.dao.TraderMapper;
import com.team4.pojo.Trader;
import com.team4.service.TraderService;

@Service("TraderService")
public class TraderServiceImpl implements TraderService {

	@Resource
	private TraderMapper traderMapper;
	
	public void addTrader(Trader trader) {
		traderMapper.addTrader(trader);
	}

	public void deleteTrader(int traderId) {
		traderMapper.deleteTrader(traderId);
	}

	public Trader getTraderInfo(int traderId) {
		Trader trader = traderMapper.getTraderInfo(traderId);
		return trader;
	}

	public List<Trader> getAllTraders() {
		List<Trader> traders = traderMapper.getAllTraders();
		return traders;
	}

	public int getTraderCount() {
		int count = 0;
		count = traderMapper.getTraderCount();
		return count;
	}

}
