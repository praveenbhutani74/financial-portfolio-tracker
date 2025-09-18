package com.financialtracker.financial_portfolio_tracker.mapper;

import java.sql.Timestamp;

import com.financialtracker.financial_portfolio_tracker.entity.EPortfolio;
import com.financialtracker.financial_portfolio_tracker.entity.EUser;
import com.financialtracker.financial_portfolio_tracker.model.PortfolioRequest;
import com.financialtracker.financial_portfolio_tracker.model.PortfolioResponse;
import com.financialtracker.financial_portfolio_tracker.model.UserResponse;

public class PortfolioMapper {
    public static EPortfolio mapPortfolioRequestToEntity(PortfolioRequest request , EUser eUser) {
        EPortfolio ePortfolio = new EPortfolio();
        ePortfolio.setName(request.getName());
        ePortfolio.seteUser(eUser);
        return ePortfolio;
    }

    public static EPortfolio mapUpdatePortfolioRequestToEntity(PortfolioRequest request, Integer id , EUser eUser) {
        EPortfolio ePortfolio = new EPortfolio();
        ePortfolio.setId(id);
        ePortfolio.setName(request.getName());
        ePortfolio.seteUser(eUser);
        ePortfolio.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return ePortfolio;
    }

    public static PortfolioResponse mapPortfolioEntityToResponse(EPortfolio ePortfolio) {
    	PortfolioResponse response = new PortfolioResponse();
        response.setId(ePortfolio.getId());
        response.setName(ePortfolio.getName());
        UserResponse userResponse = new UserResponse();
        userResponse.setId(ePortfolio.geteUser().getId());
        userResponse.setUsername(ePortfolio.geteUser().getUsername());
        userResponse.setEmail(ePortfolio.geteUser().getEmail());
        response.setUser(userResponse);
        response.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        response.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return response;
    }

}
