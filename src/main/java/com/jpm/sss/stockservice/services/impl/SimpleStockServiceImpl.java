package com.jpm.sss.stockservice.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.jpm.sss.stockservice.model.Stock;
import com.jpm.sss.stockservice.model.Stock.StockSymbol;
import com.jpm.sss.stockservice.model.Trade;
import com.jpm.sss.stockservice.persistence.StocksPersistence;
import com.jpm.sss.stockservice.services.SimpleStockService;
import com.jpm.sss.stockservice.utils.PrimitivesUtils;
/**
 * Super Simple Stocks implementation to calculate the dividend yield, P/E Ratio, record trades, Stock Price and GBCE All Share Index.
 * 
 * @author Sandeep Lohani
 *
 */
public class SimpleStockServiceImpl implements SimpleStockService {

	/**
	 *
	 */
	private Logger logger = Logger.getLogger(SimpleStockServiceImpl.class);

	/**
	 * 
	 */
	private StocksPersistence stocksPersistence = null;


	/**
	 * 
	 * @param stocksPersistence
	 */
	public void setStocksPersistence(StocksPersistence stocksPersistence) {
		this.stocksPersistence = stocksPersistence;
	}


	/* (non-Javadoc)
	 * @see com.jpm.sss.stockservice.services.SimpleStockService#calculateDividendYield(com.jpm.sss.stockservice.model.Stock.StockSymbol)
	 */
	public double calculateDividendYield(StockSymbol stockSymbol) throws Exception{
		double dividendYield = -1.0;

		try{
			logger.debug("Calculating Dividend Yield for the stock symbol: "+stockSymbol);
			Stock stock = stocksPersistence.getStockBySymbol(stockSymbol);

			// If the stock is not supported the a exception is raised
			if(stock==null){
				throw new Exception("The stock symbol "+ stockSymbol + " doesn't exist in Super Simple Stock system.");
			}

			// Ticker price with value zero does not make any sense and could produce a zero division
			if(stock.getTicketPrice() <= 0.0){
				throw new Exception("The ticket price for the stock "+ stockSymbol +" should be greater than zero (0).");
			}
			dividendYield = stock.getDividendYield();
			dividendYield = PrimitivesUtils.roundTwoDecimalPlaces(dividendYield);
			logger.debug("Dividend Yield calculated: "+dividendYield);

		}catch(Exception exception){
			logger.error("Error calculating Dividend Yield for the stock symbol: "+stockSymbol+" " + exception.getMessage());
			throw new Exception("Error calculating Dividend Yield for the stock symbol: "+stockSymbol+" " + exception.getMessage());
		}
		return dividendYield;
	}


	/* (non-Javadoc)
	 * @see com.jpm.sss.stockservice.services.SimpleStockService#calculatePERatio(com.jpm.sss.stockservice.model.Stock.StockSymbol)
	 */
	public double calculatePERatio(StockSymbol stockSymbol) throws Exception{
		double peRatio = -1.0;
		try{
			logger.debug("Calculating P/E Ratio for the stock symbol: "+stockSymbol);
			Stock stock = stocksPersistence.getStockBySymbol(stockSymbol);

			// If the stock is not supported the a exception is raised
			if(stock==null){
				throw new Exception("The stock symbol "+ stockSymbol + " doesn't exist in Super Simple Stock system.");
			}

			// Ticker price with value zero does not make any sense and could produce a zero division
			if(stock.getTicketPrice() <= 0.0){
				throw new Exception("The ticket price for the stock "+ stockSymbol +" should be greater than zero (0).");
			}

			peRatio = stock.getPeRatio();
			peRatio = PrimitivesUtils.roundTwoDecimalPlaces(peRatio);
			logger.debug("  P/E Ratio calculated: "+peRatio);

		}catch(Exception exception){
			logger.error("Error calculating P/E Ratio for the stock symbol: "+stockSymbol+".", exception);
			throw new Exception("Error calculating P/E Ratio for the stock symbol: "+stockSymbol+".", exception);
		}
		return peRatio;
	}


