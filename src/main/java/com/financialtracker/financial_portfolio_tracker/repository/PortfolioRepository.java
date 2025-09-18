package com.financialtracker.financial_portfolio_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financialtracker.financial_portfolio_tracker.entity.EPortfolio;

public interface PortfolioRepository extends JpaRepository<EPortfolio, Integer> {

}
