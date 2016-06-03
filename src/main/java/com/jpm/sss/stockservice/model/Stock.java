package com.jpm.sss.stockservice.model;

/**
 * POJO for stock
 * @author Sandeep Lohani
 * 
 */
public class Stock {
	
	/**
	 * enum StockType
	 *
	 */
	public enum StockType {
		COMMON,	PREFERRED
	}
	
	/**
	 * enum StockSymbol
	 *
	 */
	public enum StockSymbol {		
		TEA, POP, ALE, GIN, JOE
	}
	
	/**
	 * Default constructor
	 */
	public Stock(){
		
	}
	
	/**
	 * Parameterised constructor
	 * @param stockSymbol
	 * @param stockType
	 * @param lastDividend
	 * @param fixedDividend
	 * @param parValue
	 */
	public Stock(StockSymbol stockSymbol, StockType stockType,
			double lastDividend, double fixedDividend, double parValue, double ticketPrice) {
		super();
		this.stockSymbol = stockSymbol;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.ticketPrice = ticketPrice;
	}
	/**
	 * 
	 */
	private StockSymbol stockSymbol = null;
	
	/**
	 * 
	 */
	private StockType stockType = StockType.COMMON;
	
	/**
	 * 
	 */
	private double lastDividend = 0.0;
	
	/**
	 * 
	 */
	private double fixedDividend = 0.0;
	
	/**
	 * 
	 */
	private double parValue = 0.0;
		
	/**
	 * 
	 */
	private double ticketPrice = 0.0; 
	
	/**
	 * 
	 * @return
	 */
	public StockSymbol getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * 
	 * @param stockSymbol
	 */
	public void setStockSymbol(StockSymbol stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * 
	 * @return
	 */
	public StockType getStockType() {
		return stockType;
	}

	/**
	 * 
	 * @param stockType
	 */
	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	/**
	 * 
	 * @return
	 */
	public double getLastDividend() {
		return lastDividend;
	}

	/**
	 * 
	 * @param lastDividend
	 */
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * 
	 * @return
	 */
	public double getFixedDividend() {
		return fixedDividend;
	}

	/**
	 * 
	 * @param fixedDividend
	 */
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	/**
	 * 
	 * @return
	 */
	public double getParValue() {
		return parValue;
	}

	/**
	 * 
	 * @param parValue
	 */
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	/**
	 * 
	 * @return
	 */
	public double getDividendYield() {
		double dividendYield = -1.0;
		if(ticketPrice>0.0){
			switch(stockType) {
			case COMMON:
					dividendYield = lastDividend/ticketPrice;
					break;			
			case PREFERRED:
				dividendYield = fixedDividend*parValue/ticketPrice;
				break;
			default:
			}
		}
		return dividendYield;
	}

	/**
	 * 
	 * @return
	 */
	public double getPeRatio() {
		double peRatio = -1.0;
		double divident = getDividendYield();
		if(divident> 0.0){
			peRatio = ticketPrice / getDividendYield();	
		}		
		return peRatio;
	}

	/**
	 * @return the tradePrice
	 */
	public double getTicketPrice() {
		return ticketPrice;
	}

	/**
	 * @param tradePrice the tradePrice to set
	 */
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stock [stockSymbol=" + stockSymbol + ", stockType=" + stockType
				+ ", lastDividend=" + lastDividend + ", fixedDividend="
				+ fixedDividend + ", parValue=" + parValue + ", ticketPrice="
				+ ticketPrice + "]";
	}
	
}
