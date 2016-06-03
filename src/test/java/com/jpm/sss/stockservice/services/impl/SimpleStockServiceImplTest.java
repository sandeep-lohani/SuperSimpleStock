/**
 * 
 */
package com.jpm.sss.stockservice.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.jpm.sss.stockservice.model.Stock;
import com.jpm.sss.stockservice.model.Stock.StockSymbol;
import com.jpm.sss.stockservice.model.Stock.StockType;
import com.jpm.sss.stockservice.model.Trade;
import com.jpm.sss.stockservice.model.Trade.TradeIndicator;
import com.jpm.sss.stockservice.persistence.StocksPersistence;
import com.jpm.sss.stockservice.services.SimpleStockService;
/**
 * @author Sandeep Lohani
 *
 */
public class SimpleStockServiceImplTest {
	
	@InjectMocks
	SimpleStockService simpleStockService = new SimpleStockServiceImpl();
	
	private static Stock stock = new Stock(StockSymbol.POP, StockType.COMMON, 8.0, 0.0, 100, 10.00);
	
	@Spy
	private StocksPersistence stocksPersistence = new StocksPersistence(){

		@Override
		public boolean recordTrade(Trade trade) throws Exception {
			return true;
		}

		@Override
		public TreeMap<Date, Trade> getTrades() {
			TreeMap<Date, Trade> trades = new TreeMap<Date, Trade>();
			trades.put(trade.getTimeStamp(), trade);
			return trades;
		}

		@Override
		public Stock getStockBySymbol(StockSymbol stockSymbol) {
			return stock;
		}

		@Override
		public HashMap<StockSymbol, Stock> getStocks() {
			HashMap<StockSymbol, Stock> stocks = new HashMap<StockSymbol, Stock>();
			stocks.put(StockSymbol.POP, stock);
			stocks.put(StockSymbol.ALE, new Stock(StockSymbol.ALE, StockType.COMMON, 8.0, 0.0, 100, 21.00));
			return stocks;
		}		
	};
	
	private Trade trade;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		trade = new Trade(new Date(), stock, TradeIndicator.BUY, 200, 100.0);
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for {@link com.jpm.sss.stockservice.services.impl.SimpleStockServiceImpl#calculateDividendYield(com.jpm.sss.stockservice.model.Stock.StockSymbol)}.
	 */
	@Test
	public void testCalculateDividendYield() throws Exception{
		IsEqual<Double> isEqual = new IsEqual<Double>(new Double(0.8)); 
		Double divident = simpleStockService.calculateDividendYield(StockSymbol.POP);
		Assert.assertTrue(isEqual.matches(divident));
	}
	
	/**
	 * Test method for {@link com.jpm.sss.stockservice.services.impl.SimpleStockServiceImpl#calculatePERatio(com.jpm.sss.stockservice.model.Stock.StockSymbol)}.
	 */
	@Test
	public void testCalculatePERatio() throws Exception{
		IsEqual<Double> isEqual = new IsEqual<Double>(new Double(12.5)); 
		Double peRatio = simpleStockService.calculatePERatio(StockSymbol.POP);
		Assert.assertTrue(isEqual.matches(peRatio));
	}	

	/**
	 * Test method for {@link com.jpm.sss.stockservice.services.impl.SimpleStockServiceImpl#recordTrade(com.jpm.sss.stockservice.model.Trade)}.
	 */
	@Test
	public void testRecordTrade() throws Exception {
		boolean isrecorded = simpleStockService.recordTrade(trade);
		Assert.assertTrue(isrecorded);
	}

	/**
	 * Test method for {@link com.jpm.sss.stockservice.services.impl.SimpleStockServiceImpl#calculateStockPrice(com.jpm.sss.stockservice.model.Stock.StockSymbol)}.
	 */
	@Test
	public void testCalculateStockPrice() throws Exception {
		IsEqual<Double> isEqual = new IsEqual<Double>(new Double(100.0)); 
		Double stockPrice = simpleStockService.calculateStockPrice(StockSymbol.POP);
		Assert.assertTrue(isEqual.matches(stockPrice));
	}

	/**
	 * Test method for {@link com.jpm.sss.stockservice.services.impl.SimpleStockServiceImpl#calculateGBCEAllShareIndex()}.
	 */
	@Test
	public void testCalculateGBCEAllShareIndex() throws Exception {
		IsEqual<Double> isEqual = new IsEqual<Double>(new Double(11.0)); 
		Double gbceAllShareIndex = simpleStockService.calculateGBCEAllShareIndex();
		Assert.assertTrue(isEqual.matches(gbceAllShareIndex));
	}

}