	/* (non-Javadoc)
	 * @see com.jpm.sss.stockservice.services.SimpleStockService#recordTrade(com.jpm.sss.stockservice.model.Trade)
	 */
	public boolean recordTrade(Trade trade) throws Exception{
		boolean recordResult = false;
		try{
			logger.debug("Record Trade object: " + trade);

			// trade should be an object
			if(trade==null){
				throw new Exception("Trade object to record should not be null.");
			}

			// stock should be an object
			if(trade.getStock()==null){
				throw new Exception("A trade should be have a valid stock .");
			}

			// shares quantity should be greater than zero
			if(trade.getSharesQuantity()<=0){
				throw new Exception("Shares quantity should be greater than zero (0).");
			}

			// shares price should be greater than zero
			if(trade.getPrice()<=0.0){
				throw new Exception("Share price should be greater than zero (0).");
			}

			recordResult = stocksPersistence.recordTrade(trade);

			// Update the ticket price for the stock
			if(recordResult){
				trade.getStock().setTicketPrice(trade.getPrice());
			}
		}catch(Exception exception){
			logger.error("Error when trying to record a trade.", exception);
			throw new Exception("Error when trying to record a trade.", exception);
		}
		return recordResult;
	}



	/* (non-Javadoc)
	 * @see com.jpm.sss.stockservice.services.SimpleStockService#calculateStockPrice(com.jpm.sss.stockservice.model.Stock.StockSymbol)
	 */
	public double calculateStockPrice(StockSymbol stockSymbol) {
		double stockPrice = 0.0;
		logger.debug("Calculating stock price with last 15 min. trades for the stock symbol: "+stockSymbol);

		TreeMap<Date, Trade> allTrades = stocksPersistence.getTrades();
		// If no trade exists then log this info
		if(allTrades.size()<=0){
			logger.info("No trades available at all");
		}	else {
			Date now = new Date();
			// Date 15 minutes ago
			Date startTime = new Date(now.getTime() - (15 * 60 * 1000));
			// Get trades for the last 15 minutes
			SortedMap<Date, Trade> trades = allTrades.tailMap(startTime);			
			// Calculate stockPrice
			double totalStockPrice = 0.0;
			int totalQuantity = 0;
			for (Trade trade: trades.values()) {
				if(stockSymbol == trade.getStock().getStockSymbol()){
					totalQuantity += trade.getSharesQuantity();
					totalStockPrice += trade.getPrice() * trade.getSharesQuantity();	
				}			
			}
			if(totalQuantity>0){
				stockPrice =  totalStockPrice/totalQuantity;
			}
			stockPrice = PrimitivesUtils.roundTwoDecimalPlaces(stockPrice);
		}
		logger.debug("  Stock Price calculated: "+stockPrice);
		return stockPrice;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.jpm.sss.stockservice.services.SimpleStockService#calculateGBCEAllShareIndex()
	 */
	public double calculateGBCEAllShareIndex() throws Exception{
		logger.debug("CalculatingGBCE All Share Index");
		double allShareIndex = 0.0;
		double totalStockPrice = 0.0;
		// Calculate stock price for all stock in the system
		HashMap<StockSymbol, Stock> stocks = stocksPersistence.getStocks();
		// shares quantity should be greater than zero
		if(stocks.size()<=0){
			throw new Exception("No stocks returned by persistence layer.");
		}
		for(Stock stock: stocks.values()) {
			totalStockPrice+=stock.getTicketPrice();
		}
		if(totalStockPrice > 0.0){
			allShareIndex = Math.pow(totalStockPrice, 1.0 / stocks.size());
		}		
		allShareIndex = PrimitivesUtils.roundTwoDecimalPlaces(allShareIndex);
		logger.info(" GBCE All Share Index calculated: "+ allShareIndex);
		
		return allShareIndex;
	}
}
