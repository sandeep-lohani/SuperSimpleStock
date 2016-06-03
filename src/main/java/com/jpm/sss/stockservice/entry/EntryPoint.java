package com.jpm.sss.stockservice.entry;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jpm.sss.stockservice.model.Stock.StockSymbol;
import com.jpm.sss.stockservice.model.Trade;
import com.jpm.sss.stockservice.services.SimpleStockService;
/**
 * Main class to run Super-Simple-Stocks-master.
 * 
 * @author Sandeep Lohani
 *
 */
public class EntryPoint {
	
	/**
	 * Logger.
	 */
	private Logger logger = Logger.getLogger(EntryPoint.class);

	/**
	 * Spring context files.  
	 */
	private static final String SPRING_CONTEXT_FILE_NAME = "classpath:spring-main-context.xml";

	/**
	 * Spring context object.
	 */
	private AbstractApplicationContext springContext = null;

	/**
	 * Constructor.
	 */
	private EntryPoint(){
		logger.info("*********************************************\n"
				+   "                                                     "
				+ "Loading Spring Context for Super Simple Stocks.");
		springContext = new ClassPathXmlApplicationContext(SPRING_CONTEXT_FILE_NAME);
		springContext.registerShutdownHook();		
		logger.info("Spring Context created Successfully\n" + 
		"                                                      "
		+ "*********************************************");
	}
	
	/**
	 * @param args
	 */
	public static void main( String[] args ){
		 EntryPoint entryPoint = new EntryPoint();
		 SimpleStockService simpleStockService = (SimpleStockService) entryPoint.springContext.getBean("simpleStocksService");
		 entryPoint.startTrading(simpleStockService);		 
	}	

	/**
	 * @param simpleStockService
	 */
	private void startTrading(SimpleStockService simpleStockService ) {
		@SuppressWarnings("unchecked")
		ArrayList<Trade> tradeList = springContext.getBean("tradeList", ArrayList.class);
		logger.info("Trade List size: " + tradeList.size());
		
		try {
		//Record trades
		boolean recordResult = false;
		for(Trade trade: tradeList){			
				recordResult =  simpleStockService.recordTrade(trade);
				if(recordResult)
				logger.info("Trade recorded successfully: " + trade.getStock().getStockSymbol());			
		}
		
		// Calculates the dividend yield for the stock symbol
		for(StockSymbol stockSymbol: StockSymbol.values()){
				simpleStockService.calculateDividendYield(stockSymbol);		
		}
		
		// Calculates the P/E Ratio for the stock Symbol
		for(StockSymbol stockSymbol: StockSymbol.values()){
				simpleStockService.calculatePERatio(stockSymbol);
		}
		
		// Calculates the Stock Price for all stocks
		for(StockSymbol stockSymbol: StockSymbol.values()){
				simpleStockService.calculateStockPrice(stockSymbol);
		}
		
		// Calculates GBCE all share index
		simpleStockService.calculateGBCEAllShareIndex();		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("********** Trading Done *******************");
	}
}
