package com.financialtracker.financial_portfolio_tracker.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.financialtracker.financial_portfolio_tracker.entity.EAssets;
import com.financialtracker.financial_portfolio_tracker.entity.EPortfolio;
import com.financialtracker.financial_portfolio_tracker.mapper.AssestsMapper;
import com.financialtracker.financial_portfolio_tracker.model.AssestsRequest;
import com.financialtracker.financial_portfolio_tracker.model.AssestsResponse;
import com.financialtracker.financial_portfolio_tracker.repository.AssestsRepository;
import com.financialtracker.financial_portfolio_tracker.repository.PortfolioRepository;
import com.financialtracker.financial_portfolio_tracker.service.AssestsService;

public class AssestsServiceImpl implements AssestsService{
	
	@Value("${alphavantage.api.key}")
    private String apiKey;
	
	@Autowired
	private AssestsRepository assestsRepository;
	
	@Autowired
	private PortfolioRepository portfolioRepository;
	
	 private final RestTemplate restTemplate = new RestTemplate();
	
	@Override
    public AssestsResponse createAssests(AssestsRequest assestsRequest) {
        EPortfolio ePortfolio = portfolioRepository.findById(assestsRequest.getPortfolioId())
                .orElseThrow(() -> new RuntimeException(
                        "Portfolio not found with id:- " + assestsRequest.getPortfolioId()));

        EAssets eAssets = AssestsMapper.mapAssetRequestToEntity(assestsRequest, ePortfolio);
        eAssets = assestsRepository.save(eAssets);
        BigDecimal currentPrice = getCurrentPrice(eAssets.getTicker());
        return AssestsMapper.mapAssetEntityToResponse(eAssets, currentPrice);
    }

	 @Override
	    public AssestsResponse updateAssets(AssestsRequest assestsRequest, Integer id) {
	        EAssets assets = assestsRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Asset not found with id:- " + id));

	        EPortfolio ePortfolio = portfolioRepository.findById(assestsRequest.getPortfolioId())
	                .orElseThrow(() -> new RuntimeException(
	                        "Portfolio not found with id:- " + assestsRequest.getPortfolioId()));

	        EAssets eAssets = AssestsMapper.mapUpdateAssetRequestToEntity(assestsRequest, id, ePortfolio);
	        eAssets = assestsRepository.save(eAssets);

	        BigDecimal currentPrice = getCurrentPrice(eAssets.getTicker());
	        return AssestsMapper.mapAssetEntityToResponse(eAssets, currentPrice);
	    }

	 @Override
	 public List<AssestsResponse> fetchAllAssests() {
	     List<EAssets> assets = assestsRepository.findAll();
	     List<AssestsResponse> responses = new ArrayList<>();

	     for (EAssets eAssets : assets) {
	         BigDecimal currentPrice = getCurrentPrice(eAssets.getTicker());
	         AssestsResponse response = AssestsMapper.mapAssetEntityToResponse(eAssets, currentPrice);
	         responses.add(response);
	     }
	     return responses;
	 }

	    @Override
	    public String deleteAssests(Integer id) {
	        EAssets eAssets = assestsRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Asset not found with id:- " + id));

	        assestsRepository.delete(eAssets);
	        return "Asset deleted successfully with id:- " + id;
	    }
	    
	    @Override
	    public BigDecimal getCurrentPrice(String ticker) {
	        String url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="
	                + ticker + "&apikey=" + apiKey;

	        ResponseEntity<JsonNode> response = restTemplate.exchange(
	                url,
	                HttpMethod.GET,
	                HttpEntity.EMPTY,
	                JsonNode.class
	        );

	        JsonNode quote = response.getBody().path("Global Quote");
	        String priceStr = quote.path("05. price").asText();

	        if (priceStr == null || priceStr.isEmpty()) {
	            throw new RuntimeException("Price not available for: " + ticker);
	        }
	        return new BigDecimal(priceStr);
	    }

	    @Override
	    public String deleteAssetByTicker(Integer portfolioId, String ticker) {
	        EAssets eAssets = assestsRepository.findByEPortfolioIdAndTicker(portfolioId, ticker)
	                .orElseThrow(() -> new RuntimeException("Asset not found with ticker: " + ticker));
	        assestsRepository.delete(eAssets);
	        return "Asset deleted successfully with ticker:- " + ticker;
	    }


}
