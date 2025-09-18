package com.financialtracker.financial_portfolio_tracker.model;

import java.math.BigDecimal;

public class AssestsRequest {

	
	    private String ticker;
	    private Integer quantity;
	    private BigDecimal avgBuyPrice;
	    private Integer portfolioId;

	    // Getters and Setters
	    public String getTicker() {
	        return ticker;
	    }

	    public void setTicker(String ticker) {
	        this.ticker = ticker;
	    }

	    public Integer getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(Integer quantity) {
	        this.quantity = quantity;
	    }

	    public BigDecimal getAvgBuyPrice() {
	        return avgBuyPrice;
	    }

	    public void setAvgBuyPrice(BigDecimal avgBuyPrice) {
	        this.avgBuyPrice = avgBuyPrice;
	    }

	    public Integer getPortfolioId() {
	        return portfolioId;
	    }

	    public void setPortfolioId(Integer portfolioId) {
	        this.portfolioId = portfolioId;
	    }
	}
