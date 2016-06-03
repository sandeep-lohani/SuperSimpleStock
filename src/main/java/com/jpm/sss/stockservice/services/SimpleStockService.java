package com.jpm.sss.stockservice.services;

import com.jpm.sss.stockservice.model.Stock.StockSymbol;
import com.jpm.sss.stockservice.model.Trade;
/**
 * Super Simple Stocks interface to calculate the dividend yield, P/E Ratio, record trades, Stock Price and GBCE All Share Index.
 * 
 * @author Sandeep Lohani
 *
 */
public interface SimpleStockService {
	
	/**
	 * Calculates the dividend yield for a given stock
	 * 
	 * @param stockSymbol
	 * 
	 * @return the dividend yield
	 * 
	 * @throws Exception
	 */
	public double calculateDividendYield(StockSymbol stockSymbol) throws Exception;
	
	/**
	 * Calculates the P/E Ratio for a given stock.
	 * 
	 * @param stockSymbol
	 * 
	 * @return the P/E Ratio
	 * 
	 * @throws Exception
	 */
	public double calculatePERatio(StockSymbol stockSymbol) throws Exception;
	
	/**
	 * Record a trade
	 * 
	 * @param trade
	 * 
	 * @return True when the record is successful otherwise False.
	 */
	public boolean recordTrade(Trade trade) throws Exception;
	
	/**
	 * Calculates stock price as per symbol
	 * 
	 * @param stockSymbol
	 * @return
	 * @throws Exception
	 */
	public double calculateStockPrice(StockSymbol stockSymbol) throws Exception;
	
	/**
	 * Calculates the GBCE All Share Index for all stocks.
	 * 
	 * @return
	 * @throws Exception
	 */
	public double calculateGBCEAllShareIndex() throws Exception;
}
