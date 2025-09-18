package com.financialtracker.financial_portfolio_tracker.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.financialtracker.financial_portfolio_tracker.entity.EPortfolio;
import com.financialtracker.financial_portfolio_tracker.entity.EUser;
import com.financialtracker.financial_portfolio_tracker.mapper.PortfolioMapper;
import com.financialtracker.financial_portfolio_tracker.model.PortfolioRequest;
import com.financialtracker.financial_portfolio_tracker.model.PortfolioResponse;
import com.financialtracker.financial_portfolio_tracker.repository.PortfolioRepository;
import com.financialtracker.financial_portfolio_tracker.repository.UserRepository;
import com.financialtracker.financial_portfolio_tracker.service.PortfolioService;

public class PortfolioServiceImpl implements PortfolioService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PortfolioRepository portfolioRepository;

	@Override
	public PortfolioResponse createPortfolio(PortfolioRequest portfolioRequest) {
		
		 EUser eUser = userRepository.findById(portfolioRequest.getUserId())
		            .orElseThrow(() -> new RuntimeException(
		                    "User not found with id: " + portfolioRequest.getUserId())
		            );
		EPortfolio ePortfolio = PortfolioMapper.mapPortfolioRequestToEntity(portfolioRequest, eUser);
		ePortfolio =portfolioRepository.save(ePortfolio);
		return PortfolioMapper.mapPortfolioEntityToResponse(ePortfolio);
	}

	@Override
	public PortfolioResponse updatePortfolio(PortfolioRequest portfolioRequest , Integer id) {
	
		    EPortfolio ePortfolio = portfolioRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException(
		                    "Portfolio not found with id:-" + id)
		            );

		    EUser eUser = userRepository.findById(portfolioRequest.getUserId())
		            .orElseThrow(() -> new RuntimeException(
		                    "User not found with id: " + portfolioRequest.getUserId())
		            );

		    ePortfolio = PortfolioMapper.mapUpdatePortfolioRequestToEntity(portfolioRequest, id ,eUser);
		    ePortfolio = portfolioRepository.save(ePortfolio);
		    return PortfolioMapper.mapPortfolioEntityToResponse(ePortfolio);
	}

	@Override
	public List<PortfolioResponse> fetchAllPortfolio() {
		
		List<EPortfolio> portfolios = portfolioRepository.findAll();
		
		List<PortfolioResponse> portfolioResponses = new ArrayList<>();
			for (EPortfolio ePortfolio : portfolios) {
				PortfolioResponse portfolioResponse=	PortfolioMapper.mapPortfolioEntityToResponse(ePortfolio);
				portfolioResponses.add(portfolioResponse);
			}
		
		return portfolioResponses;
	}


	@Override
	public void deletePortfolio(Integer id) {
		EPortfolio ePortfolio = portfolioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException ("Portfolio not found with id:-" + id));
		
		portfolioRepository.delete(ePortfolio);
	}

	
	
}
