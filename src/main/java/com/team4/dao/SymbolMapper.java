package com.team4.dao;

import java.util.List;

import com.team4.pojo.Symbol;

public interface SymbolMapper {
	
	public Symbol getSymbolById(String symbol);
	
	public List<Symbol> getAllSymbol();
	
	public List<Symbol> getSymbolsByStr(String str);
	
	public void updateSymbol(Symbol symbol);

}
