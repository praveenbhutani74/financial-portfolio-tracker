package com.financialtracker.financial_portfolio_tracker.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="assets")
public class EAssets {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "portfolio_id")
	private EPortfolio ePortfolio;
	
	@Column(name ="ticker")
	private String ticker;
	
	@Column(name ="quantity")
	private Integer quantity;
	
	@Column(name ="avg_buy_price")
	private BigDecimal avgBuyPrice;
	
	@Column(name ="created_at")
 	private Timestamp createdAt;
	
	@Column(name ="updated_at")
 	private Timestamp updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EPortfolio getePortfolio() {
		return ePortfolio;
	}

	public void setePortfolio(EPortfolio ePortfolio) {
		this.ePortfolio = ePortfolio;
	}

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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
