package com.jpm.sss.stockservice.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import com.jpm.sss.stockservice.model.Stock;
import com.jpm.sss.stockservice.model.Stock.StockSymbol;
import com.jpm.sss.stockservice.model.Trade;
/**
 * Persistence layer to store all stocks data.
 * 
 * @author Sandeep Lohani
 *
 */
public interface StocksPersistence {
	
	/**
	 * Record a trade.	 
	 * @param trade.	
	 * @return return True when success otherwise False.	
	 * @throws Exception.
	 */
	public boolean recordTrade(Trade trade) throws Exception;
	
	/**
	 * return map of trades sorted by date.
	 * @return ArrayList.
	 */
	public TreeMap<Date, Trade> getTrades();
	
	/**
	 * return the stocks by symbol.
	 * @param stockSymbol
	 * @return Stock
	 */
	public Stock getStockBySymbol(StockSymbol stockSymbol);
	
	/**
	 * return all the stocks.
	 * 
	 * @return HashMap.
	 */
	public HashMap<StockSymbol, Stock> getStocks();
}
