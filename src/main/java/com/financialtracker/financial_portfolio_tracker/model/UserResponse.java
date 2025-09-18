package com.financialtracker.financial_portfolio_tracker.model;

import java.sql.Timestamp;

public class UserResponse {
	  private Integer id;
	    private String email;
	    private String username;
	    private Timestamp createdAt;
	    private Timestamp updatedAt;

	    // Getters and Setters
	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public Timestamp getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(Timestamp createdAt) {
	        this.createdAt = createdAt;
	    }

	    public Timestamp getUpdatedAt() {
	        return updatedAt;
	    }

	    public void setUpdatedAt(Timestamp updatedAt) {
	        this.updatedAt = updatedAt;
	    }
}
