package com.financialtracker.financial_portfolio_tracker.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.financialtracker.financial_portfolio_tracker.entity.EAssets;
import com.financialtracker.financial_portfolio_tracker.entity.EPortfolio;
import com.financialtracker.financial_portfolio_tracker.entity.EUser;
import com.financialtracker.financial_portfolio_tracker.mapper.AssestsMapper;
import com.financialtracker.financial_portfolio_tracker.mapper.PortfolioMapper;
import com.financialtracker.financial_portfolio_tracker.model.AssestsResponse;
import com.financialtracker.financial_portfolio_tracker.model.PortfolioRequest;
import com.financialtracker.financial_portfolio_tracker.model.PortfolioResponse;
import com.financialtracker.financial_portfolio_tracker.repository.AssestsRepository;
import com.financialtracker.financial_portfolio_tracker.repository.PortfolioRepository;
import com.financialtracker.financial_portfolio_tracker.repository.UserRepository;
import com.financialtracker.financial_portfolio_tracker.service.AssestsService;
import com.financialtracker.financial_portfolio_tracker.service.PortfolioService;

public class PortfolioServiceImpl implements PortfolioService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PortfolioRepository portfolioRepository;
	
	@Autowired
    private AssestsRepository assestsRepository;
	
	@Autowired 
	private AssestsService assestsService;

	@Override
	public PortfolioResponse createPortfolio(PortfolioRequest portfolioRequest) {
		
		 EUser eUser = userRepository.findById(portfolioRequest.getUserId())
		            .orElseThrow(() -> new RuntimeException(
		                    "User not found with id: " + portfolioRequest.getUserId())
		            );
		EPortfolio ePortfolio = PortfolioMapper.mapPortfolioRequestToEntity(portfolioRequest, eUser);
		ePortfolio =portfolioRepository.save(ePortfolio);
		return buildPortfolioResponse(ePortfolio);
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
		    return buildPortfolioResponse(ePortfolio);
	}

	@Override
	public List<PortfolioResponse> fetchAllPortfolio() {
		
		List<EPortfolio> portfolios = portfolioRepository.findAll();
		
		List<PortfolioResponse> portfolioResponses = new ArrayList<>();
			for (EPortfolio ePortfolio : portfolios) {
				PortfolioResponse portfolioResponse=buildPortfolioResponse(ePortfolio);
				portfolioResponses.add(portfolioResponse);
			}
		
		return portfolioResponses;
	}

	
	@Override
	public PortfolioResponse getPortfolioById(Integer id) {
	    EPortfolio ePortfolio = portfolioRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Portfolio not found with id:- " + id));

	    return buildPortfolioResponse(ePortfolio);
	}

	@Override
	public void deletePortfolio(Integer id) {
		EPortfolio ePortfolio = portfolioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException ("Portfolio not found with id:-" + id));
		
		portfolioRepository.delete(ePortfolio);
	}

	
	private PortfolioResponse buildPortfolioResponse(EPortfolio ePortfolio) {
	    PortfolioResponse portfolioResponse = PortfolioMapper.mapPortfolioEntityToResponse(ePortfolio);

	    List<EAssets> assets = assestsRepository.findByEPortfolioId(ePortfolio.getId());
	    List<AssestsResponse> assetResponses = new ArrayList<>();
	    BigDecimal totalValue = BigDecimal.ZERO;

	    for (EAssets asset : assets) {
	        BigDecimal currentPrice = BigDecimal.ZERO;
	        try {
	            currentPrice = assestsService.getCurrentPrice(asset.getTicker());
	        } catch (Exception e) {
	            System.out.println("Price fetch failed for " + asset.getTicker() + ": " + e.getMessage());
	        }

	        AssestsResponse response = AssestsMapper.mapAssetEntityToResponse(asset, currentPrice);
	        assetResponses.add(response);

	        if (response.getCurrentValue() != null) {
	            totalValue = totalValue.add(response.getCurrentValue());
	        }
	    }

	    portfolioResponse.setAssets(assetResponses);
	    portfolioResponse.setTotalCurrentValue(totalValue);

	    return portfolioResponse;
	}
}
