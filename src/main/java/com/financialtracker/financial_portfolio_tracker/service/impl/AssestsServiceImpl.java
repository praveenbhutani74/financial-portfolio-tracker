package com.financialtracker.financial_portfolio_tracker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.financialtracker.financial_portfolio_tracker.entity.EAssets;
import com.financialtracker.financial_portfolio_tracker.entity.EPortfolio;
import com.financialtracker.financial_portfolio_tracker.mapper.AssestsMapper;
import com.financialtracker.financial_portfolio_tracker.model.AssestsRequest;
import com.financialtracker.financial_portfolio_tracker.model.AssestsResponse;
import com.financialtracker.financial_portfolio_tracker.repository.AssestsRepository;
import com.financialtracker.financial_portfolio_tracker.repository.PortfolioRepository;
import com.financialtracker.financial_portfolio_tracker.service.AssestsService;

public class AssestsServiceImpl implements AssestsService{
	
	@Autowired
	private AssestsRepository assestsRepository;
	
	@Autowired
	private PortfolioRepository portfolioRepository;
	
	@Override
    public AssestsResponse createAssests(AssestsRequest assestsRequest) {
        EPortfolio ePortfolio = portfolioRepository.findById(assestsRequest.getPortfolioId())
                .orElseThrow(() -> new RuntimeException(
                        "Portfolio not found with id:- " + assestsRequest.getPortfolioId()));

        EAssets eAssets = AssestsMapper.mapAssetRequestToEntity(assestsRequest, ePortfolio);
        eAssets = assestsRepository.save(eAssets);
        return AssestsMapper.mapAssetEntityToResponse(eAssets);
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

	        return AssestsMapper.mapAssetEntityToResponse(eAssets);
	    }

	    @Override
	    public List<AssestsResponse> fetchAllAssests() {
	        List<EAssets> assets = assestsRepository.findAll();
	        List<AssestsResponse> responses = new ArrayList<>();

	        for (EAssets eAssets : assets) {
	            AssestsResponse response = AssestsMapper.mapAssetEntityToResponse(eAssets);
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

}
