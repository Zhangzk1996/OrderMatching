package com.team4.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.team4.dao.SymbolMapper;
import com.team4.pojo.Symbol;
import com.team4.service.SymbolService;

@Service("SymbolService")
public class SymbolServiceImpl implements SymbolService {

	@Resource
	private SymbolMapper symbolMapper;
	
	public Symbol getSymbolById(String symbol) {
		Symbol sym = symbolMapper.getSymbolById(symbol);
		return sym;
	}

	public List<Symbol> getAllSymbol() {
		List<Symbol> symbols = symbolMapper.getAllSymbol();
		return symbols;
	}

	public List<Symbol> getSymbolsByStr(String str) {
		List<Symbol> symbols = symbolMapper.getSymbolsByStr(str);
		return symbols;
	}

	public List<Symbol> getPageSymbols(int current, int rowCount) {
		PageHelper.startPage(current,rowCount);
		List<Symbol> symbols = symbolMapper.getAllSymbol();
		return symbols;
	}

}
