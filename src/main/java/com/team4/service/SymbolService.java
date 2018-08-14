package com.team4.service;

import java.util.List;

import com.team4.pojo.Symbol;

public interface SymbolService {
	
	public Symbol getSymbolById(String symbol);
	
	public List<Symbol> getAllSymbol();
	
	public List<Symbol> getSymbolsByStr(String str);

	public List<Symbol> getPageSymbols(int current, int rowCount);

}
