package com.financialtracker.financial_portfolio_tracker.mapper;

import com.financialtracker.financial_portfolio_tracker.entity.EAssets;
import com.financialtracker.financial_portfolio_tracker.entity.EPortfolio;
import com.financialtracker.financial_portfolio_tracker.model.AssestsRequest;
import com.financialtracker.financial_portfolio_tracker.model.AssestsResponse;
import com.financialtracker.financial_portfolio_tracker.model.PortfolioResponse;
import com.financialtracker.financial_portfolio_tracker.model.UserResponse;

public class AssestsMapper {
	  public static EAssets mapAssetRequestToEntity(AssestsRequest request , EPortfolio ePortfolio) {
	        EAssets eAssets = new EAssets();
	        eAssets.setTicker(request.getTicker());
	        eAssets.setQuantity(request.getQuantity());
	        eAssets.setAvgBuyPrice(request.getAvgBuyPrice());
	        eAssets.setePortfolio(ePortfolio);
	        return eAssets;
	    }

	    public static EAssets mapUpdateAssetRequestToEntity(AssestsRequest request, Integer id , EPortfolio ePortfolio) {
	        EAssets eAssets = new EAssets();
	        eAssets.setId(id);
	        eAssets.setTicker(request.getTicker());
	        eAssets.setQuantity(request.getQuantity());
	        eAssets.setAvgBuyPrice(request.getAvgBuyPrice());
	        eAssets.setePortfolio(ePortfolio);
	        return eAssets;
	    }

	    public static AssestsResponse mapAssetEntityToResponse(EAssets eAssets) {
	    	AssestsResponse response = new AssestsResponse();
	        response.setId(eAssets.getId());
	        response.setTicker(eAssets.getTicker());
	        response.setQuantity(eAssets.getQuantity());
	        response.setAvgBuyPrice(eAssets.getAvgBuyPrice());
	        PortfolioResponse portfolioResponse = new PortfolioResponse();
	        portfolioResponse.setName(eAssets.getePortfolio().getName());
	        portfolioResponse.setId(eAssets.getePortfolio().getId());
	        UserResponse userResponse = new UserResponse();
	        userResponse.setId(eAssets.getePortfolio().geteUser().getId());
	        userResponse.setUsername(eAssets.getePortfolio().geteUser().getUsername());
	        userResponse.setEmail(eAssets.getePortfolio().geteUser().getEmail());
	        portfolioResponse.setUser(userResponse);
	        response.setPortfolio(portfolioResponse);
	        response.setCreatedAt(eAssets.getCreatedAt());
	        response.setUpdatedAt(eAssets.getUpdatedAt());
	        return response;
	    }
}
