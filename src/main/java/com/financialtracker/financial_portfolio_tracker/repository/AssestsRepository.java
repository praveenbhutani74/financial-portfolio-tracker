package com.financialtracker.financial_portfolio_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financialtracker.financial_portfolio_tracker.entity.EAssets;

@Repository
public interface AssestsRepository extends JpaRepository<EAssets, Integer> {

}
