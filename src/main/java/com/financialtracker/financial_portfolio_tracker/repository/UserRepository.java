package com.financialtracker.financial_portfolio_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financialtracker.financial_portfolio_tracker.entity.EUser;

public interface UserRepository extends JpaRepository<EUser, Integer> {

}
