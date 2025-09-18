package com.financialtracker.financial_portfolio_tracker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financialtracker.financial_portfolio_tracker.model.UserDTO;
import com.financialtracker.financial_portfolio_tracker.model.UserDTORequest;
import com.financialtracker.financial_portfolio_tracker.model.UserLoginRequest;
import com.financialtracker.financial_portfolio_tracker.response.Response;
import com.financialtracker.financial_portfolio_tracker.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "User APIs", description = "Endpoints for managing users")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;


    
    @PostMapping("/register")
    public Response<UserDTO> register(@RequestBody UserDTORequest userDTO) {
    	Response<UserDTO> response = new Response<>();
    	response.setPayload(userService.register(userDTO));
    	response.setSuccess(true);
    	response.setMessage("User registered successfully");
       return response;
    }

    @PostMapping("/login")
    public Response<UserDTO> login(@RequestBody UserLoginRequest loginRequest) {
    	Response<UserDTO> response = new Response<>();
    	response.setPayload(userService.login(loginRequest));
    	response.setSuccess(true);
    	response.setMessage("User login successfully");
       return response;
    }

    
}
