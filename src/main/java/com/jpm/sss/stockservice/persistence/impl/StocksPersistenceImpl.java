package com.jpm.sss.stockservice.persistence.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.jpm.sss.stockservice.model.Stock;
import com.jpm.sss.stockservice.model.Stock.StockSymbol;
import com.jpm.sss.stockservice.model.Trade;
import com.jpm.sss.stockservice.persistence.StocksPersistence;
/**
 * Persistence layer to store all stocks data.
 * 
 * @author Sandeep Lohani
 *
 */
public class StocksPersistenceImpl implements StocksPersistence {

	/**
	 *
	 */
	private Logger logger = Logger.getLogger(StocksPersistenceImpl.class);

	/**
	 * 
	 */
	private HashMap<StockSymbol, Stock> stocks = null;

	/**
	 * 
	 */
	private TreeMap<Date, Trade> trades = null;

	/**
	 * 
	 */
	public StocksPersistenceImpl(){
		trades = new TreeMap<Date, Trade>();
		stocks = new HashMap<StockSymbol, Stock>();
	}


	/**
	 * @return the stocks
	 */
	public HashMap<StockSymbol, Stock> getStocks() {
		return stocks;
	}

	/**
	 * 
	 * @param stocks
	 */
	public void setStocks(HashMap<StockSymbol, Stock> stocks) {
		this.stocks = stocks;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.stocks.simple.backend.StocksEntityManager#getTrades()
	 */
	public TreeMap<Date, Trade> getTrades() {
		return trades;
	}

	/**
	 * 
	 * @param trades
	 */
	public void setTrades(TreeMap<Date, Trade> trades) {
		this.trades = trades;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.stocks.simple.backend.StocksEntityManager#recordTrade(com.jpmorgan.stocks.simple.model.Trade)
	 */
	public boolean recordTrade(Trade trade) throws Exception{
		boolean result = false;
		if(trade !=null){			
			trades.put(trade.getTimeStamp(),trade);
			result = true;
		} else
			throw new Exception("error while recording a trade.");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jpmorgan.stocks.simple.backend.StocksEntityManager#getStockBySymbol(java.lang.String)
	 */
	public Stock getStockBySymbol(StockSymbol stockSymbol){
		Stock stock = null;
		try{
			if(stockSymbol!=null){
				stock = stocks.get(stockSymbol);
			}
		}catch(Exception exception){
			logger.error("error while fetching the stock object for : "+stockSymbol+ " exeception message: "+ exception.getMessage());
		}
		return stock;
	}



}
