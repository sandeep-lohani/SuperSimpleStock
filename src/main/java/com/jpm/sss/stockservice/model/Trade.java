package com.jpm.sss.stockservice.model;

import java.util.Date;
/**
 * POJO for trade
 * @author Sandeep Lohani
 * 
 */
public class Trade {
	
	public enum TradeIndicator {
		BUY, SELL
	}
	
	/**
	 * 
	 */
	private Date timeStamp = null;
	
	/**
	 * 
	 */
	private Stock stock = null;
	
	/**
	 * 
	 */
	private TradeIndicator tradeIndicator = TradeIndicator.BUY;
	
	/**
	 * 
	 */
	private int sharesQuantity = 0;
	
	/**
	 * 
	 */
	private double price = 0.0;
	
	
	/**
	 * Default constructor
	 */
	public Trade(){
	}
	
	/**
	 * Parameterised constructor
	 * @param timeStamp
	 * @param stock
	 * @param tradeIndicator
	 * @param sharesQuantity
	 * @param price
	 */
	public Trade(Date timeStamp, Stock stock, TradeIndicator tradeIndicator,
			int sharesQuantity, double price) {
		super();
		this.timeStamp = timeStamp;
		this.stock = stock;
		this.tradeIndicator = tradeIndicator;
		this.sharesQuantity = sharesQuantity;
		this.price = price;
	}

	/**
	 * 
	 * @return
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSharesQuantity() {
		return sharesQuantity;
	}
	
	/**
	 * 
	 * @param sharesQuantity
	 */
	public void setSharesQuantity(int sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}
	
	/**
	 * 
	 * @return
	 */
	public TradeIndicator getTradeIndicator() {
		return tradeIndicator;
	}
	
	/**
	 * 
	 * @param tradeIndicator
	 */
	public void setTradeIndicator(TradeIndicator tradeIndicator) {
		this.tradeIndicator = tradeIndicator;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * 
	 * @return
	 */
	public Stock getStock() {
		return stock;
	}
	
	/**
	 * 
	 * @param stock
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Trade [timeStamp=" + timeStamp + ", stock=" + stock
				+ ", tradeIndicator=" + tradeIndicator + ", sharesQuantity="
				+ sharesQuantity + ", price=" + price + "]";
	}
	
}
