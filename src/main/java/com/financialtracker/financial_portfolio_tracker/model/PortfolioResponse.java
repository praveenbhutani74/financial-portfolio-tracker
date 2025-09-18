package com.financialtracker.financial_portfolio_tracker.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class PortfolioResponse {
    private Integer id;
    private String name;
    private UserResponse user;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // ✅ New fields
    private List<AssestsResponse> assets;   
    private BigDecimal totalCurrentValue;   

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public UserResponse getUser() {
        return user;
    }
    public void setUser(UserResponse user) {
        this.user = user;
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

    public List<AssestsResponse> getAssets() {
        return assets;
    }
    public void setAssets(List<AssestsResponse> assets) {
        this.assets = assets;
    }

    public BigDecimal getTotalCurrentValue() {
        return totalCurrentValue;
    }
    public void setTotalCurrentValue(BigDecimal totalCurrentValue) {
        this.totalCurrentValue = totalCurrentValue;
    }
}
