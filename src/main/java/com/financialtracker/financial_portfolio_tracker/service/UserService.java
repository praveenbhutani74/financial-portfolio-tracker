package com.financialtracker.financial_portfolio_tracker.service;

import com.financialtracker.financial_portfolio_tracker.model.UserDTO;
import com.financialtracker.financial_portfolio_tracker.model.UserDTORequest;
import com.financialtracker.financial_portfolio_tracker.model.UserLoginRequest;

public interface UserService {
    UserDTO register(UserDTORequest userDTO);
    
    UserDTO login(UserLoginRequest userDTO);
    
    
}
