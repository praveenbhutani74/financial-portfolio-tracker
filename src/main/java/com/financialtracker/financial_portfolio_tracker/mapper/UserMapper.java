package com.financialtracker.financial_portfolio_tracker.mapper;

import com.financialtracker.financial_portfolio_tracker.entity.EUser;
import com.financialtracker.financial_portfolio_tracker.model.UserRequest;
import com.financialtracker.financial_portfolio_tracker.model.UserResponse;

public class UserMapper {

	 public static EUser mapUserRequestToEntity(UserRequest request) {
	        EUser eUser = new EUser();
	        eUser.setEmail(request.getEmail());
	        eUser.setUsername(request.getUsername());
	        eUser.setPassword(request.getPassword());
	        return eUser;
	    }

	    public static EUser mapUpdateUserRequestToEntity(UserRequest request, Integer id) {
	        EUser eUser = new EUser();
	        eUser.setId(id);
	        eUser.setEmail(request.getEmail());
	        eUser.setUsername(request.getUsername());
	        eUser.setPassword(request.getPassword());
	        return eUser;
	    }

	    public static UserResponse mapUserEntityToResponse(EUser eUser) {
	    	UserResponse response = new UserResponse();
	        response.setId(eUser.getId());
	        response.setEmail(eUser.getEmail());
	        response.setUsername(eUser.getUsername());
	        response.setCreatedAt(eUser.getCreatedAt());
	        response.setUpdatedAt(eUser.getUpdatedAt());
	        return response;
	    }
}
