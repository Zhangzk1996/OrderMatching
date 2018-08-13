package com.team4.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.team4.dao.MatchOrderMapper;
import com.team4.pojo.MatchOrder;
import com.team4.service.MatchOrderService;

@Service("MatchOrderService")
public class MatchOrderServiceImpl implements MatchOrderService {

	@Resource
	private MatchOrderMapper matchOrderMapper;
	
	public void addMatchOrder(MatchOrder matchOrder) {
		matchOrderMapper.addMatchOrder(matchOrder);
	}

	public MatchOrder getMatchOrderById(String matchOrderId) {
		MatchOrder matchOrder = matchOrderMapper.getMatchOrderById(matchOrderId);
		return matchOrder;
	}

	public List<MatchOrder> getAllMatchOrders() {
		List<MatchOrder> matchOrders = getAllMatchOrders();
		return matchOrders;
	}

	public int getMatchOrderCount() {
		int count = 0;
		count = matchOrderMapper.getMachOrderCount();
		return count;
	}

}
