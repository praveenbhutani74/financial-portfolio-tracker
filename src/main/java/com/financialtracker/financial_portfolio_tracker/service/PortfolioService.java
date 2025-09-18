package com.financialtracker.financial_portfolio_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.financialtracker.financial_portfolio_tracker.model.PortfolioRequest;
import com.financialtracker.financial_portfolio_tracker.model.PortfolioResponse;

@Service
public interface PortfolioService {

	PortfolioResponse createPortfolio(PortfolioRequest portfolioRequest);
	
	PortfolioResponse updatePortfolio(PortfolioRequest portfolioRequest , Integer id);
	
	List<PortfolioResponse> fetchAllPortfolio();
	
	PortfolioResponse getPortfolioById(Integer id);

	
	void deletePortfolio(Integer id);
	

	
}
