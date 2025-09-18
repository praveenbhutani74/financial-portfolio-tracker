package com.financialtracker.financial_portfolio_tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.financialtracker.financial_portfolio_tracker.exception.EmailAlreadyExistsException;
import com.financialtracker.financial_portfolio_tracker.model.UserDTO;
import com.financialtracker.financial_portfolio_tracker.model.UserDTORequest;
import com.financialtracker.financial_portfolio_tracker.model.UserLoginRequest;
import com.financialtracker.financial_portfolio_tracker.repository.UserRepository;
import com.financialtracker.financial_portfolio_tracker.service.UserService;
import com.financialtracker.financial_portfolio_tracker.util.JWTUtil;


@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
    @Autowired
    private JWTUtil jwtUtil;

	
	@Override
	public UserDTO register(UserDTORequest userDTO) {
		userRepository.findByEmail(userDTO.getEmail())
	    .ifPresent(user -> {
	        throw new EmailAlreadyExistsException("Email already registered");
	    });
		
		User newUser  = new User();
		newUser.setEmail(userDTO.getEmail());
		newUser.setUserName(userDTO.getUserName());
		newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	    userRepository.save(newUser );
	    
	    UserDTO responseDTO = new UserDTO();
	    responseDTO.setEmail(newUser.getEmail());
	    responseDTO.setPassword(null); 
	    responseDTO.setUserName(newUser.getUserName());

	    return responseDTO;
	}


	@Override
	public UserDTO login(UserLoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		 User user = userRepository.findByEmail(loginRequest.getEmail())
		            .orElseThrow(() -> new EmailAlreadyExistsException("User not found"));
		 
		 String token = jwtUtil.generateToken(loginRequest.getEmail());
		  
		 UserDTO responseDTO = new UserDTO();
		    responseDTO.setId(user.getId());
		    responseDTO.setEmail(user.getEmail());
		    responseDTO.setUserName(user.getUserName()); 
		    responseDTO.setToken(token);

		    return responseDTO;
		
	}
}
