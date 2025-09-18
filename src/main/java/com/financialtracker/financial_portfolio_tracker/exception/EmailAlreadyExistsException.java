package com.financialtracker.financial_portfolio_tracker.exception;

public class EmailAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

	 public EmailAlreadyExistsException(String message) {
	        super(message);
	    }
}
